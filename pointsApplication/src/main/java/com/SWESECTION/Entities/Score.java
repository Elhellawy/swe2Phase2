package com.SWESECTION.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Score {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private int studentid;
	private String gamename;
	private int point;
	private int totalpoints;
	
	public Score(){
		
	}
	public Score(int studentid, String gamename, int point, int totalpoints) {
		super();
		this.studentid = studentid;
		this.gamename = gamename;
		this.point = point;
		this.totalpoints = totalpoints;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public String getGamename() {
		return gamename;
	}
	public void setGamename(String gamename) {
		this.gamename = gamename;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getTotalpoints() {
		return totalpoints;
	}
	public void setTotalpoints(int totalpoints) {
		this.totalpoints = totalpoints;
	}
	

}
