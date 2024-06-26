package doctors.alura.domain.consultation;

import doctors.alura.domain.doctors.Doctors;
import doctors.alura.domain.patients.Patients;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "consultation")
@Entity(name = "Consultation")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctors doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patients patient;

    private LocalDateTime data;

    @Column(name = "reason_delete")
    @Enumerated(EnumType.STRING)
    private ReasonDelete reasonDelete;




    public void delete(ReasonDelete reason) {
        this.reasonDelete = reason;
    }
}
