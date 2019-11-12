package com.math.calculation;

import com.math.data.PointsWrapper;
import com.math.data.DistanceMatrix;
import com.math.data.Point;
import com.math.scheduling.Scheduler;

import java.util.List;

public class DistanceCalculator {

    private Scheduler scheduler;

    public DistanceCalculator(Scheduler scheduler) {

        this.scheduler = scheduler;
    }

    public DistanceMatrix calculateDistances(PointsWrapper pointsWrapper, int numberOfThreads) {
        List<Point> points = pointsWrapper.getPoints();
        int size = points.size();
        DistanceMatrix result = new DistanceMatrix(size);
        if (numberOfThreads <= 0) {
            throw new RuntimeException("Illegal number of threads " + numberOfThreads);
        }
        if (size > 1) {
            CalculationStrategy calculationStrategy = new GroupBasedCalculationStrategy(numberOfThreads, this.scheduler);
            calculationStrategy.calculateDistances(pointsWrapper, result);
        }
        return result;
    }
}