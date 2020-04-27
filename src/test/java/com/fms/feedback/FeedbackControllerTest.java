package com.fms.feedback;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.fms.feedback.controller.FeedbackController;
import com.fms.feedback.entity.Feedback;
import com.fms.feedback.repository.FeedbackRepository;
import com.fms.feedback.service.FeedbackService;

import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = FeedbackController.class)
@Import(FeedbackService.class)
public class FeedbackControllerTest {

	@Autowired
	WebTestClient webTestClient;

	@MockBean
	FeedbackRepository feedbackRepository;

	@MockBean
	DatabaseClient databaseClient;

	@Test
	public void testSaveFeedback() {

		Feedback feedback = new Feedback();
		feedback.setEventID("EVN2345");
		feedback.setEmployeeID(56432);
		feedback.setQuestionDescription("Reason for unregistration");
		feedback.setAnswerText("Personal work");
		feedback.setFeedbackType("Unregistered");
		List<Feedback> feedbackList = new ArrayList<>();
		feedbackList.add(feedback);

		Mockito.when(feedbackRepository.saveAll(feedbackList)).thenReturn(Flux.just(feedback));

		webTestClient.post().uri("/feedback").header(HttpHeaders.ACCEPT, "application/json")
				.body(BodyInserters.fromObject(feedbackList)).exchange().expectStatus().isCreated();

		Mockito.verify(feedbackRepository, times(1)).saveAll(feedbackList);

	}

	@Test
	public void testGetFeedbackQuestions() {

		webTestClient.get().uri(builder -> builder.path("/feedback").queryParam("feedbackType", "Unregistered").build())
				.header(HttpHeaders.ACCEPT, "application/json").exchange().expectStatus().is5xxServerError();

	}

}
