package com.defender.secureapp.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.defender.secureapp.model.Course;
import com.defender.secureapp.model.Student;

public interface StudentService extends UserDetailsService {

    // public register by a student
    public Student saveStudent(Student s);

    //admin method 
    public List<Student> saveAllStudents(List<Student> studentsList);

    // get enrolled courses
    public Set<Course> getEnrolledCourses(long studentId);

    public void unEnrollCourses(long studentId, long courseID);

    public void enrollCourses(long studentId, long courseID);

}
