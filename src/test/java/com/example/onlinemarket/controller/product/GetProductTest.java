package com.example.onlinemarket.controller.product;

import com.example.onlinemarket.CommonIntegrationTest;
import com.example.onlinemarket.dto.ProductDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("get products ( GET /api/products )")
public class GetProductTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should get all products status code 200")
    void shouldGetAllProducts() throws Exception {

        testDataHelperCategory.createCategoryRequest("TV");

        testDataHelperProduct.createProductRequest(new ProductDto("SamsungA", "Great", 200d, 10, 1));

        testDataHelperProduct.createProductRequest(new ProductDto("SamsungB", "Good", 200d, 10, 1));

        testDataHelperProduct.createProductRequest(new ProductDto("SamsungC", "Great", 200d, 10, 1));

        ResultActions resultActions = testDataHelperProduct.getAllProductsRequest();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result", Matchers.hasSize(3)));

    }

    @Test
    @DisplayName("should get product by id status code 200")
    void shouldGetProductById() throws Exception {

        testDataHelperCategory.createCategoryRequest("TV");

        testDataHelperProduct.createProductRequest(new ProductDto("SamsungA", "Great", 200d, 10, 1));

        testDataHelperProduct.createProductRequest(new ProductDto("SamsungB", "Good", 700d, 101, 1));

        testDataHelperProduct.createProductRequest(new ProductDto("SamsungC", "Great", 200d, 10, 1));

        ResultActions resultActions = testDataHelperProduct.getProductByIdRequest(2);

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.name").value("SamsungB"))
                .andExpect(jsonPath("$.result.id").value(2))
                .andExpect(jsonPath("$.result.info").value("Good"))
                .andExpect(jsonPath("$.result.price").value(700d))
                .andExpect(jsonPath("$.result.quantity").value(101));

    }


}
