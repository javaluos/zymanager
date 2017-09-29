package com.zy.cms.mapper.master;

import java.util.List;

import com.zy.cms.vo.BalanceUpdateRecord;
import com.zy.cms.vo.query.AccBalUpdateRecordQuery;

public interface BalanceUpdateRecordMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(BalanceUpdateRecord record);

    int insertSelective(BalanceUpdateRecord record);

    BalanceUpdateRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BalanceUpdateRecord record);

    int updateByPrimaryKey(BalanceUpdateRecord record);

	int selectCountByQuery(AccBalUpdateRecordQuery query);

	List<BalanceUpdateRecord> selectByQuery(AccBalUpdateRecordQuery query);
}