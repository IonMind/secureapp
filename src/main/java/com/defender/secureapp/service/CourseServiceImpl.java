package com.defender.secureapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.defender.secureapp.ExceptionHandler.NoValuePresentException;
import com.defender.secureapp.model.Course;
import com.defender.secureapp.repository.CourseRepository;

import jakarta.transaction.Transactional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepo;

    @Override
    public List<Course> getCoursesBy(String name) {
        return courseRepo.findByNameContaining(name).orElse(new ArrayList<>());
    }

    @Override
    public List<Course> saveCourses(List<Course> coursesList) {

       return courseRepo.saveAll(coursesList);

    }

    @Override
    public Course updateCourseBy(String abbriviation, Course course) {
        Course courseToUpdate = this.getCourseBy(abbriviation);
        courseToUpdate.setName(course.getName());
        courseToUpdate.setPrice(course.getPrice());
        courseToUpdate.setAbbriviation(course.getAbbriviation());
        return courseRepo.save(courseToUpdate);
    }

    @Override
    @Transactional
    public void deleteCourseBy(String abbriviation) {
        Course course = courseRepo.findByAbbriviation(abbriviation).orElseThrow();
        // course.getStudents().clear();
        courseRepo.delete(course);

    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAllByOrderByIdAsc();
    }

    @Override
    public Course getCourseBy(long courseId) {
       return courseRepo.findById(courseId).orElseThrow(() -> new NoValuePresentException("no course associated to student with id : "+courseId));
    }

    @Override
    public Course getCourseBy(String abbriviation) {
        return  courseRepo.findByAbbriviation(abbriviation)
        .orElse(null);
    }

   
}
