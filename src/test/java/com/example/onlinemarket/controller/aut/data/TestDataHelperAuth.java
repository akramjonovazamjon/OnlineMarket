package com.example.onlinemarket.controller.aut.data;

import com.example.onlinemarket.dto.UserDto;
import com.example.onlinemarket.dto.UserLoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
@RequiredArgsConstructor
public class TestDataHelperAuth {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    public ResultActions registerUser(UserDto dto) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
    }

    public ResultActions loginUser(UserLoginDto dto) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
    }


}
