package com.survey.model;

import java.util.ArrayList;
import java.util.List;

import com.survey.hibernate.model.Answer;
import com.survey.hibernate.model.Question;

public class UserAnswerSheetForm {
	
	private Integer userAnswerId;
	private Boolean isSelected;
	private Boolean booleanAnswer;
	private String comment;
	private String detailAnswer;
	private Integer userId;
	private List<Answer> chosenAnswers = new ArrayList<Answer>();	
	private List<Answer> answers = new ArrayList<Answer>();
	private String attachmentPath;	
	
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	public List<Answer> getChosenAnswers() {
		return chosenAnswers;
	}
	public void setChosenAnswers(List<Answer> chosenAnswers) {
		this.chosenAnswers = chosenAnswers;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	public Boolean getBooleanAnswer() {
		return booleanAnswer;
	}
	public void setBooleanAnswer(Boolean booleanAnswer) {
		this.booleanAnswer = booleanAnswer;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDetailAnswer() {
		return detailAnswer;
	}
	public void setDetailAnswer(String detailAnswer) {
		this.detailAnswer = detailAnswer;
	}
	public Integer getUserAnswerId() {
		return userAnswerId;
	}
	public void setUserAnswerId(Integer userAnswerId) {
		this.userAnswerId = userAnswerId;
	}
	
}
