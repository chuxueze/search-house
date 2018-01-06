package com.jiangyx.soufang.dto;

public final class QiNiuPutResult {

    public String key;
    public String hash;
    public String bucket;
    public int width;
    public int weight;

    @Override
    public String toString() {
        return "QiNiuPutResult{" +
                "key='" + key + '\'' +
                ", hash='" + hash + '\'' +
                ", bucket='" + bucket + '\'' +
                ", width=" + width +
                ", weight=" + weight +
                '}';
    }
}
