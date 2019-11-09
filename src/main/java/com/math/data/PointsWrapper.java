package com.math.data;

import java.util.ArrayList;
import java.util.List;

public class PointsWrapper {
    private List<Point> points;
    private int size;
    private int totalNumberOfCalculations;

    public PointsWrapper(List<Point> source) {
        this.points = new ArrayList<Point>();
        this.points.addAll(source);
        this.size = points.size();
        this.totalNumberOfCalculations = size * (size - 1) / 2;
    }

    public List<Point> getPoints() {
        return points;
    }

    public int getSize() {
        return size;
    }

    public int getTotalNumberOfCalculations() {
        return totalNumberOfCalculations;
    }
}