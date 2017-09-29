package com.zy.cms.mapper.master;

import java.util.List;

import com.zy.cms.vo.SysParam;

public interface SysParamMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SysParam record);

    int insertSelective(SysParam record);

    SysParam selectByPrimaryKey(Integer id);

    List<SysParam> selectAll();

    int updateByPrimaryKeySelective(SysParam record);

    int updateByPrimaryKey(SysParam record);
    
    List<SysParam> selectByType(String paramType); 
}
