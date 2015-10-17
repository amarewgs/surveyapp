package com.survey.model;

import java.util.List;

import com.survey.hibernate.model.Question;

public class QuestionSheet {

	private Question question;
	private Integer questionnaireId;
	private boolean isSelected;
	
	public Question getQuestion() {
	
		return question;
	}
	
	public void setQuestion(Question question) {
	
		this.question = question;
	}
	
	public Integer getQuestionnaireId() {
	
		return questionnaireId;
	}
	
	public void setQuestionnaireId(Integer questionnaireId) {
	
		this.questionnaireId = questionnaireId;
	}
	
	public boolean getIsSelected() {
	
		return isSelected;
	}
	
	public void setSelected(boolean isSelected) {
	
		this.isSelected = isSelected;
	}
	
}
