package com.defender.secureapp.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.defender.secureapp.ExceptionHandler.NoValuePresentException;
import com.defender.secureapp.model.Course;
import com.defender.secureapp.model.Student;
import com.defender.secureapp.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepo;
    // @Autowired
    // CourseRepository courseRepo;

    @Autowired
    CourseService courseService;

    @Autowired 
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Student saveStudent(Student s) {
        s.setPassword(passwordEncoder.encode(s.getPassword()));
        modifyCourseListIfAlreadyInDB(s);
        return studentRepo.save(s);
    }

    @Override
    public Set<Course> getEnrolledCourses(long Id) {
        Student student = findStudentById(Id);
        return student.getCourses();
    }

    @Override
    public void unEnrollCourses(long studentId, long courseID) {
        Student student = findStudentById(studentId);
        Course course = courseService.getCourseBy(courseID);
        student.getCourses().remove(course);
        studentRepo.save(student);
    }

    @Override
    public void enrollCourses(long studentId, long courseID) {
        Student student = findStudentById(studentId);
        Course course = courseService.getCourseBy(courseID);
        student.getCourses().add(course);
        modifyCourseListIfAlreadyInDB(student);
        studentRepo.save(student);
    }

    @Override
    public List<Student> saveAllStudents(List<Student> studentsList) {
       return studentRepo.saveAll(studentsList);
    }





    //////// helper method , business logixc

    protected Student findStudentById(long studentId) {
        return studentRepo.findById(studentId)
                .orElseThrow(() -> new NoValuePresentException("No student with id " + studentId));
    }

    protected void modifyCourseListIfAlreadyInDB(Student s){
        Set<Course> coursesInDB = new HashSet<>();
        Set<Course> courses = s.getCourses();
        
        Iterator<Course> itr = courses.iterator();
        while (itr.hasNext()) {
            Course course = itr.next();
            Course courseAlreadyInDB = courseService.getCourseBy(course.getAbbriviation());
            if ((courseAlreadyInDB != null)) {
                itr.remove();
                coursesInDB.add(courseAlreadyInDB);
            }
            
        }
        courses.addAll(coursesInDB);
    }


    //DAO for login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student s = studentRepo.findByName(username).orElseThrow(() -> new UsernameNotFoundException("No Student with name : "+username));
        UserDetails studentDetails = User.withUsername(s.getName()).password(s.getPassword()).build();
        return studentDetails;
    }


}
