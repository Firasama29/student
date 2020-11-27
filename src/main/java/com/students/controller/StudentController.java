package com.students.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.students.dto.StudentDTO;
import com.students.web.rest.WebRestTemplate;

@Controller
public class StudentController extends RestTemplate{

	WebRestTemplate webRestTemplate = new WebRestTemplate();
	
	Logger log = LoggerFactory.getLogger(StudentController.class);
	
	//login
	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		
		return mav;
	}
	//register page
	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}
	
	//register
	@PostMapping("/register")
	public ModelAndView addStudent(@ModelAttribute StudentDTO studentDto) {
		ModelAndView mav = new ModelAndView("register");
		
		String url = "http://localhost:8081/addStudents";
		
		studentDto = webRestTemplate.postForObject(url, studentDto, StudentDTO.class);
		
		mav.addObject("studentDto", studentDto);
		
		log.info("studentDto " + studentDto);
		
		return mav;
	}
}
