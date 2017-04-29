package com.SWESECTION.repository;

import org.springframework.data.repository.CrudRepository;

import com.SWESECTION.Entities.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {

	Course findBycoursename(String coursename);

}
