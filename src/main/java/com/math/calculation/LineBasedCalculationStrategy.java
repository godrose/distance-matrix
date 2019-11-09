package com.math.calculation;

import com.math.data.PointsWrapper;
import com.math.data.DistanceMatrix;
import com.math.data.Point;

import java.util.List;

public class LineBasedCalculationStrategy implements CalculationStrategy {

    public void calculateDistances(PointsWrapper pointsWrapper, DistanceMatrix matrix) {
        List<Point> points = pointsWrapper.getPoints();
        int size = points.size();
        for (int i = 0; i < size - 1; i++) {
            calcPointDistances(points, size, matrix, i);
        }
    }

    private static void calcPointDistances(List<Point> points, int size, DistanceMatrix result, int line) {
        result.addDistance(line, line, 0);
        for (int j = line + 1; j < size; j++) {
            double distance = PointHelper.calcDistance(points.get(line), points.get(j));
            result.addDistance(line, j, distance);
        }
    }
}