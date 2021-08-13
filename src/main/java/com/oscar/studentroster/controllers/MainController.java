package com.oscar.studentroster.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oscar.studentroster.models.Classes;
import com.oscar.studentroster.models.Contact;
import com.oscar.studentroster.models.Dorm;
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
	
	@RequestMapping("/dorms/new")
	public String newDorm(@ModelAttribute("dorm") Dorm dorm, BindingResult result) {
		return "newDorm.jsp";
	}
	
	@RequestMapping("/dorm/create")
	public String createDorm(@ModelAttribute("dorm") Dorm dorm, BindingResult result) {
		apiService.createDorm(dorm);
		return "redirect:/dorms/" + dorm.getId();
	}
	
	@RequestMapping("/dorms/create")
	public String createDormAPI(@RequestParam(value="name", required=false) String name) {
		Dorm dorm = new Dorm(name);
		apiService.createDorm(dorm);
		return "redirect:/dorms/" + dorm.getId();
	}
	
	@RequestMapping("/dorms/{id}")
	public String showDorm(@PathVariable("id") Long id, Model model) {
		Dorm dorm = apiService.getDormById(id);
		List<Student> students = apiService.getAllStudents();
		model.addAttribute("dorm", dorm);
		model.addAttribute("students", students);
		return "showDorm.jsp";
	}
	
	@RequestMapping("/dorms/{id}/add")
	public String addToDormAPI(@PathVariable("id") Long id, @RequestParam(value="student", required=false) Long studentID) {
		Dorm dorm = apiService.getDormById(id);
		apiService.addStudentByIdToDorm(studentID, dorm);
		return "redirect:/dorms/" + id;
	}
	
	@RequestMapping(value="/dorm/{id}/add", method=RequestMethod.POST)
	public String addToDorm(@PathVariable("id") Long id, @RequestParam("student") Long studentID) {
		Dorm dorm = apiService.getDormById(id);
		apiService.addStudentByIdToDorm(studentID, dorm);
		return "redirect:/dorms/" + id;
	}
	
	@RequestMapping("/dorms/{id}/remove")
	public String removeStudentFromDorm(@PathVariable("id") Long dormID, @RequestParam("student") Long studentID) {
		apiService.removeStudentByIdFromDorm(studentID);
		return "redirect:/dorms/" + dormID;
	}
	
	@RequestMapping("/classes/new")
	public String newClass(@ModelAttribute("class") Classes classes, BindingResult result) {
		return "newClass.jsp";
	}
	
	@RequestMapping(value="/class/create", method=RequestMethod.POST)
	public String createClass(@ModelAttribute("class") Classes classes, BindingResult result) {
		apiService.createClass(classes);
		return "redirect:/classes/new";
	}
	
	@RequestMapping("/classes/create")
	public String createClassAPI(@RequestParam(value="name", required=false) String name) {
		apiService.createClass(new Classes(name));
		return "redirect:/classes/new";
	}
	
	@RequestMapping("/students/{id}")
	public String showSchedule(@PathVariable("id") Long id, Model model) {
		Student student = apiService.getStudentById(id);
		List<Classes> classes = apiService.findClassesNotContainingStudent(student);
		model.addAttribute("classes", classes);
		model.addAttribute("student", student);
		return "showSchedule.jsp";
	}
	
	@RequestMapping(value="/students/add", method=RequestMethod.POST)
	public String addClassToStudent(@RequestParam("student") Long studentID, @RequestParam("class") Long classID) {
		Student student = apiService.getStudentById(studentID);
		Classes classes = apiService.getClass(classID);
		apiService.addClassToStudent(student, classes);
		return "redirect:/students/" + studentID;
	}
	
	@RequestMapping("/students/{id}/add")
	public String addClassToStudentAPI(@PathVariable("id") Long studentID,@RequestParam(value="class", required=false) Long classID) {
		Student student = apiService.getStudentById(studentID);
		Classes classes = apiService.getClass(classID);
		apiService.addClassToStudent(student, classes);
		return "redirect:/students/" + studentID;
	}
	
	@RequestMapping("/classes/{id}")
	public String showClass(@PathVariable("id") Long id, Model model) {
		Classes classes = apiService.getClass(id);
		model.addAttribute("classes", classes);
		return "showClass.jsp";
	}
}
