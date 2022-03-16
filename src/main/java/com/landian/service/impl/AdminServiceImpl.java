package com.landian.service.impl;

import com.landian.domain.Admin;
import com.landian.mapper.AdminMapper;
import com.landian.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String adminname, String password) {
        Admin login = adminMapper.login(adminname, password);
        return login;
    }
}
