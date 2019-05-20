package com.sghy1801.service.impl;

import com.sghy1801.dao.ShopMapper;
import com.sghy1801.entity.meituan.Shop;
import com.sghy1801.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper ShopMapper;

    @Override
    public List<Shop> getAlllist() {
        return ShopMapper.getAlllist();
    }
}
