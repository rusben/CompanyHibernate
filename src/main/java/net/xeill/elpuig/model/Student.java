package net.xeill.elpuig.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    private Long id;

    private String name;

    @ManyToMany (mappedBy = "students", cascade = CascadeType.ALL)

/*    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    ) */
    private List<Course> courses = new ArrayList<Course>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}