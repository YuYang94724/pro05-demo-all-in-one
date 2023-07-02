package com.atguigu.imperial.court.service.impl;

import com.atguigu.imperial.court.dao.api.MemorialsDao;
import com.atguigu.imperial.court.dao.impl.MemorialsDaoImpl;
import com.atguigu.imperial.court.entity.Memorials;
import com.atguigu.imperial.court.service.api.MemorialsService;

import java.util.List;

public class MemorialsServiceImpl implements MemorialsService {


    private MemorialsDao memorialsDao = new MemorialsDaoImpl();

    @Override
    public List<Memorials> getAllMemorialsDigest() {
        return memorialsDao.selectAllMemorialsDigest();
    }
}