package com.example.onlinemarket.controller.category;

import com.example.onlinemarket.CommonIntegrationTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("get categories ( GET /api/categories )")
public class GetCategoryTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should get all categories status code 200")
    void shouldGetCategories() throws Exception {

        testDataHelperCategory.createCategoryRequest("TV");

        testDataHelperCategory.createCategoryRequest("Phone");

        ResultActions resultActions = testDataHelperCategory.getAllCategoriesRequest();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result", Matchers.hasSize(2)));
    }

    @Test
    @DisplayName("should get category by id status code 200")
    void shouldGetCategoryById() throws Exception {

        testDataHelperCategory.createCategoryRequest("TV");

        ResultActions resultActions = testDataHelperCategory.getCategoryByIdRequest(1);

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.name").value("TV"));
    }

}
