package com.service.impl;

import com.dao.AdminMapper;
import com.entity.Admin;
import com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public Admin login(Admin admin) {
        return adminMapper.login(admin);
    }
}
