package com.example.quizapp.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.model.QuestionWrapper;
import com.example.quizapp.model.QuizResponses;
import com.example.quizapp.service.QuizService;

@RestController
@RequestMapping(path="/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;

	Logger logger = LoggerFactory.getLogger(QuizController.class);

	@GetMapping
	public String landing(Principal principal) {
		return "Welcome "+principal.getName() +" to my quiz Api!!";
	}
	/**
	 * Post Mapping to direct the create quiz requests
	 * @param category - denotes the category of questions to be added in quiz
	 * @param numQ - denotes the number of questions in the quiz
	 * @param title - denotes the title of the quiz
	 * @return returns "success" on successful creation of a quiz
	 */
	@PostMapping(path="/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
		logger.info("serving a post request to create a quiz");
		return quizService.createQuiz( category, numQ, title);
	}

	/**
	 * Get Mapping to send the list of quiz questions
	 * @param id - requires the id of the quiz to retrieve its questions
	 * @return returns a list of question in the quiz
	 */
	@GetMapping(path="/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
		logger.info("serving a get request to send quiz questions to the client");
		return quizService.getQuizQuestions(id);
		
	}

	/**
	 * Post Mapping to submit the quiz answers and get a score
	 * @param id - requires the id of the quiz
	 * @param quizResponses - requires a list of type QuizResponses
	 * @return returns the calculated score based on the responses given
	 */
	@PostMapping(path="/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizResponses> quizResponses) {
		logger.info("serving a post mapping to submit quiz responses from the client");
		return quizService.calculateResult(id, quizResponses);
	}
}
