package com.example.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This model represents the responses to the quiz from the client
 */
@Data
@AllArgsConstructor
public class QuizResponses {
	private Integer id;
	private String response;

}
