package com.oscar.studentroster.repositories;

import org.springframework.data.repository.CrudRepository;

import com.oscar.studentroster.models.Dorm;

public interface DormRepository extends CrudRepository<Dorm, Long> {

}
