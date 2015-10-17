package com.survey.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import com.survey.utils.UrlTool;


public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        // Get the role of logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        String targetUrl = "";
        if(role.contains("ROLE_USER")) {
            targetUrl = "redirect:/public/auth/userCampaigns";
        } else if(role.contains("ROLE_ADMIN")) {
            targetUrl = "redirect:/admin/index";
        }
        return targetUrl;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
    	
        // TODO Auto-generated method stub
    	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         String role = auth.getAuthorities().toString();

    	 String targetUrl = "";
    	 
         if(role.contains("ROLE_USER")) {
             targetUrl = "/public/auth/userCampaigns";
         } else if(role.contains("ROLE_ADMIN")) {
             targetUrl = "/admin/index";
         }
         getRedirectStrategy().sendRedirect(request, response, targetUrl);
        
    }
}