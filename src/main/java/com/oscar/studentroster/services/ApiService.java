package com.oscar.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oscar.studentroster.models.Classes;
import com.oscar.studentroster.models.Contact;
import com.oscar.studentroster.models.Dorm;
import com.oscar.studentroster.models.Student;
import com.oscar.studentroster.repositories.ClassesRepository;
import com.oscar.studentroster.repositories.ContactRepository;
import com.oscar.studentroster.repositories.DormRepository;
import com.oscar.studentroster.repositories.StudentRepository;

@Service
public class ApiService {
	private final ContactRepository conRepo;
	private final StudentRepository stuRepo;
	private final DormRepository dormRepo;
	private final ClassesRepository clsRepo;
	
	public ApiService(ContactRepository conRepo, StudentRepository stuRepo, 
			DormRepository dormRepo, ClassesRepository clsRepo) {
		this.conRepo = conRepo;
		this.stuRepo = stuRepo;
		this.dormRepo = dormRepo;
		this.clsRepo = clsRepo;
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
	
	public Dorm createDorm(Dorm dorm) {
		return dormRepo.save(dorm);
	}
	
	public Dorm getDormById(Long id) {
		Optional<Dorm> optionalDorm = dormRepo.findById(id);
		if(optionalDorm.isPresent()) {
			return optionalDorm.get();
		} else {
			return null;
		}
	}
	
	public Student getStudentById(Long id) {
		Optional<Student> optionalStudent = stuRepo.findById(id);
		if(optionalStudent.isPresent()) {
			return optionalStudent.get();
		} else {
			return null;
		}
	}
	
	public Student addStudentByIdToDorm(Long id, Dorm dorm) {
		Student editStu = getStudentById(id);
		editStu.setDorm(dorm);
		return stuRepo.save(editStu);
		
	}
	
	public Student removeStudentByIdFromDorm(Long id) {
		Student editStu = getStudentById(id);
		editStu.setDorm(null);
		return stuRepo.save(editStu);
	}
	
	public Classes createClass(Classes classes) {
		return clsRepo.save(classes);
	}
	
	public Classes getClass(Long id) {
		Optional<Classes> optionalClass = clsRepo.findById(id);
		if(optionalClass.isPresent()) {
			return optionalClass.get();
		} else {
			return null;
		}
	}
	
	public List<Classes> findClassesNotContainingStudent(Student student) {
		return clsRepo.findByStudentsNotContains(student);
	}
	
	public Student addClassToStudent(Student student, Classes classes) {
		student.getClasses().add(classes);
		return stuRepo.save(student);
	}
	
}
