package com.math.scheduling;

public interface Scheduler {
    void workFor(long millisec) throws InterruptedException;
}
