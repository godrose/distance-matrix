package com.math;

import com.math.calculation.DistanceCalculator;
import com.math.data.DistanceMatrix;
import com.math.data.Point;
import com.math.data.PointsWrapper;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class DistanceCalculatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void calculationTest() {

        PointsWrapper pointsWrapper = new PointsWrapper(getPoints());
        DistanceCalculator calculator = new DistanceCalculator();
        for (int numOfThreads = 1; numOfThreads < 10; numOfThreads++) {
            DistanceMatrix result = calculator.calculateDistances(pointsWrapper, numOfThreads);
            Assert.assertArrayEquals("Should be the same", new double[]{0, 5, 10, 15}, result.getDistances(0), 0.0001);
            Assert.assertArrayEquals("Should be the same", new double[]{5, 0, 5, 10}, result.getDistances(1), 0.0001);
            Assert.assertArrayEquals("Should be the same", new double[]{10, 5, 0, 5}, result.getDistances(2), 0.0001);
            Assert.assertArrayEquals("Should be the same", new double[]{15, 10, 5, 0}, result.getDistances(3), 0.0001);
            for (int i = 0; i < pointsWrapper.getSize(); i++) {
                System.out.print("line " + i + " : ");
                for (double item : result.getDistances(i)) {
                    System.out.print("|" + item);

                }
                System.out.println("|");
            }
        }
    }

    @Test
    public void zeroThreadsTest() {
        PointsWrapper pointsWrapper = new PointsWrapper(getPoints());
        DistanceCalculator calculator = new DistanceCalculator();
        thrown.expect(RuntimeException.class);
        calculator.calculateDistances(pointsWrapper, 0);

    }

    private static List<Point> getPoints() {
        List<Point> source = new ArrayList<Point>();

        source.add(new Point(0, 0));
        source.add(new Point(3, 4));
        source.add(new Point(6, 8));
        source.add(new Point(9, 12));

        return source;
    }

}
