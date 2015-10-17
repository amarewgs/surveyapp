package com.survey.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class QuestionnaireIdsForm {

	@NotEmpty(message = "select one or more questionnaires")
	List<Integer> groupIds = new ArrayList<Integer>();

	public List<Integer> getGroupIds() {
		
		return groupIds;
	}

	public void setGroupIds(List<Integer> groupIds) {
		
		this.groupIds = groupIds;
	}
	
}
