package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Employee;

public interface IEmployeeRepo extends JpaRepository<Employee, Integer>{
	@Query(value="select * from Employee where is_employee_avaliable=true",nativeQuery =true)
	public List<Employee> findByAvaliability();
	
	@Query(value="select count(*) from employee where project_id=id",nativeQuery = true)
	public Integer countByProjectId(Integer id);
}
