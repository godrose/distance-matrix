package com.math.infra;

import com.math.calculation.PointHelper;
import com.math.data.*;

import java.util.concurrent.Callable;

public class DistanceGroupCallableImpl implements Callable<String> {

    private DataWrapper dataWrapper;
    private IndexMap indexMap;

    public DistanceGroupCallableImpl(DataWrapper dataWrapper) {
        this.dataWrapper = dataWrapper;
        this.indexMap = new IndexMap(dataWrapper.getPointsWrapper());
    }

    @Override
    public String call() {
        int groupSize = dataWrapper.getPointsWrapper().getSize() / dataWrapper.getNumberOfGroups();
        long startPointIndex = groupSize * dataWrapper.getGroupIndex();
        long endPointIndex = dataWrapper.getGroupIndex() == dataWrapper.getNumberOfGroups() - 1 ? dataWrapper.getPointsWrapper().getTotalNumberOfCalculations() : startPointIndex + groupSize;
        calcGroup(startPointIndex, endPointIndex, dataWrapper.getPointsWrapper(), dataWrapper.getMatrix());
        System.out.println("Completed group: " + dataWrapper.getGroupIndex());
        return "Completed";
    }

    private void calcGroup(long startIndex, long endIndex, PointsWrapper pointsWrapper, DistanceMatrix matrix) {
        for (long i = startIndex; i < endIndex; i++) {
            IndexInfo info = this.indexMap.getInfoByIndex(i);
            double distance = PointHelper.calcDistance(pointsWrapper.getPoints().get(info.getFirstIndex()), pointsWrapper.getPoints().get(info.getSecondIndex()));
            matrix.addDistance(info.getFirstIndex(), info.getSecondIndex(), distance);
        }
    }

}