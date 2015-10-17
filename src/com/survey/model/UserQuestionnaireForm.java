package com.survey.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.survey.hibernate.model.Question;


public class UserQuestionnaireForm {
	
	private String categoryName;
	private String description;
	private String title;
	private Integer questionSize;
	private Integer questionCompletedSize;
	private Integer remainingQuestions;
	private List<Question> questions = new ArrayList<Question>();
	private List<Question> questionsCompleted = new ArrayList<Question>();
	
	private Integer groupId;
	
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Integer getQuestionSize() {
		return questionSize;
	}

	public void setQuestionSize(Integer questionSize) {
		this.questionSize = questionSize;
	}

	public String getCategoryName() {
	
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
	
		this.categoryName = categoryName;
	}
	
	public String getTitle() {
	
		return title;
	}
	
	public void setTitle(String title) {
	
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Question> getQuestionsCompleted() {
		return questionsCompleted;
	}

	public void setQuestionsCompleted(List<Question> questionsCompleted) {
		this.questionsCompleted = questionsCompleted;
	}
	public Integer getQuestionCompletedSize() {
		return questionCompletedSize;
	}

	public void setQuestionCompletedSize(Integer questionCompletedSize) {
		this.questionCompletedSize = questionCompletedSize;
	}
	
	
	public Integer getRemainingQuestions() {
		return remainingQuestions;
	}

	public void setRemainingQuestions(Integer remainingQuestions) {
		this.remainingQuestions = remainingQuestions;
	}

}
