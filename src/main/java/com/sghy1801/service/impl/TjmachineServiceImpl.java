package com.sghy1801.service.impl;

import com.sghy1801.dao.TjmachineMapper;
import com.sghy1801.entity.Tjmachine;
import com.sghy1801.service.TjmachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TjmachineServiceImpl implements TjmachineService {

    @Autowired
    private TjmachineMapper tjmachineMapper;


    //获取所有机器信息
    @Override
    public List<Tjmachine> getAllMachines(Map<String,Object> map) {

        List<Tjmachine> tjmachines = tjmachineMapper.getAllMachines(map);
        return tjmachineMapper.getAllMachines(map);
    }



    //新增机器
    @Override
    public int addMachine(Tjmachine tjmachine) {
        return tjmachineMapper.addMachine(tjmachine);
    }

    //更新机器信息
    @Override
    public int updateMachineInfo(Tjmachine tjmachine) {
        return tjmachineMapper.updateMachineInfo(tjmachine);
    }

    //删除机器
    @Override
    public int delMachine(int id) {

        return tjmachineMapper.delMachine(id);
    }

    //根据用户查找机器
    @Override
    public Tjmachine getOneByUserId(int userid) {
        return tjmachineMapper.getOneByUserId(userid);
    }
}
