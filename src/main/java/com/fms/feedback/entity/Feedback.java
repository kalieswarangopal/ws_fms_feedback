package com.fms.feedback.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("feedback")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

	@Id
	@Column("FeedbackID")
	private Integer feedbackID;

	@Column("EventID")
	private String eventID;

	@Column("EmployeeID")
	private Integer employeeID;
	
	@Column("FeedbackType")
	private String feedbackType;

	@Column("QuestionDescription")
	private String questionDescription;

	@Column("AnswerText")
	private String answerText;

}
