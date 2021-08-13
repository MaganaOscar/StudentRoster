package com.oscar.studentroster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.oscar.studentroster.models.Classes;
import com.oscar.studentroster.models.Student;

public interface ClassesRepository extends CrudRepository<Classes, Long> {
	List<Classes> findByStudentsNotContains(Student student);
}
