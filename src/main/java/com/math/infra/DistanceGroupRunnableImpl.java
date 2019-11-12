package com.math.infra;

import com.math.calculation.PointHelper;
import com.math.data.*;

public class DistanceGroupRunnableImpl implements Runnable {

    private DataWrapper dataWrapper;
    private IndexMap indexMap;

    public DistanceGroupRunnableImpl(DataWrapper dataWrapper) {
        this.dataWrapper = dataWrapper;
        this.indexMap = new IndexMap(dataWrapper.getPointsWrapper());
    }

    @Override
    public void run() {
        int groupSize = dataWrapper.getPointsWrapper().getSize() / dataWrapper.getNumberOfGroups();
        long startPointIndex = groupSize * dataWrapper.getGroupIndex();
        long endPointIndex = dataWrapper.getGroupIndex() == dataWrapper.getNumberOfGroups() - 1 ? dataWrapper.getPointsWrapper().getTotalNumberOfCalculations() : startPointIndex + groupSize;
        try {
            dataWrapper.getScheduler().workFor(200);
        }
        catch (Exception ex) {
        }

        calcGroup(startPointIndex, endPointIndex, dataWrapper.getPointsWrapper(), dataWrapper.getMatrix());
        System.out.println("Completed group: " + dataWrapper.getGroupIndex());
    }

    private void calcGroup(long startIndex, long endIndex, PointsWrapper pointsWrapper, DistanceMatrix matrix) {
        for (long i = startIndex; i < endIndex; i++) {
            IndexInfo info = this.indexMap.getInfoByIndex(i);
            double distance = PointHelper.calcDistance(pointsWrapper.getPoints().get(info.getFirstIndex()), pointsWrapper.getPoints().get(info.getSecondIndex()));
            matrix.addDistance(info.getFirstIndex(), info.getSecondIndex(), distance);
        }
    }

}
