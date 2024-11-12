package com.example.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organisation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer organisationId;
	@NonNull
	private String organisationName;
	@NonNull
	private String organisationPassword;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Project> projects;
	

	public void addProject(Project p) {projects.add(p);}
	public void removeProject(Project p) {projects.remove(p);};
	
	
	
	

//		
//		public void addProject(Project p) {projects.add(p);}
//		public void removeProject(Project p) {projects.remove(p);};
//	

}
