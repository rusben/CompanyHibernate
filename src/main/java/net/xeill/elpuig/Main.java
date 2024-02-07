package net.xeill.elpuig;

import net.xeill.elpuig.controller.StudentController;
import net.xeill.elpuig.database.ConnectionFactory;
import net.xeill.elpuig.model.Course;
import net.xeill.elpuig.model.Student;
import net.xeill.elpuig.view.Menu;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();

        } catch (HibernateException he) {
            System.out.println("Session Factory creation failure");
            throw he;
        }
    }

    public static EntityManagerFactory createEntityManagerFactory() {
        EntityManagerFactory emf;
        try {
            emf = Persistence.createEntityManagerFactory("Company"); //TODO
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return emf;
    }

    public static void main(String[] args) {

        //    ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        //    Connection c = connectionFactory.connect();

        EntityManagerFactory entityManagerFactory = createEntityManagerFactory();
        StudentController studentController = new StudentController(entityManagerFactory);

        Menu menu = new Menu();
        int opcio;

        do {
            opcio = menu.mainMenu();
            switch (opcio) {
                case 0:
                    System.out.println("Bye!");
                    System.exit(1);
                    break;
                case 1:
                    studentController.listStudents();
                    break;
                case 2:
                    Student s = new Student();
                    s.setId(2L);
                    s.setName("Juan");


                    Course c = new Course();
                    c.setName("Curso Hibernate");
                    c.setId(2L);
                    s.getCourses().add(c);

                    c.getStudents().add(s);

                    studentController.addStudent(s);
                    System.out.println("ADD STUDENT");
                    break;

                default:
                    break;
            }
        } while(opcio != 0);
    }
}