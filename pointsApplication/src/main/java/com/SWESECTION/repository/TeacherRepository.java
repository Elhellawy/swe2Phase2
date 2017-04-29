package com.SWESECTION.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.SWESECTION.Entities.Student;
import com.SWESECTION.Entities.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
	List<Teacher> findBypasswordAndName(String password,String name);

}
