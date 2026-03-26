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

    @Column(name = "used_materials")
    String usedMaterials;

    @Column(name = "technical_conclusion")
    String technicalConclusion;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    StatusSO status;

    @JoinColumn(name = "responsible_teacher")
    @ManyToOne(fetch = FetchType.LAZY)
    User responsibleTeacher;

    @ManyToMany()
    @JoinTable(
            name = "service_order_students",
            joinColumns = @JoinColumn(name = "service_order_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<User> students;

    public ServiceOrder(String equipment,
                        String reportedDefect,
                        String usedMaterials,
                        String technicalConclusion,
                        StatusSO status,
                        User responsibleTeacher,
                        List<User> students) {

        this.equipment = equipment;
        this.reportedDefect = reportedDefect;
        this.usedMaterials = usedMaterials;
        this.technicalConclusion = technicalConclusion;
        this.status = status;
        this.responsibleTeacher = responsibleTeacher;
        this.students = students;
    }

    public ServiceOrder() { }

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
