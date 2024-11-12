package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Employee;

@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	
	// login 
	@Query(value="select * from employee e where e.employee_name=:employeeName",nativeQuery=true)
	public Employee findByName(@Param("employeeName")String employeeName);
	
	//availability of employee
	@Query(value="select * from Employee where is_employee_available=true",nativeQuery =true)
	public List<Employee> findByAvaliability();
	
}
