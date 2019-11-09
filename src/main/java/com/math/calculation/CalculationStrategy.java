package com.math.calculation;

import com.math.data.PointsWrapper;
import com.math.data.DistanceMatrix;

public interface CalculationStrategy {
    void calculateDistances(PointsWrapper pointsWrapper, DistanceMatrix matrix);
}