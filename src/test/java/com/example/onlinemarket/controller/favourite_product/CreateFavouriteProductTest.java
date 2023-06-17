package com.example.onlinemarket.controller.favourite_product;

import com.example.onlinemarket.CommonIntegrationTest;
import com.example.onlinemarket.dto.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("create favourite product ( POST /api/favourite-products )")
public class CreateFavouriteProductTest extends CommonIntegrationTest {

    @Test
    @DisplayName("should create user favourite product status code 201")
    void shouldCreateUserFavouriteProduct() throws Exception {

        testDataHelperCategory.createCategoryRequest("TV");

        testDataHelperProduct.createProductRequest(new ProductDto("A", "yaxshi", 200d, 1, 1));

        ResultActions resultActions = testDataHelperFavouriteProduct.createFavouriteProductRequest(1);

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.productId").value(1));

    }

}
