package com.SWESECTION.Entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Game {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String gamename;
	private int courseid;
	private int teacherid;
	public Game(){
		
	}
	
	public Game(String gamename, int courseid, int teacherid) {
		super();
		this.gamename = gamename;
		this.courseid = courseid;
		this.teacherid = teacherid;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGamename() {
		return gamename;
	}
	public void setGamename(String gamename) {
		this.gamename = gamename;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public int getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}
	
	
	
}
