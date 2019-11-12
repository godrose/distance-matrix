package com.math.scheduling;

public class TestScheduler implements Scheduler {
    @Override
    public void workFor(long millisec) throws InterruptedException {
        Thread.sleep(200);
    }
}
