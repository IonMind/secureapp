package com.defender.secureapp.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.defender.secureapp.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long>{
    public Optional<List<Course>> findByNameContaining(String name);
    public Optional<Course> findByAbbriviation(String abbriviation); //unique key
    public int deleteByAbbriviation(String abbriviation);
    public Set<Course> findAllByOrderByIdAsc();
}
