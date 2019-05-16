package com.sghy1801.service;

import com.sghy1801.entity.Tjmachine;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TjmachineService {
    /**
     * 查询所有机器信息
     * @return
     */
    List<Tjmachine> getAllMachines(Map<String,Object> map);

    /**
     * 新增一个机器
     * @param tjmachine
     * @return
     */
    int addMachine(Tjmachine tjmachine);

    /**
     * 更改机器设备
     * @param tjmachine
     * @return
     */
    int updateMachineInfo(Tjmachine tjmachine);

    /**
     * 删除设备
     * @param id
     * @return
     */
    int delMachine(@Param("machineid") int id);

    /**
     * 根据用户查机器
     * @param userid
     * @return
     */
    Tjmachine getOneByUserId(@Param("userid") int userid);
}
