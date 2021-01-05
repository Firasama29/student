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
	
	//login page
	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		
		return mav;
	}
	//addStudent page
	@GetMapping("/addStudent")
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("addStudent");
		return mav;
	}
	
	//add a student
	@PostMapping("/addStudent")
	public ModelAndView addStudent(@ModelAttribute StudentDTO studentDto) {
		ModelAndView mav = new ModelAndView("addStudent");
		
		String url = "http://localhost:8082/students";
		
		studentDto = webRestTemplate.postForObject(url, studentDto, StudentDTO.class);
		
		mav.addObject("studentDto", studentDto);
		
		log.info("studentDto " + studentDto);
		
		return mav;
	}
	
	//login
	@PostMapping("login")
	public ModelAndView loginStudent(@ModelAttribute StudentDTO studentDto){
		ModelAndView mav = new ModelAndview("login");
		
		String url = "http://localhost:8082/students";
		
		mav.addObject("studentDto", studentDto);
		
		log.info("studentDto " + studentDto);
		
		return mav;
	}
	
	//update student
	@Get
}
