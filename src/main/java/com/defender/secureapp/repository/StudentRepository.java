package com.defender.secureapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.defender.secureapp.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

    public Optional<Student> findByName(String name);
}
