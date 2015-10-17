package com.survey.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UserAnswerForm {

	private Integer userAnswerId;
	private List<Integer> answerIds;
	private Integer questionId;
	private Boolean booleanAnswer;
	private String comment;
	private String detailAnswer;
	private MultipartFile file;
	public List<Integer> getAnswerIds() {
		return answerIds;
	}
	public void setAnswerIds(List<Integer> answerIds) {
		this.answerIds = answerIds;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Integer getUserAnswerId() {
		return userAnswerId;
	}
	public void setUserAnswerId(Integer userAnswerId) {
		this.userAnswerId = userAnswerId;
	}
}
