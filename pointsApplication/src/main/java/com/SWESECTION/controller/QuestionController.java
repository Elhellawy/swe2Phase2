package com.SWESECTION.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.SWESECTION.Entities.Game;
import com.SWESECTION.Entities.Question;
import com.SWESECTION.Entities.Score;
import com.SWESECTION.repository.CourseRepository;
import com.SWESECTION.repository.GameRepository;
import com.SWESECTION.repository.QuestionRepository;
import com.SWESECTION.repository.ScoreRepository;

@Controller
public class QuestionController {
	@Autowired
	CourseRepository courserepo;
	
	@Autowired
	GameRepository gamerepo;
	
	@Autowired
	QuestionRepository questionrepo;
	
	@Autowired
	ScoreRepository scorerepo;
	
	@Autowired 
	private HttpSession session;
	
	@PostMapping("/anotherquestion")
	public ModelAndView anotherQuestion(
			@RequestParam("question") String question,
			@RequestParam("answer1") String answer1,
			@RequestParam("answer2")  String answer2,
			@RequestParam("answer3") String answer3,
			@RequestParam("correct") String correct,Model model){
		
	
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("game_questions");
		Game game=(Game) session.getAttribute("game");
		Question questionobj=new Question(game.getId(),question,answer1,
				answer2,answer3,correct);
		questionrepo.save(questionobj);
		return mv;
	}
	
	public Boolean checkCorrect(String Answer,String CorrectAnswer)
	{
		if(Answer.equals(CorrectAnswer))
			return true;
		return false;
	}
	
	@PostMapping("/nextquestion")
	public ModelAndView nextQuestion(
			@RequestParam("answer") String answer,
			Model model){
	
		
		ModelAndView mv=new ModelAndView();
		ArrayList<Question>questions=new ArrayList<Question>();
		questions=(ArrayList<Question>) session.getAttribute("questions");
		int questionNum=(int) session.getAttribute("qnum");
		if(checkCorrect(answer, questions.get(questionNum).getCorrect()))
		{
			int point=(int) session.getAttribute("point");
			point+=1;
			session.setAttribute("point", point);
		}
		
		questionNum+=1;
		if(questionNum!=questions.size()){
			mv.setViewName("answer_question");
		mv.addObject("question",questions.get(questionNum));
		session.setAttribute("qnum", questionNum);
		return mv;}
		else{
			mv.setViewName("game_finished");
			Game gameObj=new Game();
			gameObj=gamerepo.findOne(questions.get(0).getGameid());
			Score scoreobj=new Score((int)session.getAttribute("studentid"),
					gameObj.getGamename(),
					(int) session.getAttribute("point"),
					questions.size()
					);
			scorerepo.save(scoreobj);
			mv.addObject("finished","game ended And your Score"+
			Integer.toString((int) session.getAttribute("point"))+"/"
			+Integer.toString(questions.size()));
		}
		return mv;
	}
	
	

}
