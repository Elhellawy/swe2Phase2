package com.SWESECTION.controller;

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

import com.SWESECTION.Entities.Teacher;
import com.SWESECTION.repository.TeacherRepository;

@Controller
@SessionAttributes("id")
public class TeacherController {

	@Autowired
	TeacherRepository repo;
	
	@Autowired 
	private HttpSession session;
	
	@GetMapping("/addteacher")
	public String showAddTeacherForm(Model model){
		model.addAttribute("teacher",new Teacher());
		return "teacher_signup";
	}
	
	
	@PostMapping("/addteacher")
	public String addTeacher(Model model,@ModelAttribute Teacher teacher){
		System.out.println(teacher.getName());
		System.out.println(teacher.getPassword());
		repo.save(teacher);
		model.addAttribute("teacher",new Teacher());
		return "index";
	}

	@GetMapping("/check_teacher")
	public String showTeacherLoginForm(Model model){
		model.addAttribute("teacher",new Teacher());
		if(session.getAttribute("id")!=null)
			return "teacher_home";
			return "teacher_login";
	}
	
	@PostMapping("/check_teacher")
	public String checkTeacher(ModelMap model,@ModelAttribute Teacher teacher){
		System.out.println(teacher.getName());
		System.out.println(teacher.getPassword());
		List<Teacher> check=repo.findBypasswordAndName(teacher.getPassword(), teacher.getName());
		System.out.println("check"+check.size());
		if(check.size()==0){
			System.out.println("Not Found");
			return "teacher_login";
		}
		if(check.size()!=0){
			System.out.println("found");
			session.setAttribute("id",check.get(0).getId());
			return "teacher_home";
		}
		return null;
		
	}

	
}
