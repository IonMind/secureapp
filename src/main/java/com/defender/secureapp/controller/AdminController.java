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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/master")
public class AdminController {

    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studenService;

    @PostMapping("/addCourses")
    public ResponseEntity<List<Course>> saveCourse(@RequestBody List<Course> coursesList) {
        return new ResponseEntity<List<Course>>(courseService.saveCourses(coursesList), HttpStatus.OK);
    }

    @PutMapping("course/{courseAbbr}")
    public ResponseEntity<Course> updateCourseByAbbriviation(@PathVariable String courseAbbr,
            @RequestBody Course course) {
        return new ResponseEntity<Course>(courseService.updateCourseBy(courseAbbr, course), HttpStatus.OK);
    }

    @DeleteMapping("course/{courseAbbr}")
    public ResponseEntity<String> deleteCourseByAbbriviation(@PathVariable String courseAbbr) {
        courseService.deleteCourseBy(courseAbbr);
        return new ResponseEntity<String>(" Course Deleted  ", HttpStatus.OK);

    }

    @PostMapping("/addStudents")
    public ResponseEntity<List<Student>> saveAllStudents(@RequestBody List<Student> studentList) {
        return new ResponseEntity<List<Student>>(studenService.saveAllStudents(studentList), HttpStatus.OK);
    }
}
