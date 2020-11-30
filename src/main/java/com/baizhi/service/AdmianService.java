package com.baizhi.service;

import com.baizhi.entity.Admain;

import java.util.HashMap;

public interface AdmianService {
    HashMap<String,Object> login(Admain admain, String code);
}
