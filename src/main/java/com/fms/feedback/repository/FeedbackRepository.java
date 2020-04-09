package com.fms.feedback.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fms.feedback.entity.Feedback;

public interface FeedbackRepository extends ReactiveCrudRepository<Feedback, Integer>{

}
