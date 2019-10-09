package com.curtain.hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Curtain
 * @date 2019/10/8 15:52
 */
public class TreeMapConsistentHash extends AbstractConsistentHash {

    /**
     * 存储 hash(key)  节点ip（value）
     */
    private TreeMap<Long, String> treeMap = new TreeMap<>();

    /**
     * 虚拟节点个数
     */
    private static final int VIRTUAL_NODE_SIZE = 30;

    /**
     * 每个节点增加2个 虚拟节点  （尽可能分配均匀）
     *
     * @param key
     * @param value
     */
    @Override
    protected void add(long key, String value) {
        for (int i = 0; i < VIRTUAL_NODE_SIZE; i++) {
            Long hash = super.hash("vir" + key + i);
            treeMap.put(hash, value);
        }
        treeMap.put(key, value);
    }

    @Override
    protected void sort() {
        super.sort();
    }

    @Override
    protected String getFirstNodeValue(String value) {
        long hash = hash(value);
        System.out.println("value = " + value + " hash = " + hash);
        SortedMap<Long, String> last = treeMap.tailMap(hash);
        if (!last.isEmpty()) {
            return last.get(last.firstKey());
        }

        return treeMap.firstEntry().getValue();
    }
}
