package com.survey.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionGroupForm {
	
	private List<QuestionForm> questionForms = new ArrayList<QuestionForm>();
	private String groupName;
	private String description;
			
	private Integer groupId;
	
	public Integer getGroupId() {
		return groupId;
	}


	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<QuestionForm> getQuestionForms() {
	
		return questionForms;
	}

	
	public void setQuestionForms(List<QuestionForm> questionForms) {
	
		this.questionForms = questionForms;
	}

	public String getGroupName() {
	
		return groupName;
	}
	
	public void setGroupName(String groupName) {
	
		this.groupName = groupName;
	}

}
