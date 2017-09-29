package com.zy.cms.service.manager;

import java.util.List;

import com.zy.cms.vo.Department;

public interface DepartmentService {

	List<Department> getAllDepartment();

	Department getById(Integer deptNo);

}
