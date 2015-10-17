package com.survey.model;

import java.util.List;

import com.survey.hibernate.model.Answer;
import com.survey.hibernate.model.Question;

public class QuestionForm {
	
	private Question question;
	private List<Answer> answers;
	private String category;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
}
