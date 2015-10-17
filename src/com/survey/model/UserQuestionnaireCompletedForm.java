package com.survey.model;

import java.util.ArrayList;
import java.util.List;

import com.survey.hibernate.model.Question;
import com.survey.hibernate.model.User;

public class UserQuestionnaireCompletedForm {

	private UserAnswerSheetForm answerSheetForm;
	private Question question;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserAnswerSheetForm getAnswerSheetForm() {
		return answerSheetForm;
	}
	public void setAnswerSheetForm(UserAnswerSheetForm answerSheetForm) {
		this.answerSheetForm = answerSheetForm;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
}
