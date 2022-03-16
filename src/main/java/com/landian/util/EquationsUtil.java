package com.landian.util;

import java.text.DecimalFormat;

public class EquationsUtil {
    static DecimalFormat df = new DecimalFormat("0.0");
    /***
     * 增广矩阵机型初等行变化的算法
     *
     * @param value
     *            需要算的增广矩阵
     * @return 计算的结果
     */
    public static float[][] mathDeterminantCalculation(float[][] value)
            throws Exception {
        // 当矩阵的行数大于2时
        for (int i = 0; i < value.length; i++) {
            // 检查数组对角线位置的数值是否是0，如果是零则对该数组进行调换，查找到一行不为0的进行调换
            if (value[i][i] == 0) {
                value = changeDeterminantNoZero(value, i, i);
            }
            for (int j = 0; j < i; j++) {
                // 让开始处理的行的首位为0处理为三角形式
                // 如果要处理的列为0则和自己调换一下位置，这样就省去了计算
                if (value[i][j] == 0) {
                    continue;
                }
                // 如果要是要处理的行是0则和上面的一行进行调换
                if (value[j][j] == 0) {
                    float[] temp = value[i];
                    value[i] = value[i - 1];
                    value[i - 1] = temp;
                    continue;
                }
                float ratio = -(value[i][j] / value[j][j]);
                value[i] = addValue(value[i], value[j], ratio);
            }
        }
        return value;
    }

    /***
     * 将i行之前的每一行乘以一个系数，使得从i行的第i列之前的数字置换为0
     *
     * @param currentRow
     *            当前要处理的行
     * @param frontRow
     *            i行之前的遍历的行
     * @param ratio
     *            要乘以的系数
     * @return 将i行i列之前数字置换为0后的新的行
     */
    public static float[] addValue(float[] currentRow, float[] frontRow,
                                    float ratio) throws Exception {
        for (int i = 0; i < currentRow.length; i++) {
            currentRow[i] += frontRow[i] * ratio;
            currentRow[i] = Float.parseFloat(df.format(currentRow[i]));
        }
        return currentRow;
    }

    /**
     * 指定列的位置是否为0，查找第一个不为0的位置的行进行位置调换，如果没有则返回原来的值
     *
     * @param determinant
     *            需要处理的行列式
     * @param line
     *            要调换的行
     * @param row
     *            要判断的列
     */
    public static float[][] changeDeterminantNoZero(float[][] determinant,
                                                     int line, int row) throws Exception {
        for (int j = line; j < determinant.length; j++) {
            // 进行行调换
            if (determinant[j][row] != 0) {
                float[] temp = determinant[line];
                determinant[line] = determinant[j];
                determinant[j] = temp;
                return determinant;
            }
        }
        return determinant;
    }

    /**
     * 将系数矩阵和方程值的矩阵进行合并成增广矩阵
     *
     * @param coefficient
     *            系数矩阵
     * @param value
     *            方程值
     * @return 增广矩阵
     */
    public static float[][] transferMatrix(float[][] coefficient,
                                            float[] value) {
        float[][] temp = new float[coefficient.length][coefficient[0].length + 1];
        if (coefficient.length != value.length) {
            return temp;
        }
        // 将方程值添加到系数矩阵中
        for (int i = 0; i < coefficient.length; i++) {
            for (int j = 0; j < coefficient[0].length; j++) {
                temp[i][j] = coefficient[i][j];
            }
        }
        for (int i = 0; i < value.length; i++) {
            temp[i][temp[i].length - 1] = value[i];
        }
        return temp;
    }

    /**
     * 检查有效的行数，看非零行的个数
     *
     * @param value
     *            需要检查的数组
     * @return 非零行的个数
     */
    public static int effectiveMatrix(float[][] value) {
        for (int i = value.length - 1; i > -1; i--) {
            for (int j = 0; j < value[i].length; j++) {
                if (value[i][j] != 0) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    /**
     * 当方程组有解的时候计算方程组的解
     *
     * @param mathMatrix
     *            方程组的增广矩阵
     * @return 方程组的解
     */
    public static float[] calculationResult(float[][] mathMatrix) {
        // 有解时方程组的个数等于方程组的未知数
        float[] result = new float[mathMatrix.length];
        for (int i = mathMatrix.length - 1; i > -1; i--) {
            float temp = 0;
            for (int j = mathMatrix[i].length; j > 0; j--) {
                // 第一个为方程的解，需要将解赋值给临时变量
                if (mathMatrix[i][j - 1] != 0) {
                    if (j == mathMatrix[i].length) {
                        temp = mathMatrix[i][j - 1];
                    } else if (j - 1 > -1 && result[j - 1] != 0) {
                        temp -= mathMatrix[i][j - 1] * result[j - 1];
                    } else {
                        result[i] = temp / mathMatrix[i][j - 1];
                        continue;
                    }
                }
            }
        }
        return result;
    }

}
