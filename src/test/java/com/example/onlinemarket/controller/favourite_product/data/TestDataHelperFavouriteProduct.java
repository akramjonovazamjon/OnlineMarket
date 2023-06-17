package com.example.onlinemarket.controller.favourite_product.data;

import com.example.onlinemarket.dto.FavouriteProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
@RequiredArgsConstructor
public class TestDataHelperFavouriteProduct {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    public ResultActions createFavouriteProductRequest(Integer productId) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/favourite-products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new FavouriteProductDto(productId))));

    }

    public ResultActions getUserFavouriteProductRequest() throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.get("/api/favourite-products/user"));

    }


}
