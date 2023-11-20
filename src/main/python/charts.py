import matplotlib.pyplot as plt

# Updated data with execution times in milliseconds
graph_sizes = [10, 100, 1000, 10000, 100000, 1000000]
dijkstra_times = [1.4, 4.0, 17.0, 105.2, 3174.8, 305795]  # in milliseconds
improved_times = [0.0, 3.0, 12.8, 94, 568.6, 8252.0]  # in milliseconds

import matplotlib.pyplot as plt

# Updated data with execution times in milliseconds
graph_sizes = [10, 100, 1000, 10000, 100000, 1000000]
dijkstra_times = [1.4, 4.0, 17.0, 105.2, 3174.8, 305795]  # in milliseconds
improved_times = [0.0, 3.0, 12.8, 94, 568.6, 8252.0]  # in milliseconds

# Enhancing the plot with additional features

plt.figure(figsize=(10, 6))
plt.plot(graph_sizes, dijkstra_times, label='Dijkstra', marker='o', color='blue', linestyle='dashed')
plt.plot(graph_sizes, improved_times, label='Improved Dijkstra', marker='s', color='green')

# Adding title and labels with enhanced formatting
plt.title('Enhanced Comparison of Dijkstra and Improved Dijkstra Execution Times', fontsize=14)
plt.xlabel('Graph Size', fontsize=12)
plt.ylabel('Execution Time (milliseconds)', fontsize=12)

# Logarithmic scales for better visualization
plt.xscale('log')
plt.yscale('log')

# Adding grid lines
plt.grid(True, which="both", linestyle='--', linewidth=0.5)

# Adding legend with location adjustment
plt.legend(loc='upper left')

# Show the enhanced plot
plt.show()
