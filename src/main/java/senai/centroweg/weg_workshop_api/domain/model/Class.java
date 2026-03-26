package senai.centroweg.weg_workshop_api.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    String name;

    @JoinColumn(name = "teacher", nullable = false)
    @ManyToOne()
    User teacher;

    @JoinColumn(name = "students", nullable = false)
    @ManyToMany(cascade = CascadeType.ALL)
    List<User> students;

    public Class(String name, User teacher, List<User> students) {
        this.name = name;
        this.teacher = teacher;
        this.students = students;
    }

    public Class() { }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getTeacher() {
        return teacher;
    }

    public List<User> getStudents() {
        return students;
    }
}
