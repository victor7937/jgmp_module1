package com.epam.ld.module2.controller;

import com.epam.ld.module2.controller.handler.GlobalExceptionHandler;
import com.epam.ld.module2.service.MessengerService;
import com.epam.ld.module2.service.mail.MailServer;
import com.epam.ld.module2.service.template.TemplateEngine;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {MessageController.class, MessengerService.class, TemplateEngine.class, GlobalExceptionHandler.class})
public class ControllerTest {
    public static final String SEND_ENDPOINT = "/mail/send";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TemplateEngine templateEngine;



    @MockBean
    private MailServer mailServer;

    @BeforeEach
    void configureMainServerMock (){
        when(mailServer.send(
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString())
        ).thenReturn("123");
    }


    @Test
    void integrationTest() throws Exception {
        String requestJson = """
                {
                    "from":"mail.@mail.com",
                    "password":"password",
                    "to":"mail.@mail.com",
                    "subject":"Test Subject",
                    "text":"Hi it's #{name} #{surname}",
                    "tags":
                            {
                                "name":"Alexey",
                                "surname":"Sanders"
                            }
                }
                
                """;

        String expectedResponse = """
                {
                    "messageId": "123",
                    "messageText": "Hi it's Alexey Sanders",
                    "from": "mail.@mail.com",
                    "to": "mail.@mail.com"
                }
                """;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(SEND_ENDPOINT)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        String resultJson = result.getResponse().getContentAsString();
        assertEquals(mapper.readTree(resultJson), mapper.readTree(expectedResponse));
    }

    @Test
    void badRequestTest() throws Exception {
        String requestJson = """
                {
                    "from":"mail.@mail.com",
                    "password":"password",
                    "to":"mail.@mail.com",
                    "subject":"Test Subject",
                    "text":"Hi it's #{name} #{surname} #{familyname} ",
                    "tags":
                            {
                                "name":"Alexey",
                                "surname":"Sanders"
                            }
                }
                
                """;

        String expectedResponse = """
                {
                    "statusCode": 400,
                    "status": "BAD_REQUEST",
                    "message": "There are a some tags that are still unpopulated!"
                }
                """;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(SEND_ENDPOINT)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        String resultJson = result.getResponse().getContentAsString();
        assertEquals(mapper.readTree(resultJson), mapper.readTree(expectedResponse));
    }

    @Test
    void nonLatin1Test() throws Exception {
        String requestJson = """
                {
                    "from":"mail.@mail.com",
                    "password":"password",
                    "to":"mail.@mail.com",
                    "subject":"Test Subject",
                    "text":"Hi it's #{name} #{surname} привет",
                    "tags":
                            {
                                "name":"Alexey",
                                "surname":"Sanders"
                            }
                }
                
                """;

        String expectedResponse = """
                {
                    "statusCode": 400,
                    "status": "BAD_REQUEST",
                    "message": "Template and variables should be Latin-1!"
                }
                """;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(SEND_ENDPOINT)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        String resultJson = result.getResponse().getContentAsString();
        assertEquals(mapper.readTree(resultJson), mapper.readTree(expectedResponse));
    }

}

