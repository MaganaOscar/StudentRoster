package com.oscar.studentroster.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oscar.studentroster.models.Contact;
import com.oscar.studentroster.models.Student;
import com.oscar.studentroster.services.ApiService;

@Controller
public class MainController {
	private final ApiService apiService;
	
	public MainController(ApiService apiService) {
		this.apiService = apiService;
	}
	
	@RequestMapping("/students")
	public String showStudents(Model model) {
		model.addAttribute("contacts", apiService.getAllContacts());
		return "showStudents.jsp";
	}
	@RequestMapping("/students/new")
	public String newStudent(@ModelAttribute("student") Student student, BindingResult result) {
		return "newStudent.jsp";
	}
	
	@RequestMapping("/student/create")
	public String createStudent(@ModelAttribute("student") Student student, BindingResult result) {
		return String.format("redirect:/students/create?firstName=%s&lastName=%s&age=%s", student.getFirstName(),
				student.getLastName(), student.getAge());
	}
	
	@RequestMapping("/contact/new")
	public String newContact(Model model) {
		model.addAttribute("students", apiService.getAllStudents());
		return "newContact.jsp";
	}
	
	@RequestMapping("/contact/create")
	public String createContact(@RequestParam("student") String id, @RequestParam("address") String address,
			@RequestParam("city") String city, @RequestParam("state") String state) {
		
		return String.format("redirect:/contacts/create?student=%s&address=%s&city=%s&state=%s", id, address, city, state);
	}
	
	@RequestMapping("/contacts/create")
	public String createContactAPI(@RequestParam(value="student",required=false) Long id, @RequestParam(value="address",required=false) String address,
			@RequestParam(value="city",required=false) String city, @RequestParam(value="state",required=false) String state) {
		Student stu = apiService.getStudentById(id);
		apiService.createContact(new Contact(address, city, state, stu));
		return "redirect:/students";
	}
	
	@RequestMapping("/students/create")
	public String createStudentAPI(@RequestParam(value="firstName", required=false) String firstName,
			@RequestParam(value="lastName", required=false) String lastName,
			@RequestParam(value="age", required=false) int age) {
		
		apiService.createStudent(new Student(firstName, lastName, age));
		return "redirect:/contact/new";
	}
}
