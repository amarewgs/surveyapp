package com.survey.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;

import com.survey.dao.RoleDao;
import com.survey.dao.UserDao;
import com.survey.hibernate.model.Role;
import com.survey.hibernate.model.User;

public class RestAuthenticationProvider implements AuthenticationProvider {

	private static final Log LOGGER = LogFactory.getLog(RestAuthenticationProvider.class);

	public RestAuthenticationProvider() {

		super();
	}

	@Autowired(required = false)
	private UserDao userDao;

	@Autowired(required = false)
	private RoleDao roleDao;
	
	
	public void setRoleDao(RoleDao roleDao) {
	
		this.roleDao = roleDao;
	}

	public void setUserDao(UserDao userdao) {

		this.userDao = userdao;
	}

	
	@Autowired(required = false)
	private Md5PasswordEncoder passwordEncoder;

	public void setPasswordEncoder(Md5PasswordEncoder passwordEncoder) {

		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);

	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String userName = authentication.getName();
		String userPass = authentication.getCredentials().toString();

		// Credentials should not be null or blank      if( userName == null || userPass == null || userName.length() < 1 || userPass.length() < 1 )        {           throw new BadCredentialsException("Credential Missing !");      }

		// Fetch Roles And Generate Authorities         List<String> roles = userToken.getRoles();      // Add all the functions as well        roles.addAll(userToken.getFunctions());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		/*
		 * for (String role : roles) { authorities.add(new
		 * SimpleGrantedAuthority(role)); }
		 */

	    
		if (userName != null && !userName.isEmpty()) {
			
			User user = userDao.getUserByUsername(userName);

		    if(user == null){
		        throw new UsernameNotFoundException(String.format("Invalid credentials", authentication.getPrincipal()));
		    }
		    
		    if(!user.isActive()) {
		    	throw new BadCredentialsException("Invalid credentials");
		    }
		    
			if (passwordEncoder.encodePassword(userPass, userName).equals(user.getPassword())) {

				Role role = user.getRole();
				
				authorities.add(new SimpleGrantedAuthority(role.getName().equals("ROLE_USER") ? "ROLE_USER" : "ROLE_ADMIN"));

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, userPass, authorities);

				return usernamePasswordAuthenticationToken;
			} else {

				throw new BadCredentialsException("Invalid credentials");
			}
		} else {
			throw new AuthenticationCredentialsNotFoundException(userName);
		}

	}
}
