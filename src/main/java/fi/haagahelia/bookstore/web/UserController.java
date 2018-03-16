package fi.haagahelia.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import fi.haagahelia.bookstore.domain.UserRepository;
import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.SignupForm;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/signup")
	public String addUser(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}
	
	// Create new user and check if user already exists
	
	@RequestMapping(value="/saveuser", method=RequestMethod.POST)
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
		// Validation errors
		System.out.println(signupForm.getUsername());
		if (!bindingResult.hasErrors()) { 
    		// Checking if passwords match
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { 		
	    		System.out.println(signupForm.getUsername());
				String password = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPassword = bc.encode(password);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPassword);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setEmail(signupForm.getEmail());
		    	newUser.setRole("USER");
		    	// Checking if user exists
		    	if (userRepository.findByUsername(signupForm.getUsername()) == null) { 
		    		userRepository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login"; 
	}
}
