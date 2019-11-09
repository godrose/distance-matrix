package com.math.calculation;

import com.math.data.Point;

import java.lang.Math;

public class PointHelper {
    public static double calcDistance(Point pointA, Point pointB) {
        return Math.sqrt(Math.pow(pointA.getX() - pointB.getX(), 2) + Math.pow(pointA.getY() - pointB.getY(), 2));
    }
}