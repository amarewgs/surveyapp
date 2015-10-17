package com.survey.model;

public enum QuestionTypeEnum {

	YES_NO("Yes/No Answer"), MULTICHOICE("Multiple Select Answer"), LIST_CHOICE("Single Select Answer"), OPEN_ENDED("Open Ended Answer");

	private String type;

	private QuestionTypeEnum(String s) {

		type = s;
	}

	public String getType() {

		return type;
	}
}
