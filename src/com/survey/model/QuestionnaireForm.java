package com.survey.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.survey.hibernate.model.Question;


public class QuestionnaireForm {
	
	@NotEmpty(message = "select one or more questions")
	List<Integer> questionIds = new ArrayList<Integer>();
	@NotEmpty(message = "category can not be empty")
	private String categoryName;
	private String description;
	private String title;
	private boolean active;
	private Integer questionSize;
	private List<Question> questions = new ArrayList<Question>();
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

	public List<Integer> getQuestionIds() {
	
		return questionIds;
	}
	
	public void setQuestionIds(List<Integer> questionIds) {
	
		this.questionIds = questionIds;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
