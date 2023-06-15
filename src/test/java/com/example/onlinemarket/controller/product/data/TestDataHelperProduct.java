package com.example.onlinemarket.controller.product.data;

import com.example.onlinemarket.dto.ProductDto;
import com.example.onlinemarket.dto.ProductEditDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
@RequiredArgsConstructor
public class TestDataHelperProduct {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    public ResultActions createProductRequest(ProductDto dto) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)));
    }

    public ResultActions getAllProductsRequest() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get("/api/products"));
    }

    public ResultActions getProductByIdRequest(Integer id) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get("/api/products/" + id));
    }

    public ResultActions updateProductRequest(ProductEditDto dto, Integer id) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders
                        .patch("/api/products/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
    }

}
