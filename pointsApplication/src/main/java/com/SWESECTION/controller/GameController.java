package com.SWESECTION.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.SWESECTION.Entities.Course;
import com.SWESECTION.Entities.Game;
import com.SWESECTION.Entities.Question;
import com.SWESECTION.repository.CourseRepository;
import com.SWESECTION.repository.GameRepository;
import com.SWESECTION.repository.QuestionRepository;

import antlr.collections.List;


@Controller
public class GameController {

	@Autowired
	CourseRepository courserepo;
	
	@Autowired
	GameRepository gamerepo;
	
	@Autowired
	QuestionRepository questionrepo;
	
	@Autowired 
	private HttpSession session;
	
	
	@GetMapping("/create_game")
	public ModelAndView showCoursesPage(Model model){
		
		//model.addAttribute("course",new Course());
		ModelAndView mv=new ModelAndView();
		mv.setViewName("create_game");
		/*List<Course>courses=new ArrayList<>();
		courserepo.findAll().forEach(courses::add);*/
		mv.addObject("courses",courserepo.findAll());
		return mv;
	}
	
	
	@PostMapping("/create_game")
	public ModelAndView GotoHome(@RequestParam("coursename") String coursename,
			@RequestParam("gamename") String gamename,Model model){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("game_questions");
		System.out.println(coursename);
		//mv.addObject("user",u);
		
		Course course=courserepo.findBycoursename(coursename);
		System.out.println(course.getId());
		System.out.println(course.getCoursename());
		System.out.println(course.getCoursecode());
		System.out.println(course.getTeacherid());
		System.out.println("Teacher Id"+session.getAttribute("id"));
		Game gameObj=new Game(gamename,course.getId(),(int)session.getAttribute("id"));
		gamerepo.save(gameObj);
		Game game=gamerepo.findByGamename(gamename);
		session.setAttribute("game", game);
		return mv;
	}
	
	
	@PostMapping("/choose_game")
	public ModelAndView chooseGame(@RequestParam("coursename") String coursename,
			Model model){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("choose_game");
		Course course=courserepo.findBycoursename(coursename);
		System.out.println(course.getId());
		System.out.println(course.getCoursename());
		System.out.println(course.getCoursecode());
		System.out.println(course.getTeacherid());
		ArrayList<Game>Games=new ArrayList<Game>();
		System.out.println("Teacher Id"+session.getAttribute("id"));
		Games=gamerepo.findBycourseid(course.getId());
		mv.addObject("games",Games);
		return mv;
	}
	@PostMapping("/play_game")
	public ModelAndView play_game(@RequestParam("gamename") String gamename,
			Model model){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("answer_question");
		Game game=gamerepo.findBygamename(gamename);
		System.out.println(game.getId());
		System.out.println(game.getGamename());
		System.out.println(game.getTeacherid());
		ArrayList<Question>Questions=new ArrayList<Question>();
		Questions=questionrepo.findBygameid(game.getId());
		session.setAttribute("questions", Questions);
		session.setAttribute("qnum", 0);
		session.setAttribute("point", 0);
		mv.addObject("question",Questions.get(0));
		
		return mv;
	}
	
	
	
	
	
	
}
