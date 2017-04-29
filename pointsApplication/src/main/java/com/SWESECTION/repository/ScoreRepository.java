package com.SWESECTION.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.SWESECTION.Entities.Score;

public interface ScoreRepository extends CrudRepository<Score, Integer>{

	ArrayList<Score> findBystudentid(Object attribute);

}
