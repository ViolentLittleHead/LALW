package com.landian.service;


import com.landian.domain.Admin;

public interface AdminService {

    public Admin login(String adminname, String password);
}
