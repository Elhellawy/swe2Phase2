package com.SWESECTION.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.SWESECTION.Entities.Game;
import com.SWESECTION.Entities.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer>{

	ArrayList<Question> findBygameid(Integer id);

}
