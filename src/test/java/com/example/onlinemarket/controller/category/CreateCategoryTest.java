package com.example.onlinemarket.controller.category;

import com.example.onlinemarket.CommonIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("create category ( POST /api/category )")
public class CreateCategoryTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should create new category status code 201")
    void shouldCreateCategory() throws Exception {

        ResultActions resultActions = testDataHelperCategory.createCategoryRequest("TV");

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.name").value("TV"));
    }

    @Test
    @DisplayName("should fail create new category because of duplicate status code 409")
    void shouldFailCreateCategoryByDuplicate() throws Exception {

        testDataHelperCategory.createCategoryRequest("TV");

        ResultActions resultActions = testDataHelperCategory.createCategoryRequest("TV");

        resultActions.andExpect(status().isConflict());
    }

    @Test
    @DisplayName("should fail create new category because of required field null status code 400")
    void shouldFailCreateCategoryByRequiredField() throws Exception {

        ResultActions resultActions = testDataHelperCategory.createCategoryRequest(null);

        resultActions.andExpect(status().isBadRequest());
    }


}
