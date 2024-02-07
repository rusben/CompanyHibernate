package net.xeill.elpuig.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Department {

    @Id
    private Long id;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }


}