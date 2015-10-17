package com.survey.dao;

import com.survey.hibernate.model.Property;

public interface PropertyDao {

	public void createProperty(Property property);
	public Property getProperty();
}
