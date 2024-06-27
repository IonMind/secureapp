package com.defender.secureapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.defender.secureapp.model.Course;
import com.defender.secureapp.model.Student;
import com.defender.secureapp.service.CourseService;
import com.defender.secureapp.service.StudentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/public")
public class PubliController {

    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;

    @GetMapping("/courses/{name}")
    public ResponseEntity<List<Course>> getAllCoursesContaining(@PathVariable String name) {

        return new ResponseEntity<List<Course>>(courseService.getCoursesBy(name), HttpStatus.OK);

    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<List<Course>>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        return  new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.OK);

    }
    

}
