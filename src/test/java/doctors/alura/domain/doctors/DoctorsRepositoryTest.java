package doctors.alura.domain.doctors;

import doctors.alura.domain.address.AddressData;
import doctors.alura.domain.consultation.Consultation;
import doctors.alura.domain.patients.Patients;
import doctors.alura.domain.patients.PatientsData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorsRepositoryTest {
    @Autowired
    private DoctorsRepository repository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("return null when the only doctor register isn't available on the date")
    void chooseRandomDoctorFirstCase() {
        System.out.println("first case");
        //given
        var nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
        var doctor = registerDoctor("Doctor","doctor@teste","123456", Specialty.CARDIOLOGY);
        var patient = registerPatient("patient","patient@teste");
        registerConsultation(doctor, patient, nextMonday);
        //when
        var doctorAvailable= repository.chooseRandomDoctor(Specialty.CARDIOLOGY,nextMonday);
        //then
        assertThat(doctorAvailable).isNull();

    }
    @Test
    @DisplayName("return doctor when the doctor register is available on the date")
    void chooseRandomDoctorSecondCase() {
        System.out.println("second case");
        //given
        var nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
        var doctor = registerDoctor("Doctor","doctor@teste","123456", Specialty.CARDIOLOGY);
        System.out.println(doctor.getName());
        //when
        var doctorAvailable = repository.chooseRandomDoctor(Specialty.CARDIOLOGY,nextMonday);
        System.out.println(doctorAvailable.getName());
        //then
        assertThat(doctorAvailable).isEqualTo(doctor);

    }
    private void registerConsultation(Doctors doctors, Patients patients, LocalDateTime data) {
        em.persist(new Consultation(null, doctors, patients, data, null));

    }

    private Doctors registerDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctors(doctorsData(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patients registerPatient(String name, String email) {
        var patient = new Patients(patientsData(name, email));
        em.persist(patient);
        return patient;
    }
    private DoctorData doctorsData(String name, String email, String crm, Specialty specialty) {
        return new DoctorData(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                address()
        );
    }

    private PatientsData patientsData(String name, String email) {
        return new PatientsData(
                name,
                email,
                "61999999999",
                address()

        );
    }

    private AddressData address() {
        return new AddressData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null

        );
    }
}