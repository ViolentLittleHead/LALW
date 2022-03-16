package com.landian.service.impl;

import com.github.pagehelper.PageHelper;
import com.landian.domain.Algorithm;
import com.landian.mapper.AlgorithmMapper;
import com.landian.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("AlgorithmService")
public class AlgorithmServiceImpl implements AlgorithmService {

    @Autowired
    private AlgorithmMapper algorithmMapper;

    @Override
    public void uploadAlgorithmFile(Algorithm algorithm, int uid) {
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        algorithmMapper.addAlgorithm(algorithm.getContent(),
                bf.format(algorithm.getDate()),
                String.valueOf(uid),
                algorithm.getTitle(),
                algorithm.getFileName());

    }

    @Override
    public List<Algorithm> findAllAlgorithm(int currentPage) {
        PageHelper.startPage(currentPage,8);
        List<Algorithm> allAlgorithm = algorithmMapper.findAllAlgorithm();
        return allAlgorithm;
    }

    @Override
    public Algorithm findAlgorithmById(int id) {
        Algorithm algorithm = algorithmMapper.findAlgorithmById(id);
        return algorithm;
    }

    @Override
    public Algorithm downloadAlgorithm(int id) {
        return algorithmMapper.downloadAlgorithm(id);
    }
}
