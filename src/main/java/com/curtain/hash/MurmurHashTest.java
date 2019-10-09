package com.curtain.hash;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

/**
 * @author Curtain
 * @date 2019/10/8 11:28
 */
public class MurmurHashTest {
    public static void main(String[] args) {
        HashFunction function = Hashing.murmur3_32();
        HashCode hascode = function.hashString("hello1", Charset.forName("utf-8"));
        System.out.println(hascode.asInt());
        HashCode hascode2 = function.hashString("hello2", Charset.forName("utf-8"));
        System.out.println(hascode2.asInt());
        HashCode hascode3 = function.hashString("hello3", Charset.forName("utf-8"));
        System.out.println(hascode3.asInt());

    }
}
