import pandas as pd
import matplotlib.pyplot as plt

def read_data_and_plot(csv_file_path):
    # Reading data from CSV
    df = pd.read_csv(csv_file_path)

    # Setting up the plot
    plt.figure(figsize=(12, 6))

    # Plotting the average time for both algorithms
    plt.plot(df["NodeSize"], df["AverageTime Improved"], label="Improved Algorithm", marker='o')
    plt.plot(df["NodeSize"], df["AverageTime Original"], label="Original Algorithm", marker='o')

    # Setting the plot title and labels
    plt.title("Algorithm Performance Comparison")
    plt.xlabel("Node Size")
    plt.ylabel("Average Time (s)")
    plt.xscale('log')  # Setting x-axis to logarithmic scale due to wide range of node sizes
    plt.yscale('log')  # Using logarithmic scale for y-axis for better visualization of differences
    plt.grid(True, which="both", linestyle='--', linewidth=0.5)

    # Adding a legend
    plt.legend()

    # Show the plot
    plt.show()
