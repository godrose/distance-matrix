package com.math.data;

public class DistanceMatrix {
    private double[][] distances;

    public DistanceMatrix(int size) {
        this.distances = new double[size][size];
    }

    public void addDistance(int i, int j, double distance) {
        this.distances[i][j] = distance;
        if (i != j) {
            this.distances[j][i] = distance;
        }
    }

    public double[] getDistances(int line) {
        return distances[line];
    }
}