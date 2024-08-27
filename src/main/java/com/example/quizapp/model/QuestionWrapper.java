package com.example.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the question model that will be stored as part of a quiz and sent upon client request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {

	private int id;
	private String questionTitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
}
