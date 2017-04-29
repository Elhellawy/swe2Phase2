package com.SWESECTION.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.SWESECTION.Entities.Score;
import com.SWESECTION.Entities.Student;
import com.SWESECTION.repository.ScoreRepository;
import com.SWESECTION.repository.StudentRepository;

@Controller
@SessionAttributes("name")
public class StudentController {
	@Autowired
	StudentRepository repo;
	@Autowired
	ScoreRepository scorerepo;
	
	@Autowired 
	private HttpSession session;
	
	@GetMapping("/addstudent")
	public String showAddStudenForm(Model model){
		model.addAttribute("student",new Student());
		return "student_signup";
	}
	
	
	@PostMapping("/addstudent")
	public String addStudent(Model model,@ModelAttribute Student student){
		System.out.println(student.getName());
		System.out.println(student.getStudentid());
		repo.save(student);
		model.addAttribute("student",new Student());
		return "student_signup";
	}

	@GetMapping("/check_student")
	public String showStudentLoginForm(Model model){
		model.addAttribute("student",new Student());
		if(session.getAttribute("studentid")!=null)
			return "student_home";
		return "student_login";
	}
	
	@PostMapping("/check_student")
	public String checkStudent(ModelMap model,@ModelAttribute Student student){
		System.out.println(student.getName());
		System.out.println(student.getStudentid());
		List<Student> check=repo.findBystudentidAndName(student.getStudentid(),student.getName());
		System.out.println("check"+check.size());
		if(check.size()==0){
			System.out.println("Not Found");
			return "student_login";
		}
		if(check.size()!=0){
			System.out.println("found");
			model.put("name", student.getName());
			session.setAttribute("studentid", check.get(0).getId());
			return "student_home";
		}
		return null;
		
	}

	
	@GetMapping("/profile")
	public ModelAndView profile(Model model){

		//model.addAttribute("course",new Course());
		ModelAndView mv=new ModelAndView();
		mv.setViewName("profile");
		ArrayList<Score>myscores= scorerepo.findBystudentid(session.getAttribute("studentid"));
		mv.addObject("myscores",myscores);
		return mv;
	}
	
}
