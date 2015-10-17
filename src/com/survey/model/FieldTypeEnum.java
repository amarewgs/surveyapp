package com.survey.model;

public enum FieldTypeEnum {

	TEXT("text"), DATE("date"), BOOLEAN("boolean"), NUMBER("number"), MULTI_SELECT("multi-select"), DROPDOWN("dropdown"), RADIO("radio"), MULTI_LINE("multi-line");

	private String type;

	private FieldTypeEnum(String s) {

		type = s;
	}

	public String getType() {

		return type;
	}
}
