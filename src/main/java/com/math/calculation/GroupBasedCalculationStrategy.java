package com.math.calculation;

import com.math.data.DataWrapper;
import com.math.data.DistanceMatrix;
import com.math.data.PointsWrapper;
import com.math.infra.DistanceGroupRunnableImpl;
import com.math.scheduling.Scheduler;

import java.util.ArrayList;
import java.util.List;

public class GroupBasedCalculationStrategy implements CalculationStrategy {
    private int numberOfThreads;
    private Scheduler scheduler;

    public GroupBasedCalculationStrategy(int numberOfThreads, Scheduler scheduler) {
        this.numberOfThreads = numberOfThreads;
        this.scheduler = scheduler;
    }

    public void calculateDistances(PointsWrapper pointsWrapper, DistanceMatrix matrix) {
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < this.numberOfThreads; i++) {
            Thread thread = new Thread(new DistanceGroupRunnableImpl(new DataWrapper(i, pointsWrapper, matrix, this.numberOfThreads, this.scheduler)));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread :
                threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {

            }
        }
    }
}