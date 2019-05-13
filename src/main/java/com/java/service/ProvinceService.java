package com.java.service;

import com.java.domain.Province;

import java.util.List;

public interface ProvinceService {
    /**
     * 查询所有的省份
     */
    List<Province> finAll();

    String findAllJson();
}
