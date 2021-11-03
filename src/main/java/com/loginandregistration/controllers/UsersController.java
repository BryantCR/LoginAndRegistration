package com.loginandregistration.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.loginandregistration.models.User;
import com.loginandregistration.service.UserService;


@Controller
public class UsersController {
	
	private final UserService userService;
	
	public UsersController( UserService usersService ) {
		this.userService = usersService;
	}
	
    @RequestMapping("/")
    public String Home(@ModelAttribute("user") User user) {
        return "redirect:/registration";
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerForm(@ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }
	
    @RequestMapping( value = "/registerUser", method = RequestMethod.POST )
	public String registerUser( @RequestParam(value="email") String email,
								@RequestParam(value="password") String password,
								@RequestParam(value="passwordConfirmation") String passwordConfirmation,
								RedirectAttributes redirectAttributes,
								HttpSession session) 
    {
    	
    	List<User> user = userService.getUserByEmail(email);
    	
    	if( user.size() > 0  ) {
			redirectAttributes.addFlashAttribute("errorMessage", "That user email already exists!");
			return "redirect:/registration";
		}
    	else {
			if( ! password.equals( passwordConfirmation ) ) {
				redirectAttributes.addFlashAttribute("errorMessage", "Password and password confirmation do not match");
				/*Integer  u = userService.registerUser(email, password);
		    	session.setAttribute("user_id", u);*/
				return "redirect:/registration";
			}
			else {
				System.out.println( email + " " + password );
				/*Integer  u = userService.registerUser(email, password);
		    	session.setAttribute("user_id", u);*/
				userService.registerUser(email, password );
				/*session.setAttribute( "firstName", firstname );
				session.setAttribute( "lastName", lastname );*/
				return "redirect:/login";
			}
		}
    	
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "loginPage.jsp";
    }
    
	@RequestMapping( value = "/validate/login", method = RequestMethod.POST )
	public String login( @RequestParam(value="email") String email,
						 @RequestParam(value="password") String password,
						 RedirectAttributes redirectAttributes,
						 Model model,
						 HttpSession session) {
		
		//List<User> user = userService.getUserByEmail(email);
		User current = userService.findByEmail(email);
		
		if( userService.validateUser(current, password) ) {
			User u = userService.findByEmail(email);
	    	session.setAttribute("user_id", u.getId());
	    	return "redirect:/home";
		}
		else {
	    	redirectAttributes.addFlashAttribute("lerrorMessage", "The password for this email is incorrect");
	    	return "redirect:/login";
		}
	}
	
	/*@RequestMapping( value = "/homeAlfredo", method = RequestMethod.GET )
	public String home2( HttpSession session, Model model ) {
		String firstName = (String) session.getAttribute("firstName");
		String lastName = (String) session.getAttribute("lastName");
		
		if( firstName != null && lastName != null ) {
			model.addAttribute("firstName", firstName );
			model.addAttribute("lastName", lastName );
			return "home.jsp";
		}
		else {
			return "redirect:/login";
		}
		
	}*/
	
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
    	
    	Long user_id =  (Long) session.getAttribute("user_id");
    	
    	User current = userService.findUserById(user_id);
    	
    	model.addAttribute("user", current );
    	return "homePage.jsp";
    }
    
	@RequestMapping( value = "/logout", method = RequestMethod.GET )
	public String logout( HttpSession session ) {
		/*session.removeAttribute("firstName");
		session.removeAttribute("lastName");*/
		session.removeAttribute("user_id");
		return "redirect:/login";
	}
	
    
}
