package com.example.onlinemarket.controller.category;

import com.example.onlinemarket.CommonIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("update category ( PUT /api/categories )")
public class UpdateCategoryTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should update category status code 200")
    void shouldUpdateCategory() throws Exception {

        testDataHelperCategory.createCategoryRequest("TV");


        ResultActions resultActions = testDataHelperCategory.updateCategoryRequest("Phone", 1);

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.name").value("Phone"));

    }

    @Test
    @DisplayName("should fail update category because of duplicate status code 200")
    void shouldFailUpdateCategoryByDuplicate() throws Exception {

        testDataHelperCategory.createCategoryRequest("TV");

        testDataHelperCategory.createCategoryRequest("Phone");

        testDataHelperCategory.createCategoryRequest("Car");


        ResultActions resultActions = testDataHelperCategory.updateCategoryRequest("Phone", 1);

        resultActions.andExpect(status().isConflict());

    }


}
