package com.zy.cms.mapper.master;

import java.util.List;

import com.zy.cms.vo.MobileOperator;

public interface MobileOperatorMapper {
    MobileOperator selectByPrimaryKey(MobileOperator key);
    List<MobileOperator> selectAll();
    List<String> selectAllMobileCity();
}
