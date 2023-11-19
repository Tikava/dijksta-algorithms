# Improved Dijkstra Algorithm

A Java implementation of the Improved Dijkstra algorithm for finding the shortest path in a weighted graph.

## Overview

The Improved Dijkstra algorithm is an optimized version of Dijkstra's algorithm for finding the shortest path in a weighted graph. This project provides a Java implementation of this algorithm, allowing you to efficiently find the shortest path in various graph scenarios.

## Key Features

- Implementation of the Improved Dijkstra algorithm.
- Support for directed and weighted graphs.
- Easily customizable with your own graph data.

## Usage

To use this library in your Java project, simply include the provided classes and use the `ImprovedDijkstra` class to find the shortest path in your graph.

```java
// Example usage:
ImprovedDijkstra<Integer> dijkstra = new ImprovedDijkstra<>();
dijkstra.calculateShortestPath(sourceNode);
dijkstra.printPaths(nodes);
```