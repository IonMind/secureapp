package com.defender.secureapp.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.defender.secureapp.model.Course;
import com.defender.secureapp.services.StudentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    
    @GetMapping("/{studentId}/getCourses")
    public ResponseEntity<Set<Course>> getAllCourses(@PathVariable long studentId) {
        return new ResponseEntity<Set<Course>>(studentService.getEnrolledCourses(studentId),HttpStatus.OK);
    }

    @PutMapping("/{studentId}/course/unenroll/{courseId}")
    public ResponseEntity<String> unenrollCourse(@PathVariable long studentId,  @PathVariable long courseId) {
        studentService.unEnrollCourses(studentId, courseId);
        return new ResponseEntity<String>("Course unenrolled", HttpStatus.OK);
    }
    
    @PutMapping("/{studentId}/course/enroll/{courseId}")
    public ResponseEntity<String> enrollCourse(@PathVariable long studentId,  @PathVariable long courseId) {
        studentService.enrollCourses(studentId, courseId);
        return new ResponseEntity<String>("Course enrolled", HttpStatus.OK);
    }
    
    
}
