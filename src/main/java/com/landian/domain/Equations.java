package com.landian.domain;

import java.util.Arrays;

public class Equations {
    private Integer arow;
    private Integer acolumn;
    private Integer brow;
    private Integer i;//未知数的个数
    private float a[][];
    private float b[];

    public Integer getArow() {
        return arow;
    }

    public void setArow(Integer arow) {
        this.arow = arow;
    }

    public Integer getAcolumn() {
        return acolumn;
    }

    public void setAcolumn(Integer acolumn) {
        this.acolumn = acolumn;
    }

    public Integer getBrow() {
        return brow;
    }

    public void setBrow(Integer brow) {
        this.brow = brow;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public float[] getB() {
        return b;
    }

    public void setB(float[] b) {
        this.b = b;
    }

    public float[][] getA() {
        return a;
    }

    public void setA(float[][] a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "Equations{" +
                "arow=" + arow +
                ", acolumn=" + acolumn +
                ", brow=" + brow +
                ", i=" + i +
                ", a=" + Arrays.toString(a) +
                ", b=" + Arrays.toString(b) +
                '}';
    }
}
