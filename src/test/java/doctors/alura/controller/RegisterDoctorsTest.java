package doctors.alura.controller;

import doctors.alura.domain.address.Address;
import doctors.alura.domain.address.AddressData;
import doctors.alura.domain.consultation.ConsultationData;
import doctors.alura.domain.doctors.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class RegisterDoctorsTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DoctorData> doctorDataJson;

    @Autowired
    private JacksonTester<DoctorDetails> doctorDetailsJson;

    @MockBean
    private DoctorsRepository repository;

    @Test
    @DisplayName("return code 400 when invalid information")
    @WithMockUser
    void registerDoctorFirstCase() throws Exception {
        var response = mvc.perform(post("/doctors"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("return code 200 when valid infomation")
    @WithMockUser
    void registerDoctorSecondCase() throws Exception {
        var doctorData = new DoctorData(
                "Doctor",
                "doctor@teste",
                "61999999999",
                "123456",
                Specialty.CARDIOLOGY,
                address());

        when(repository.save(any())).thenReturn(new Doctors(doctorData));

        var response = mvc.perform(
                post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(doctorDataJson.write(doctorData).getJson())
        ).andReturn().getResponse();

        var doctorDetails = new DoctorDetails(
                null,
                doctorData.name(),
                doctorData.email(),
                doctorData.phone(),
                doctorData.crm(),
                doctorData.specialty(),
                new Address(doctorData.address()));

        var jsonRight = doctorDetailsJson.write(doctorDetails).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonRight);
    }

    private AddressData address() {
        return new AddressData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "XXXX",
                "DF"

        );
    }
}