package com.landian.domain;

import java.util.Arrays;

public class Determinant {
    private float dtm[][];
    private Integer order;

    public float[][] getDtm() {
        return dtm;
    }

    public void setDtm(float[][] dtm) {
        this.dtm = dtm;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Determinant{" +
                "dtm=" + Arrays.toString(dtm) +
                ", order=" + order +
                '}';
    }
}
