package com.fms.feedback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackQuestionsResponseDTO {
	
	private Integer questionid;
	private String questiondescription;
	private Integer answerid;
	private String answertext;
	private String answertype;

}
