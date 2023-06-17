package com.example.onlinemarket.controller.favourite_product;

import com.example.onlinemarket.CommonIntegrationTest;
import com.example.onlinemarket.dto.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("get user favourite products ( GET /api/favourite-products/user )")
public class GetFavouriteProductsTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should get user favourite products status code 200")
    void shouldGetUserFavouriteProducts() throws Exception {

        testDataHelperCategory.createCategoryRequest("TV");

        testDataHelperProduct.createProductRequest(new ProductDto("A", "yaxshi", 200d, 1, 1));
        testDataHelperProduct.createProductRequest(new ProductDto("B", "yaxshi", 200d, 1, 1));
        testDataHelperProduct.createProductRequest(new ProductDto("C", "yaxshi", 200d, 1, 1));

        testDataHelperFavouriteProduct.createFavouriteProductRequest(1);
        testDataHelperFavouriteProduct.createFavouriteProductRequest(2);
        testDataHelperFavouriteProduct.createFavouriteProductRequest(3);

        ResultActions resultActions = testDataHelperFavouriteProduct.getUserFavouriteProductRequest();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result", hasSize(3)));

    }

}
