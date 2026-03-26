package senai.centroweg.weg_workshop_api.domain.model;

import jakarta.persistence.*;
import senai.centroweg.weg_workshop_api.domain.enums.UserType;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.STRING)
    UserType userType;

    public User(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
    }

    public User() { }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserType getUserType() {
        return userType;
    }
}
