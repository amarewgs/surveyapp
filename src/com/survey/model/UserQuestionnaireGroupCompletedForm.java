package com.survey.model;

import java.util.ArrayList;
import java.util.List;

public class UserQuestionnaireGroupCompletedForm {

	private List<UserQuestionnaireCompletedForm> userQuestionnaireCompletedForms = new ArrayList<UserQuestionnaireCompletedForm>();
	private String categoryName;
	public List<UserQuestionnaireCompletedForm> getUserQuestionnaireCompletedForms() {
		return userQuestionnaireCompletedForms;
	}
	public void setUserQuestionnaireCompletedForms(
			List<UserQuestionnaireCompletedForm> userQuestionnaireCompletedForms) {
		this.userQuestionnaireCompletedForms = userQuestionnaireCompletedForms;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	private String categoryDescription;
}
