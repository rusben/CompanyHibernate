package net.xeill.elpuig.controller;

import net.xeill.elpuig.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.util.List;

public class StudentController {
    private EntityManagerFactory entityManagerFactory;

    public StudentController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /* Method to CREATE a Student in the database */
    public void addStudent(Student student) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to READ all Students */
    public void listStudents() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Student> result = em.createQuery("from Student", Student.class)
                .getResultList();
        System.out.println(result.size());

        for (Student student : result) {
            System.out.println(student.toString());
        }

        System.out.println("STUDENTS");
        em.getTransaction().commit();
        em.close();
    }

    /* Method to UPDATE activity for a Student */
    public void updateStudent(Integer studentId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Student student = (Student) em.find(Student.class, studentId);
        em.merge(student);
        em.getTransaction().commit();
        em.close();
    }

    /* Method to DELETE a Student from the records */
    public void deleteStudent(Integer studentId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Student student = (Student) em.find(Student.class, studentId);
        em.remove(student);
        em.getTransaction().commit();
        em.close();
    }
}
