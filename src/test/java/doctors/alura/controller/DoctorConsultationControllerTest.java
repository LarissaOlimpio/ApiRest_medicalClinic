package doctors.alura.controller;

import doctors.alura.domain.consultation.ConsultationData;
import doctors.alura.domain.consultation.ConsultationDetails;
import doctors.alura.domain.consultation.ConsultationSchedule;
import doctors.alura.domain.doctors.Specialty;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorConsultationControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<ConsultationData> consultationDataJson;
    @Autowired
    private JacksonTester<ConsultationDetails> consultationDetailsJson;


    @MockBean
    private ConsultationSchedule schedule;


    @Test
    @DisplayName("Return http code 400 when invalid information")
    @WithMockUser
    void scheduleFirstCase() throws Exception {
       var response = mvc.perform(post("/consultation"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Return http code 200 when valid information")
    @WithMockUser
    void scheduleSecondCase() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var speciality = Specialty.CARDIOLOGY;
        var dataDetails =  new ConsultationDetails(null,2l,5l,date);
        when(schedule.schedule(any())).thenReturn(dataDetails);
        var response = mvc.perform(
                post("/consultation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(consultationDataJson.write(new ConsultationData(2l,5l,date,speciality)).getJson())

        ).andReturn().getResponse();
        //code http validation
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //response validation
        var jsonRight = consultationDetailsJson.write(dataDetails).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonRight);

    }


}