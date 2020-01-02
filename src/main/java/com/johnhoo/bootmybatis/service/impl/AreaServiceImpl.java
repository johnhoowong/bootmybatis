package com.johnhoo.bootmybatis.service.impl;

import com.johnhoo.bootmybatis.dao.AreaDAO;
import com.johnhoo.bootmybatis.pojo.Area;
import com.johnhoo.bootmybatis.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDAO areaDAO;

    @Override
    public List<Area> getAreaList() {
        return areaDAO.queryArea();
    }

    @Override
    public Area getAreaById(int areaId) {
        return areaDAO.queryAreaById(areaId);
    }

    @Transactional
    @Override
    public boolean addArea(Area area) {
        if((area.getAreaName() != null) && (!"".equals(area.getAreaName()))) {
            area.setCreateTime(LocalDateTime.now());
            area.setLastEditTime(LocalDateTime.now());
            try {
                int effectedNum = areaDAO.insertArea(area);
                if(effectedNum > 0){
                    return true;
                }else {
                    throw new RuntimeException("添加事业部信息失败!");
                }
            }catch (Exception e) {
                throw new RuntimeException("添加事业部信息失败!" + e.toString());
            }
        }else {
            throw new RuntimeException("事业部名称不能为空!");
        }
    }

    @Transactional
    @Override
    public boolean modifyArea(Area area) {
        // 空值判断，主要是areaId不为空
        if (area.getAreaId() != null && area.getAreaId() > 0) {
            // 设置默认值
            area.setLastEditTime(LocalDateTime.now());
            try {
                // 更新区域信息
                int effectedNum = areaDAO.updateArea(area);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("更新事业部信息失败!");
                }
            } catch (Exception e) {
                throw new RuntimeException("更新事业部信息失败:" + e.toString());
            }
        } else {
            throw new RuntimeException("事业部编号不能为空！");
        }
    }

    @Transactional
    @Override
    public boolean deleteArea(int areaId) {
        if (areaId > 0) {
            try {
                // 删除事业部信息
                int effectedNum = areaDAO.deleteArea(areaId);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("删除事业部信息失败!");
                }
            } catch (Exception e) {
                throw new RuntimeException("删除事业部信息失败:" + e.toString());
            }
        } else {
            throw new RuntimeException("事业部编号不能为空！");
        }
    }
}
