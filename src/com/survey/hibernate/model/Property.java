package com.survey.hibernate.model;

/**
 * Property entity. @author MyEclipse Persistence Tools
 */

public class Property implements java.io.Serializable {

	// Fields

	private Integer id;
	private String namespace;
	private String label;
	private String value;
	private String key;

	// Constructors

	/** default constructor */
	public Property() {
	}

	/** full constructor */
	public Property(String namespace, String label, String value, String key) {
		this.namespace = namespace;
		this.label = label;
		this.value = value;
		this.key = key;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNamespace() {
		return this.namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}