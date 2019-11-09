package com.math.data;

public class DataWrapper {
    private int groupIndex;
    private PointsWrapper pointsWrapper;
    private DistanceMatrix matrix;
    private int numberOfGroups;

    public DataWrapper(int index, PointsWrapper pointsWrapper, DistanceMatrix matrix, int numberOfGroups) {
        this.groupIndex = index;
        this.pointsWrapper = pointsWrapper;
        this.matrix = matrix;
        this.numberOfGroups = numberOfGroups;
    }

    public int getGroupIndex() {
        return groupIndex;
    }

    public PointsWrapper getPointsWrapper() {
        return pointsWrapper;
    }

    public DistanceMatrix getMatrix() {
        return matrix;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }
}
