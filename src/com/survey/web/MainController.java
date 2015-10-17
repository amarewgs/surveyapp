package com.survey.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.survey.utils.Constants;

@Controller
public class MainController {

	protected final Log	logger	= LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/")
	public String index() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        String targetUrl = "/public/signin";
        if(role.contains("ROLE_USER")) {
            targetUrl = "/public/auth/user";
        } else if(role.contains("ROLE_ADMIN")) {
            targetUrl = "/admin/index";
        }else{
        	targetUrl = "/public/signin";
        }
        return targetUrl;
	}
	
	@RequestMapping(value = Constants.PATH_PAGES_AUTH + Constants.USER)
	public String home() {
		
		return "public/auth/user";
	}

	@RequestMapping(value = "/authentication/loginpage/{error}", method = RequestMethod.GET)	 
	public final String displayLoginform(Model model,  @PathVariable final String error) {
	    model.addAttribute("error", error);
	    return "redirect:/public/signin";
	}
	
	@RequestMapping(value = "/logout")
	public String logout() {

		SecurityContextHolder.clearContext();

        String targetUrl = "/public/signin";
        
        return targetUrl;
	}
}
