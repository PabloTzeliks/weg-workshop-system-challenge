package senai.centroweg.weg_workshop_api.domain.model;

import jakarta.persistence.*;
import senai.centroweg.weg_workshop_api.domain.enums.StatusSO;

import java.util.List;

@Entity
@Table
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "equipment", nullable = false)
    String equipment;

    @Column(name = "reported_defect", nullable = false)
    String reportedDefect;

    @Column(name = "used_materials", nullable = false)
    String usedMaterials;

    @Column(name = "technical_conclusion", nullable = false)
    String technicalConclusion;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    StatusSO status;

    @JoinColumn(name = "responsible_teacher")
    @ManyToOne(fetch = FetchType.LAZY)
    User responsibleTeacher;

    @JoinColumn(name = "students")
    @ManyToMany()
    List<User> students;

    public Integer getId() {
        return id;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getReportedDefect() {
        return reportedDefect;
    }

    public String getUsedMaterials() {
        return usedMaterials;
    }

    public String getTechnicalConclusion() {
        return technicalConclusion;
    }

    public StatusSO getStatus() {
        return status;
    }

    public User getResponsibleTeacher() {
        return responsibleTeacher;
    }

    public List<User> getStudents() {
        return students;
    }
}
