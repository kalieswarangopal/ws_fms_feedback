package com.fms.feedback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;

import com.fms.feedback.dto.FeedbackQuestionsResponseDTO;
import com.fms.feedback.entity.Feedback;
import com.fms.feedback.repository.FeedbackRepository;

import reactor.core.publisher.Flux;

@Service
public class FeedbackService {

	@Autowired
	DatabaseClient databaseClient;

	@Autowired
	FeedbackRepository feedbackRepository;

	public Flux<FeedbackQuestionsResponseDTO> getFeedbackQuestions(String feedbackType) {

		Flux<FeedbackQuestionsResponseDTO> response = databaseClient.execute().sql(
				"select q.questionid, q.questiondescription, q.answertype, a.answerid, a.answertext from question q left join answer a on q.questionid=a.questionid where q.feedbacktype = :feedbackType")
				.bind("feedbackType", feedbackType).as(FeedbackQuestionsResponseDTO.class).fetch().all();
		return response;
	}

	public Flux<Feedback> saveFeedback(List<Feedback> feedback) {

		return feedbackRepository.saveAll(feedback);
	}

}
