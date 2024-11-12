package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Project;

public interface IProjectRepo extends JpaRepository<Project, Integer> {
	@Query(value="select count(*) from project_employees where project_project_id=:id",nativeQuery = true)
	public Integer countOfEmployeeWithProject(Integer id);
	
	@Query(value="select count(*) from project_tickets where project_project_id=:id",nativeQuery = true)
	public Integer countOfTicketWithProject(Integer id);
	
	@Query(value="select count(*) from project_sprints where project_project_id=:id",nativeQuery = true)
	public Integer countOfSprintWithProject(Integer id);
}
