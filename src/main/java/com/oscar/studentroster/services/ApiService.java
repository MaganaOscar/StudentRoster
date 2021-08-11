package com.oscar.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oscar.studentroster.models.Contact;
import com.oscar.studentroster.models.Student;
import com.oscar.studentroster.repositories.ContactRepository;
import com.oscar.studentroster.repositories.StudentRepository;

@Service
public class ApiService {
	private final ContactRepository conRepo;
	private final StudentRepository stuRepo;
	
	public ApiService(ContactRepository conRepo, StudentRepository stuRepo) {
		this.conRepo = conRepo;
		this.stuRepo = stuRepo;
	}
	
	public List<Student> getAllStudents() {
		return stuRepo.findAll();
	}
	
	public List<Contact> getAllContacts() {
		return conRepo.findAll();
	}
	
	public Student createStudent(Student student) {
		return stuRepo.save(student);
	}
	
	public Contact createContact(Contact contact) {
		return conRepo.save(contact);
	}
	
	public Student getStudentById(Long id) {
		Optional<Student> optionalStudent = stuRepo.findById(id);
		if(optionalStudent.isPresent()) {
			return optionalStudent.get();
		} else {
			return null;
		}
	}
}
