package com.survey.dao;

import java.util.List;

import com.survey.hibernate.model.Role;


public interface RoleDao {

	public Role getRoleById(Integer roleId);
	public List<Role> getRoles();
	public Role getRoleByName(String role);
	public void createRole(Role role);
}
