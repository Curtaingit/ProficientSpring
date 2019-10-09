package com.curtain.hash;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;
import java.util.List;

import static java.util.Objects.hash;

/**
 * @author Curtain
 * @date 2019/10/8 15:40
 */
public abstract class AbstractConsistentHash {

    protected abstract void add(long key, String value);

    protected void sort() {
    }

    ;

    protected abstract String getFirstNodeValue(String value);

    public String process(List<String> values, String key) {
        for (String value : values) {
            add(hash(value), value);
        }
        sort();
        return getFirstNodeValue(key);
    }

    public Long hash(String key) {
        HashFunction function = Hashing.murmur3_32();
        HashCode hascode = function.hashString(key, Charset.forName("utf-8"));
        return Long.valueOf(hascode.asInt());
    }
}
