package senai.centroweg.weg_workshop_api.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "class")
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    String name;

    @JoinColumn(name = "teacher", nullable = false)
    @ManyToOne()
    User teacher;

    @ManyToMany()
    @JoinTable(
            name = "service_order_students",
            joinColumns = @JoinColumn(name = "service_order_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<User> students;

    public SchoolClass(String name, User teacher, List<User> students) {
        this.name = name;
        this.teacher = teacher;
        this.students = students;
    }

    public SchoolClass() { }

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
