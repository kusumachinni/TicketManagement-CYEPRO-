package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Sprint;

public interface ISprintRepo extends JpaRepository<Sprint, Integer> {
	@Query(value="select count(*) from sprint where project_id=id",nativeQuery = true)
	public Integer countByProjectId(Integer id);
}
