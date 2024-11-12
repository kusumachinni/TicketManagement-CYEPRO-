package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Organisation;
@EnableJpaRepositories
public interface OrganisationRepository extends JpaRepository<Organisation,Integer>{

	
	// getting data for login 
	@Query(value="select * from organisation o where o.organisation_name=:organisationName",nativeQuery=true)
	public Organisation findByName(@Param("organisationName")String organisationName);
}
