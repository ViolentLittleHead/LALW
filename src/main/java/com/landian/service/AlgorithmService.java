package com.landian.service;

import com.landian.domain.Algorithm;

import java.util.List;

public interface AlgorithmService {
    public void uploadAlgorithmFile(Algorithm algorithm,int uid);

    public List<Algorithm> findAllAlgorithm(int currentPage);

    public Algorithm findAlgorithmById(int id);

    public Algorithm downloadAlgorithm(int id);
}
