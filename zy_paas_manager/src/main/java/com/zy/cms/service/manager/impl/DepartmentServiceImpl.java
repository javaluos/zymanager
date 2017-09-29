package com.zy.cms.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.cms.mapper.manager.DepartmentMapper;
import com.zy.cms.service.manager.DepartmentService;
import com.zy.cms.vo.Department;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentMapper mapper;
	
	@Override
	public List<Department> getAllDepartment() {
		return mapper.selectAll();
	}

	@Override
	public Department getById(Integer deptNo) {
		return mapper.selectByPrimaryKey(deptNo);
	}

}
