package com.math.calculation;

import com.math.data.PointsWrapper;
import com.math.data.DataWrapper;
import com.math.data.DistanceMatrix;
import com.math.infra.DistanceGroupCallableImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GroupBasedCalculationStrategy implements CalculationStrategy {
    private int numberOfThreads;

    public GroupBasedCalculationStrategy(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public void calculateDistances(PointsWrapper pointsWrapper, DistanceMatrix matrix) {
        ExecutorService executor = Executors.newFixedThreadPool(this.numberOfThreads);
        List<Callable<String>> tasks = new ArrayList<Callable<String>>();
        for (int i = 0; i < this.numberOfThreads; i++) {
            Callable<String> worker = new DistanceGroupCallableImpl(new DataWrapper(i, pointsWrapper, matrix, this.numberOfThreads));
            tasks.add(worker);
        }
        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}