package com.example.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizapp.dao.QuestionDao;
import com.example.quizapp.entities.Question;

/**
 * Service class for the Question Controller
 */
@Service
public class QuestionService {

	Logger logger = LoggerFactory.getLogger(QuestionService.class);
	@Autowired
	QuestionDao questionDao;

	/**
	 * Service to return all questions
	 * @return ResponseEntity<List<Question>>
	 */
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
		return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error", e);

		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Service to get all questions by category
	 * @param category - requires the category of questions to be retrieved
	 * @return List<Question>
	 */
	public List<Question> getQuestionByCategory(String category) {
		return questionDao.findByCategory(category);
	}

	/**
	 * Service to add a new question to the database
	 * @param question - requires an object of type Question to be added
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> addQuestion(Question question) {
		try {
		questionDao.save(question);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Not able to add question", HttpStatus.BAD_REQUEST);
	}

	/**
	 *Service to delete a question based on its id
	 * @param id - requires id of the question to be deleted
	 * @return String
	 */
	public String deleteById(int id) {
		try {
			if (questionDao.existsById(id)) {
				questionDao.deleteById(id);
				return "question deleted";
			}
		} catch (Exception e) {
			logger.error("can't delete the question!!", e);
		}
		return "no such question";
		
	}

	/**
	 * Service to update category of  question referenced by its id
	 * @param id - requires id of the question to be updated
	 * @param category - the new category to be set
	 * @return String
	 */
	public String updateQuestionCategory(int id, String category) {
		Question q;
		if(questionDao.existsById(id)) {
			q = questionDao.getById(id);
			q.setCategory(category);
			questionDao.save(q);
			return "question updated ";
		}
		return "no such question";
	}

	
}
