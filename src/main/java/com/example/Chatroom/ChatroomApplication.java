package com.example.Chatroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

@SpringBootApplication
@RestController
public class ChatroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatroomApplication.class, args);
	}

	/**
	 * Login Page
	 */
	@GetMapping("/")
	public ModelAndView login() {
		return new ModelAndView("/login");
	}

	/**
	 * Chatroom Page
	 */
	@GetMapping("/chat")
	public ModelAndView index(String username, HttpServletRequest request) throws UnknownHostException {
		//TODO: add code for login to chatroom.
		System.out.println("ModelAndView: username="+ username);

		StringBuffer url =  request.getRequestURL();
		String wsURL = url.toString().replaceFirst("http:", "ws:") + "/";
		System.out.println("ModelAndView: wsURL="+ wsURL);

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/chat");  //had to change this from chat to /chat and add line in yml to get jar to work.
		modelAndView.addObject("username", username);
		modelAndView.addObject("wsURL", wsURL);

		return modelAndView;
	}

}
