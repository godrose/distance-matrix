package com.math.data;

import java.util.HashMap;
import java.util.Map;

public class IndexMap {
    private Map<Long, IndexInfo> internalMap = new HashMap<Long, IndexInfo>();

    public IndexMap(PointsWrapper pointsWrapper) {
        long index = 0;
        for (int i = 0; i < pointsWrapper.getSize(); i++) {
            for (int j = i + 1; j < pointsWrapper.getSize(); j++) {
                this.internalMap.put(index++, new IndexInfo(i, j));
            }
        }
    }

    public IndexInfo getInfoByIndex(long index) {
        return internalMap.get(index);
    }
}