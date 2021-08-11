package com.oscar.studentroster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.oscar.studentroster.models.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
	List<Student> findAll();
}
