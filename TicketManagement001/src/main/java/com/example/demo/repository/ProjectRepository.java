package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.model.Project;

@EnableJpaRepositories
public interface ProjectRepository extends JpaRepository<Project,Integer>{

}
