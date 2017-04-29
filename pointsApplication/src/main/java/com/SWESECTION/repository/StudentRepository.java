package com.SWESECTION.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.SWESECTION.Entities.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{
	
	List<Student> findBystudentidAndName(int studentid,String name);
	 // List<Student> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);
}
