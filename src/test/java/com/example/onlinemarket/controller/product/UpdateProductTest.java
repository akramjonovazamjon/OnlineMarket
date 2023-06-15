package com.example.onlinemarket.controller.product;

import com.example.onlinemarket.CommonIntegrationTest;
import com.example.onlinemarket.dto.ProductDto;
import com.example.onlinemarket.dto.ProductEditDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("update product ( PUT /api/products )")
public class UpdateProductTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should update product status code 200")
    void shouldUpdateProduct() throws Exception {

        testDataHelperCategory.createCategoryRequest("TV");

        testDataHelperProduct.createProductRequest(new ProductDto("SamsungA", "Great", 200d, 10, 1));
        testDataHelperProduct.createProductRequest(new ProductDto("SamsungB", "Good", 700d, 10, 1));
        testDataHelperProduct.createProductRequest(new ProductDto("SamsungC", "Great", 500d, 10, 1));

        testDataHelperProduct.updateProductRequest(new ProductEditDto("Artel", "YAxshi", 100d, 1), 2);

        ResultActions resultActions = testDataHelperProduct.getProductByIdRequest(2);

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(2))
                .andExpect(jsonPath("$.result.name").value("Artel"))
                .andExpect(jsonPath("$.result.info").value("YAxshi"))
                .andExpect(jsonPath("$.result.price").value(100d))
                .andExpect(jsonPath("$.result.quantity").value(1))
                .andExpect(jsonPath("$.result.categoryId").value(1));

    }

    @Test
    @DisplayName("should fail update product because of duplicate status code 409")
    void shouldFailUpdateProductByDuplicate() throws Exception {

        testDataHelperCategory.createCategoryRequest("TV");

        testDataHelperProduct.createProductRequest(new ProductDto("SamsungA", "Great", 200d, 10, 1));
        testDataHelperProduct.createProductRequest(new ProductDto("SamsungB", "Good", 700d, 10, 1));
        testDataHelperProduct.createProductRequest(new ProductDto("SamsungC", "Great", 500d, 10, 1));

        ResultActions resultActions = testDataHelperProduct.updateProductRequest(new ProductEditDto("SamsungC", "YAxshi", 100d, 1), 2);

        resultActions.andExpect(status().isConflict());
    }

}
