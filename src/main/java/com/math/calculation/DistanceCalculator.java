package com.math.calculation;

import com.math.data.PointsWrapper;
import com.math.data.DistanceMatrix;
import com.math.data.Point;

import java.util.List;

public class DistanceCalculator {
    public DistanceMatrix calculateDistances(PointsWrapper pointsWrapper, int numberOfThreads) {
        List<Point> points = pointsWrapper.getPoints();
        int size = points.size();
        DistanceMatrix result = new DistanceMatrix(size);
        if (numberOfThreads <= 0) {
            throw new RuntimeException("Illegal number of threads " + numberOfThreads);
        }
        if (size > 1) {
            CalculationStrategy calculationStrategy = new GroupBasedCalculationStrategy(numberOfThreads);
            calculationStrategy.calculateDistances(pointsWrapper, result);
        }
        return result;
    }

    private boolean shouldUseMultiThreading(PointsWrapper pointsWrapper, int numberOfThreads) {
        long totalNumber = pointsWrapper.getTotalNumberOfCalculations();
        return numberOfThreads * 2 <= totalNumber;
    }
}