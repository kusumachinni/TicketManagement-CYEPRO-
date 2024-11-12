package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Ticket;

public interface ITicketRepo extends JpaRepository<Ticket, Integer> {
	@Query(value="select count(*) from ticket where project_id=id",nativeQuery = true)
	public Integer countByProjectId(Integer id);
}
