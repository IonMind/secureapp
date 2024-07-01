package com.defender.secureapp.model;

import java.util.HashSet;
import java.util.Set;



import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

@Entity
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name should not be null")
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(unique = true,name = "abbriviation")
    private String abbriviation;

    @Column(name = "price")
    private float price = 0.0f;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "courses")
    @JsonIgnore
    Set<Student> students = new HashSet<>();

    @PreRemove
    public void preRemoveStudents(){
        for (Student student : this.getStudents()) {
            student.getCourses().remove(this);
            System.out.println(student);
        }
        students.clear();
    }

    @Override
    public int hashCode() {
        return this.abbriviation.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Course that = (Course) obj;
        return this.getAbbriviation().equals(that.getAbbriviation());
    }
    
}
