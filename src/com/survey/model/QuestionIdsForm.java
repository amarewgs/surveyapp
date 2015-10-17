package com.survey.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;


public class QuestionIdsForm {
	
	@NotEmpty(message = "select one or more questions")
	List<Integer> questionIds = new ArrayList<Integer>();

	public List<Integer> getQuestionIds() {
		
		return questionIds;
	}

	public void setQuestionIds(List<Integer> questionIds) {
		
		this.questionIds = questionIds;
	}

}
