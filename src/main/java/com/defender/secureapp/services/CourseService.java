package com.defender.secureapp.services;

import java.util.List;
import java.util.Set;

import com.defender.secureapp.model.Course;

public interface CourseService {

    public Set<Course> getAllCourses();
    public List<Course> getCoursesBy(String name);
    public Course getCourseBy(long courseId);
    public Course getCourseBy(String abbriviation);
    public List<Course> saveCourses(List<Course> coursesList);
    public Course updateCourseBy(String abbriviation, Course course);
    public void deleteCourseBy(String abbriviation);
} 
    

