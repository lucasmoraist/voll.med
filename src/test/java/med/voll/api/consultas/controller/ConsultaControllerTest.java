package med.voll.api.consultas.controller;

import med.voll.api.consultas.domain.dto.DadosAgendamentoConsulta;
import med.voll.api.consultas.domain.dto.DadosDetalhamentoConsulta;
import med.voll.api.consultas.service.ConsultaService;
import med.voll.api.medico.domain.enums.Especialidade;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> jsonRequest;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> jsonResponse;

    @MockBean
    private ConsultaService consultaService;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void case01() throws Exception{
        var response = mvc.perform(post("/consulta"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao válidas")
    @WithMockUser
    void case02() throws Exception{
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.ORTOPEDIA;
        var detalhamentoConsulta = new DadosDetalhamentoConsulta(null, 2L, 5L, data);

        when(consultaService.agendarConsulta(any())).thenReturn(detalhamentoConsulta);

        var response = mvc.perform(post("/consulta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest.write(
                                new DadosAgendamentoConsulta(2L, 5L, data, especialidade)
                        ).getJson())
                )
                .andReturn().getResponse();

        var jsonEsperado = jsonResponse.write(detalhamentoConsulta).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}