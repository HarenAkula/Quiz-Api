package com.example.quizapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.entities.Question;
import com.example.quizapp.service.QuestionService;

@RestController
@RequestMapping(path= "/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;


	Logger logger = LoggerFactory.getLogger(QuestionController.class);


	/**
	 * Get Mapping to get all questions present in the database
	 * @return List<Question>
	 */
	@GetMapping(path= "/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		logger.info("serving a getAllQuestions request");
		return questionService.getAllQuestions();
	}

	/**
	 * Get Mapping to get a list of questions with the same category
	 * @param category - category of the questions
	 * @return List<Question>
	 */
	@GetMapping(path="/category/{category}")
	public List<Question> getQuestionByCategory(@PathVariable String category) {
		logger.info("serving a request to get all questions by category");
		return questionService.getQuestionByCategory(category);
	}

	/**
	 * Post Mapping to add a question to the database
	 * @param question - object of type Question to be added
	 * @return Http Status code and a response message
	 */
	@PostMapping(path="/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		logger.info("serving a post request to add a question");
		return questionService.addQuestion(question);
	};

	/**
	 * Delete Mapping to delete a question based to given id
	 * @param id - id of the question to be deleted
	 * @return String
	 */
	@DeleteMapping(path="/{id}")
	public String deleteById(@PathVariable int id) {
		logger.info("serving a delete request to delete a question");
		return questionService.deleteById(id);
	};

	/**
	 *Put Mapping to update a question category referenced by its ID
	 * @param id - id of the question to be updated
	 * @param category - the category to be set
	 * @return String
	 */
	@PutMapping(path="/update/{id}&{category}")
	public String updateQuestionCategory(@PathVariable int id, @PathVariable String category) {
		logger.info("serving a put request to update a method");
		return questionService.updateQuestionCategory(id, category);
	};
}
