package com.survey.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.survey.dao.RoleDao;
import com.survey.dao.UserDao;
import com.survey.hibernate.model.Role;
import com.survey.hibernate.model.User;
import com.survey.model.UserRegistrationForm;
import com.survey.service.IUserManager;
import com.survey.utils.Constants;
import com.survey.utils.UserValidator;

@Controller
public class RegisterController {

	protected final Log	logger	= LogFactory.getLog(getClass());
		
	@Autowired(required = false)
	private UserDao	userDao;
	
	@Autowired(required = false)
	private RoleDao	roleDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@RequestMapping(value = Constants.PATH_PAGES_PUBLIC + "register")
	public String registerUser(HttpSession session, ModelMap model) throws ServletException, IOException {

		String now = (new Date()).toString();
		logger.info("Returning register  view with " + now);
			
		model.addAttribute("userForm",  session.getAttribute("userForm") != null ? session.getAttribute("userForm") : new UserRegistrationForm());
		model.put("org.springframework.validation.BindingResult.userForm", session.getAttribute("binding"));
				
		session.removeAttribute("userForm");
		session.removeAttribute("binding");
		
		return Constants.PATH_PAGES_PUBLIC + "register";

	}
	
    @RequestMapping(value = Constants.PATH_PAGES_PUBLIC + "/users/new", method = RequestMethod.POST)
    public String createUser(ModelMap model, @ModelAttribute("userForm") @Valid UserRegistrationForm userForm, HttpSession session, BindingResult result, SessionStatus status) {
        new UserValidator().validate(userForm, result);
        if (result.hasErrors()) {
        	session.setAttribute("userForm", userForm);
			session.setAttribute("binding",result);
			
            return "redirect:/public/register";
        } else { 
        	
        	User user = this.userDao.getUserByUsername(userForm.getUserName());
        	
        	if(user != null) {
        		
        		result.rejectValue("userName", "exists", "user name already exists!");
        		session.setAttribute("userForm", userForm);
    			session.setAttribute("binding",result);
        		return "redirect:/public/register";
        	} else {
        	
	        	Role role = roleDao.getRoleByName("ROLE_USER");
	        	
	        	user = new User();
	        	user.setFirstName(userForm.getFirstName());
	        	user.setLastName(userForm.getLastName());
	        	user.setEmail(userForm.getEmail());
	        	user.setRole(role);
	        	user.setUserName(userForm.getUserName());
	        	user.setPassword(userForm.getPassword());
	        	user.setActive(true);
	        	
	            this.userDao.createUser(user);
	            status.setComplete();
	            return "redirect:/public/signin";
        	}
        }
    }

}
