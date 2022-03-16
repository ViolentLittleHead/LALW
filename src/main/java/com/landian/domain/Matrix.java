package com.landian.domain;

import java.util.Arrays;

public class Matrix {
    private Integer row;
    private Integer column;
    private float mat[][];

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public float[][] getMat() {
        return mat;
    }

    public void setMat(float[][] mat) {
        this.mat = mat;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "row=" + row +
                ", column=" + column +
                ", mat=" + Arrays.toString(mat) +
                '}';
    }
}
