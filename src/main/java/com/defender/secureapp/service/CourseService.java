package com.defender.secureapp.service;

import java.util.List;

import com.defender.secureapp.model.Course;

public interface CourseService {

    public List<Course> getAllCourses();
    public List<Course> getCoursesBy(String name);
    public Course getCourseBy(long courseId);
    public Course getCourseBy(String abbriviation);
    public List<Course> saveCourses(List<Course> coursesList);
    public Course updateCourseBy(String abbriviation, Course course);
    public void deleteCourseBy(String abbriviation);
} 
    

