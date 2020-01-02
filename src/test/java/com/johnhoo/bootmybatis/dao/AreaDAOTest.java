package com.johnhoo.bootmybatis.dao;

import com.johnhoo.bootmybatis.pojo.Area;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AreaDAOTest {
    @Autowired
    private AreaDAO areaDAO;

    @Test
    void queryArea() {
        List<Area> areaList = areaDAO.queryArea();
        assertEquals(2, areaList.size());
    }

    @Test
    void queryAreaById() {
        Area area = areaDAO.queryAreaById(1);
        assertEquals("高低压", area.getAreaName());
    }

    @Test
    void insertArea() {
        Area area = new Area();
        area.setAreaName("测试");
        area.setPriority(1);
        area.setCreateTime(LocalDateTime.now());
        area.setLastEditTime(LocalDateTime.now());
        int effectedNum = areaDAO.insertArea(area); //成功返回1，失败返回-1
        assertEquals(1, effectedNum);
    }

    @Test
    void updateArea() {
        Area area = new Area();
        area.setAreaName("材料工程");
        area.setAreaId(4);
        area.setLastEditTime(LocalDateTime.now());
        int effectedNum = areaDAO.updateArea(area); //成功返回1，失败返回-1
        assertEquals(1, effectedNum);
    }

    @Test
    void deleteArea() {
        int effectedNum = areaDAO.deleteArea(11);
        assertEquals(1, effectedNum);
    }
}