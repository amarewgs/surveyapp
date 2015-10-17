package com.survey.hibernate.model;

/**
 * AssetAttribute entity. @author MyEclipse Persistence Tools
 */

public class AssetAttribute implements java.io.Serializable {

	// Fields

	private Integer id;
	private Asset asset;
	private Section section;
	private Attribute attribute;
	private String value;

	// Constructors

	/** default constructor */
	public AssetAttribute() {
	}

	/** full constructor */
	public AssetAttribute(Asset asset, Section section, Attribute attribute,
			String value) {
		this.asset = asset;
		this.section = section;
		this.attribute = attribute;
		this.value = value;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
}