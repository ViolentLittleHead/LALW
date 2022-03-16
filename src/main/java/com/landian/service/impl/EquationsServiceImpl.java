package com.landian.service.impl;

import com.landian.domain.Equations;
import com.landian.domain.Matrix;
import com.landian.service.EquationsService;
import com.landian.util.EquationsUtil;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

import static com.landian.util.EquationsUtil.mathDeterminantCalculation;

@Service("EquationsService")
public class EquationsServiceImpl implements EquationsService {
    @Override
    public String toResult(Equations equations) {
        DecimalFormat df = new DecimalFormat("0.0");
        EquationsUtil equationsUtil = new EquationsUtil();
        // 方程的未知数的个数
        int n = equations.getAcolumn();
        // 系数矩阵
        float[][] test = equations.getA();
        for (float[] floats : test) {
            for (float aFloat : floats) {
                System.out.print(aFloat+" ");
            }
        }
        //获取B矩阵
        float[] value = equations.getB();
        //返回值
        String s =null;
        try {
            // 转换成增广矩阵并进行初等行变化
            float[][] mathMatrix = mathDeterminantCalculation(equationsUtil.transferMatrix(
                    test, value));
            // 找出非零行的个数
            int checkMatrixRow = equationsUtil.effectiveMatrix(mathMatrix);
            // 根据未知数的个数和方程组非零行的个数来判断当前方程组的解的情况
            if (n > checkMatrixRow) {
                s = "未知数有" + n + "个，消元法后获取的阶梯方程组有"
                        + checkMatrixRow + "个方程,少于未知数个数，所以该方程有无数组解";
            } else if (n < checkMatrixRow) {
                s="未知数有" + n + "个，消元法后获取的阶梯方程组有"
                        + checkMatrixRow + "个方程,多于未知数个数，所以该方程有无解";
            } else {
                s = "未知数有" + n + "个，消元法后获取的阶梯方程组有"
                        + checkMatrixRow + "个方程,等于未知数个数，所以该方程有解\n";
                float[] result = equationsUtil.calculationResult(mathMatrix);
                for (int i = 0; i < result.length; i++) {
                    s+= "方程组的解为x" + (i + 1) + "="
                            + df.format(result[i])+"\n";
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
    }
}
