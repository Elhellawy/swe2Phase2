package com.SWESECTION.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.SWESECTION.Entities.Course;
import com.SWESECTION.Entities.Game;

public interface GameRepository extends CrudRepository<Game, Integer> {
Game findByGamename(String gamename);

ArrayList<Game> findBycourseid(Integer id);

Game findBygamename(String gamename);
}
