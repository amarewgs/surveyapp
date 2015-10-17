package com.survey.model;

import java.util.List;

import com.survey.hibernate.model.Answer;


public class QuestionAnswer {
	
	private List<Answer> answers;

	
	public List<Answer> getAnswers() {
	
		return answers;
	}

	
	public void setAnswers(List<Answer> answers) {
	
		this.answers = answers;
	}
	

}
