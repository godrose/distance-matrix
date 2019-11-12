package com.math.data;

import com.math.scheduling.Scheduler;

public class DataWrapper {
    private int groupIndex;
    private PointsWrapper pointsWrapper;
    private DistanceMatrix matrix;
    private int numberOfGroups;
    private Scheduler scheduler;

    public DataWrapper(int index, PointsWrapper pointsWrapper, DistanceMatrix matrix, int numberOfGroups, Scheduler scheduler) {
        this.groupIndex = index;
        this.pointsWrapper = pointsWrapper;
        this.matrix = matrix;
        this.numberOfGroups = numberOfGroups;
        this.scheduler = scheduler;
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

    public Scheduler getScheduler() {
        return scheduler;
    }
}
