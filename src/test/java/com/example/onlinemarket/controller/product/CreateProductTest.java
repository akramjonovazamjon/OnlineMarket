package com.example.onlinemarket.controller.product;

import com.example.onlinemarket.CommonIntegrationTest;
import com.example.onlinemarket.dto.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("create product ( POST /api/products )")
public class CreateProductTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should create product status code 201")
    void shouldCreateProduct() throws Exception {

        testDataHelperCategory.createCategoryRequest("TV");

        ResultActions resultActions = testDataHelperProduct.createProductRequest(new ProductDto("Samsung", "Great", 500d, 10, 1));

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.name").value("Samsung"))
                .andExpect(jsonPath("$.result.info").value("Great"))
                .andExpect(jsonPath("$.result.price").value(500d))
                .andExpect(jsonPath("$.result.quantity").value(10))
                .andExpect(jsonPath("$.result.categoryId").value(1));

    }

    @Test
    @DisplayName("should fail create product because of category not found status code 409")
    void shouldFailCreateProductByCategoryNotFound() throws Exception {

        ResultActions resultActions = testDataHelperProduct.createProductRequest(new ProductDto("Samsung", "Great", 500d, 10, 1));

        resultActions.andExpect(status().isConflict());

    }

    @Test
    @DisplayName("should fail create product because of duplicate status code 409")
    void shouldFailCreateProductByDuplicate() throws Exception {

        testDataHelperCategory.createCategoryRequest("TV");

        testDataHelperProduct.createProductRequest(new ProductDto("Samsung", "Great", 500d, 10, 1));

        ResultActions resultActions = testDataHelperProduct.createProductRequest(new ProductDto("Samsung", "Good", 200d, 10, 1));

        resultActions.andExpect(status().isConflict());

    }

}
