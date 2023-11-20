import subprocess
import os
import xml.etree.ElementTree as ET
import pandas as pd
from collections import Counter
from plot_algorithm_performance import read_data_and_plot

BASE_DIR = os.path.join(os.path.dirname(__file__), '..', '..', '..')
#!IMPORTANT!
#Actual path to your maven
MAVEN_PATH = r"C:\Program Files\maven\apache-maven-3.9.1\bin\mvn.cmd"


node_size_mapping = {
    '1': 10,
    '2': 100,
    '3': 1000,
    '4': 10000,
    '5': 100000,
    '6': 1000000
}

def run_maven_tests(repetitions=10):
    # Run Maven tests multiple times
    for _ in range(repetitions):
        custom_dir = f"run_{_}"
        subprocess.run([MAVEN_PATH, "test", "-Pcustom-test-run", f"-Dcustom.dir={custom_dir}"], cwd=BASE_DIR, check=True)

def parse_and_analyze_test_results(repetitions=10):
    aggregated_results = {}

    for i in range(repetitions):
        results_dir = os.path.join(BASE_DIR, "target", "surefire-reports", f"run_{i}")
        for filename in os.listdir(results_dir):
            if filename.endswith(".xml"):
                tree = ET.parse(os.path.join(results_dir, filename))
                root = tree.getroot()
                for testcase in root.findall(".//testcase"):
                    key = (testcase.get("classname"), testcase.get("name"))
                    if key not in aggregated_results:
                        aggregated_results[key] = {
                            "Time": [],
                            "Status": []
                        }
                    aggregated_results[key]["Time"].append(float(testcase.get("time")))
                    aggregated_results[key]["Status"].append("FAILED" if testcase.find("failure") is not None else "PASSED")

    # Analyze results
    analyzed_results = []
    for key, data in aggregated_results.items():
        average_time = sum(data["Time"]) / repetitions
        status_counts = Counter(data["Status"])
        analyzed_results.append({
            "ClassName": key[0],
            "TestName": key[1],
            "AverageTime": average_time,
            "Passed": status_counts["PASSED"],
            "Failed": status_counts["FAILED"],
            "TestsRun": repetitions
        })

    return analyzed_results

def create_table(analyzed_results):
    df = pd.DataFrame(analyzed_results)
    
    df['NodeSize'] = df['TestName'].str.extract(r'\[(\d+)\]').replace(node_size_mapping)
    df['Algorithm'] = df['TestName'].str.contains('Improved').map({True: 'Improved', False: 'Original'})

    pivot_df = df.pivot_table(index='NodeSize', columns='Algorithm', 
                              values=['AverageTime', 'TestsRun'], 
                              aggfunc={'AverageTime': 'mean', 'TestsRun': 'sum'}).reset_index()

    # Flatten MultiIndex in columns
    pivot_df.columns = [' '.join(col).strip() for col in pivot_df.columns.values]

    # Calculate Performance Improvement
    pivot_df['Performance Improvement (%)'] = (pivot_df['AverageTime Original'] - pivot_df['AverageTime Improved']) / pivot_df['AverageTime Original'] * 100

    return pivot_df

def main():
    run_maven_tests(3)
    test_results = parse_and_analyze_test_results(3)
    df = create_table(test_results)
    print(df)
    csv_path = os.path.join(os.path.dirname(__file__), "test_results.csv")
    df.to_csv(csv_path, index=False)
    read_data_and_plot(csv_path)

if __name__ == "__main__":
    main()
