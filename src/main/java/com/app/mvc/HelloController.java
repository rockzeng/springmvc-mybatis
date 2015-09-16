package com.app.mvc;

import com.app.model.User;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
@RequestMapping("/")
public class HelloController {

	@Autowired
	private UserService userService;


	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(Model model) {
		User user=userService.selectByPrimaryKey(1);
		model.addAttribute("message", "Hello world!"+user.getAtuEmail());
		return "hello";
	}

}