package com.landian.service.impl;

import com.landian.domain.Determinant;
import com.landian.service.DeterminantService;
import org.springframework.stereotype.Service;

@Service("DeterminantService")
public class DeterminantServiceImpl implements DeterminantService {
    @Override
    public float toResult(float [][]Matrix,int N) {
        int T0;
        int T1;
        int T2;
        float Num;
        int Cha;
        float [][] B;
        if(N>0)
        {
            Cha=0;
            B=new float[N][N];
            Num=0;
            if(N==1)
            {
                return Matrix[0][0]*Matrix[1][1]-Matrix[0][1]*Matrix[1][0];
            }
            for(T0=0;T0<=N;T0++)//T0循环
            {
                for(T1=1;T1<=N;T1++)//T1循环
                {
                    for(T2=0;T2<=N-1;T2++)//T2循环
                    {
                        if(T2==T0)
                        {
                            Cha=1;

                        }
                        B[T1-1][T2]=Matrix[T1][T2+Cha];
                    }//T2循环
                    Cha=0;
                }//T1循环
                Num= (float) (Num+Matrix[0][T0]*toResult(B,N-1)*Math.pow((-1),T0));
            }//T0循环
            return Num;
        }
        else if(N==0)
        {
            return Matrix[0][0];
        }

        return 0;

    }

}
