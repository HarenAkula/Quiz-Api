package com.example.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quizapp.entities.Quiz;

/**
 * Data access object for the quiz entity
 */
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
