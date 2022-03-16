package com.landian.service;

import com.landian.domain.Determinant;
import org.springframework.stereotype.Service;

public interface DeterminantService {
    float toResult(float [][]Matrix,int N);
}
