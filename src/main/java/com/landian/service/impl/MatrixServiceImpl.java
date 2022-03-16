package com.landian.service.impl;

import com.landian.domain.Matrix;
import com.landian.service.MatrixService;
import org.springframework.stereotype.Service;

@Service("MatrixService")
public class MatrixServiceImpl implements MatrixService {
    @Override
    public Matrix add(Matrix matrix1, Matrix matrix2) {
        float[][] m1 = matrix1.getMat();
        float[][] m2 = matrix2.getMat();
        if(m1==null||m2==null||
                m1.length!=m2.length||
                m1[0].length!=m2[0].length) {
            return null;
        }

        float[][] m = new float[m1.length][m1[0].length];

        for(int i=0;i<m.length;++i){
            for (int j=0;j<m[i].length;++j){
                m[i][j]=m1[i][j]+m2[i][j];
            }
        }
        Matrix matrix = new Matrix();
        matrix.setMat(m);
        matrix.setRow(matrix1.getRow());
        matrix.setColumn(matrix1.getColumn());
        return matrix;
    }

    @Override
    public Matrix subtract(Matrix matrix1, Matrix matrix2) {
        float[][] m1 = matrix1.getMat();
        float[][] m2 = matrix2.getMat();
        if(m1==null||m2==null||
                m1.length!=m2.length||
                m1[0].length!=m2[0].length) {
            return null;
        }

        float[][] m = new float[m1.length][m1[0].length];

        for(int i=0;i<m.length;++i){
            for (int j=0;j<m[i].length;++j){
                m[i][j]=m1[i][j]-m2[i][j];
            }
        }
        Matrix matrix = new Matrix();
        matrix.setMat(m);
        matrix.setRow(matrix1.getRow());
        matrix.setColumn(matrix1.getColumn());
        return matrix;
    }

    @Override
    public Matrix multiply(Matrix matrix1, Matrix matrix2) {
        float[][] m1 = matrix1.getMat();
        float[][] m2 = matrix2.getMat();
        int M=m1.length;
        int N=m1[0].length;
        int K=m2[0].length;
        float[][] m=new float[M][K];
        for(int i=0;i<M;i++){
            for(int j=0;j<K;j++){
                for(int k=0;k<N;k++){
                    m[i][j]+=m1[i][k]*m2[k][j];
                }
            }
        }
        Matrix matrix = new Matrix();
        matrix.setMat(m);
        matrix.setRow(matrix1.getRow());
        matrix.setColumn(matrix2.getColumn());
        return matrix;
    }

    @Override
    public Matrix transpose(Matrix matrix) {
        float[][] data = matrix.getMat();
        int i=matrix.getRow();
        int j=matrix.getColumn();
        float[][] m=new float[j][i];
        for(int k2=0;k2<j;k2++){
            for(int k1=0;k1<i;k1++){
                m[k2][k1]=data[k1][k2];
            }
        }
        Matrix matrix1 = new Matrix();
        matrix1.setMat(m);
        matrix1.setRow(matrix.getColumn());
        matrix1.setColumn(matrix.getRow());
        return matrix1;

    }

    @Override
    public Matrix inverse(Matrix matrix) {
        float[][] data = matrix.getMat();
        int M=matrix.getRow();
        int N=matrix.getColumn();
        float data2[][]=new float[M][N];
        float det_val=cal_det(data);
        System.out.println(det_val);
        data2=ajoint(data);
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                data2[i][j]=data2[i][j]/det_val;
            }
        }
        Matrix matrix1 = new Matrix();
        matrix1.setMat(data2);
        matrix1.setRow(matrix.getColumn());
        matrix1.setColumn(matrix.getRow());
        return matrix1;
    }

    @Override
    public float rank(Matrix matrix1) {
        int error_ = -1;
        float[][] Matrix = matrix1.getMat();
        int n=matrix1.getColumn();
        int m=matrix1.getRow();
        int i=0;
        int i1;
        int j=0;
        int j1;
        float temp1;
        if(m>n)
        {
            i=m;
            m=n;
            n=i;
            i=1;
        }
        m-=1;
        n-=1;
        float[][]temp=new float[m+1][n+1];
        if(i==0)
        {
            for(i=0;i<=m;i++)
            {
                for(j=0;j<=n;j++)
                {
                    temp[i][j]=Matrix[i][j];
                }

            }
        }
        else
        {
            for(i=0;i<=m;i++)
            {
                for(j=0;j<=n;j++)
                {
                    temp[i][j]=Matrix[j][i];
                }
            }
        }
        if(m==0)
        {
            i=0;
            while(i<=n)
            {
                if(Matrix[0][i]!=0)
                {
                    return 1;
                }
                i+=1;
            }
            return 0;
        }
        float error0;
        if(error_==-1)
        {
            error0= (float) Math.pow(0.1, 10);
        }
        else
        {
            error0= (float) Math.pow(0.1, error_);
        }
        i=0;
        while(i<=m)
        {
            j=0;
            while(j<=n)
            {
                if(temp[i][j]!=0)
                {
                    error0*=temp[i][j];
                    i=m;
                    break;
                }
                j+=1;
            }
            i+=1;
        }
        float error1;
        for(i=0;i<=m;i++)
        {
            j=0;
            while(j<=n)
            {
                if(temp[i][j]!=0)
                {
                    break;
                }
                j+=1;
            }
            if(j<=n)
            {
                i1=0;
                while(i1<=m)
                {
                    if(temp[i1][j]!=0&&i1!=i)
                    {
                        temp1=temp[i][j]/temp[i1][j];
                        error1=Math.abs((temp[i][j]-temp[i1][j]*temp1))*100;
                        error1+=error0;
                        for(j1=0;j1<=n;j1++)
                        {
                            temp[i1][j1]=temp[i][j1]-temp[i1][j1]*temp1;
                            if(Math.abs(temp[i1][j1])<error1)
                            {
                                temp[i1][j1]=0;
                            }
                        }

                    }
                    i1+=1;
                }
            }
        }
        i1=0;
        for(i=0;i<=m;i++)
        {
            for(j=0;j<=n;j++)
            {
                if(temp[i][j]!=0)
                {
                    i1+=1;
                    break;
                }
            }
        }
        return i1;
    }

    @Override
    public float eigenValue(Matrix matrix1, Matrix matrix2) {
        return 0;
    }

    @Override
    public Boolean judgeSame(Matrix matrix1, Matrix matrix2) {
        float[][] mat1 = matrix1.getMat();
        float[][] mat2 = matrix2.getMat();
        float v1 = cal_det(mat1);
        float v2 = cal_det(mat2);
        float rank1 = rank(matrix1);
        float rank2 = rank(matrix2);
        if(v1==v2 && rank1==rank2 ){
            return true;
        }
        return false;
    }

    /*计算矩阵的伴随矩阵*/
    private float[][] ajoint(float[][] data) {
        int M=data.length;
        int N=data[0].length;
        float m[][]=new float[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if((i+j)%2==0){
                    m[i][j]=cal_det(get_complement(data, i, j));
                }
                else{
                    m[i][j]=-cal_det(get_complement(data, i, j));
                }
            }
        }

        Matrix matrix1 = new Matrix();
        matrix1.setMat(m);
        matrix1.setRow(M);
        matrix1.setColumn(M);
        return transpose(matrix1).getMat();

    }
    /* 计算矩阵行列式 */
    private float cal_det(float[][] data) {
        float ans=0;
        /*若为2*2的矩阵可直接求值并返回*/
        if(data[0].length==2){
            ans=data[0][0]*data[1][1]-data[0][1]*data[1][0];
        }
        else{
            for(int i=0;i<data[0].length;i++){
                /*若矩阵不为2*2那么需求出矩阵第一行代数余子式的和*/
                float[][] data_temp=get_complement(data, 0, i);
                if(i%2==0){
                    /*递归*/
                    ans=ans+data[0][i]*cal_det(data_temp);
                }
                else{
                    ans=ans-data[0][i]*cal_det(data_temp);
                }
            }
        }
        return ans;

    }

    /*原矩阵去掉第i+1行第j+1列后的剩余矩阵*/
    private float[][] get_complement(float[][] data, int i, int j) {

        /* x和y为矩阵data的行数和列数 */
        int x = data.length;
        int y = data[0].length;

        /* data2为所求剩余矩阵 */
        float data2[][] = new float[x - 1][y - 1];
        for (int k = 0; k < x - 1; k++) {
            if (k < i) {
                for (int kk = 0; kk < y - 1; kk++) {
                    if (kk < j) {
                        data2[k][kk] = data[k][kk];
                    } else {
                        data2[k][kk] = data[k][kk + 1];
                    }
                }

            } else {
                for (int kk = 0; kk < y - 1; kk++) {
                    if (kk < j) {
                        data2[k][kk] = data[k + 1][kk];
                    } else {
                        data2[k][kk] = data[k + 1][kk + 1];
                    }
                }
            }
        }
        return data2;

    }
}
