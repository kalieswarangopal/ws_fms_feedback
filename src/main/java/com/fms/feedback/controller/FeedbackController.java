package com.fms.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fms.feedback.dto.FeedbackQuestionsResponseDTO;
import com.fms.feedback.entity.Feedback;
import com.fms.feedback.service.FeedbackService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;

	@GetMapping
	public Flux<FeedbackQuestionsResponseDTO> getFeedbackQuestions(@RequestParam String feedbackType) {

		return feedbackService.getFeedbackQuestions(feedbackType);

	}

	@PostMapping
	public ResponseEntity<Flux<Feedback>> saveFeedback(@RequestBody List<Feedback> feedback) {

		Flux<Feedback> response = feedbackService.saveFeedback(feedback);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
