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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.SWESECTION.Entities.Course;
import com.SWESECTION.Entities.Game;
import com.SWESECTION.Entities.Student;
import com.SWESECTION.repository.CourseRepository;


@Controller
@SessionAttributes("id")
public class CourseController {
	@Autowired
	CourseRepository courserepo;
	
	@Autowired 
	private HttpSession session;
	
	
	@GetMapping("/courses")
	public ModelAndView showCoursesPage(Model model){
		
		//model.addAttribute("course",new Course());
		ModelAndView mv=new ModelAndView();
		mv.setViewName("courses");
		List<Course>courses=new ArrayList<>();
		courserepo.findAll().forEach(courses::add);
		mv.addObject("courses",courserepo.findAll());
		return mv;
	}
	@GetMapping("/create_course")
	public String showCreateCourseForm(Model model){
		model.addAttribute("course",new Course());
		//session.getAttribute("id");
		model.addAttribute("id",session.getAttribute("id"));
		return "create_course";
	}
	
	@PostMapping("/create_course")
	public String addTeacher(ModelMap model,@ModelAttribute Course course){
		System.out.println(course.getCoursename());
		System.out.println(course.getCoursecode());
		//course.setTeacher_id( (int) model.get("id"));
		course.setTeacherid((int) session.getAttribute("id"));
		courserepo.save(course);
		model.addAttribute("course",new Course());
		return "redirect:/courses";
	}
	
	@PostMapping("/delete_course")
	public String deleteCourse(@RequestParam("id") String id,ModelMap model){
		System.out.println("courseid="+id);
		courserepo.delete(Integer.parseInt(id));
		return "redirect:/courses";
	}

	@GetMapping("/choose_course")
	public ModelAndView chooseCourse(Model model){
		
		//model.addAttribute("course",new Course());
		ModelAndView mv=new ModelAndView();
		mv.setViewName("choose_course");
		/*List<Course>courses=new ArrayList<>();
		courserepo.findAll().forEach(courses::add);*/
		mv.addObject("courses",courserepo.findAll());
		return mv;
	}
	
	/*@GetMapping("/check_teacher")
	public String showTeacherLoginForm(Model model){
		model.addAttribute("teacher",new Teacher());
		return "teacher_login";
	}*/
	/*
	@PostMapping("/create")
	public String checkTeacher(Model model,@ModelAttribute Teacher teacher){
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
			return "teacher_home";
		}
		return null;
		
	}
*/

}
