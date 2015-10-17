package com.survey.dao;

import java.util.List;

import com.survey.hibernate.model.Target;

public interface TargetDao {
	
	public void createTarget(Target target);
	public Target getTragetById(Integer id);
	public Target getTargetByName(String name);
	
	public List<Target> getAllTargets();
}
