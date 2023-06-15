package com.example.onlinemarket.controller.category.data;

import com.example.onlinemarket.dto.CategoryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
@RequiredArgsConstructor
public class TestDataHelperCategory {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    public ResultActions createCategoryRequest(String name) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CategoryDto(name))));
    }

    public ResultActions updateCategoryRequest(String name) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CategoryDto(name))));
    }

    public ResultActions getAllCategoriesRequest() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get("/api/categories"));
    }

    public ResultActions getCategoryByIdRequest() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get("/api/categories/1"));
    }

}
