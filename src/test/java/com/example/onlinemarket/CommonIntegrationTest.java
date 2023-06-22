package com.example.onlinemarket;

import com.example.onlinemarket.controller.category.data.TestDataHelperCategory;
import com.example.onlinemarket.controller.favourite_product.data.TestDataHelperFavouriteProduct;
import com.example.onlinemarket.controller.product.data.TestDataHelperProduct;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class CommonIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TestDataHelperCategory testDataHelperCategory;

    @Autowired
    public TestDataHelperProduct testDataHelperProduct;
    @Autowired
    public TestDataHelperFavouriteProduct testDataHelperFavouriteProduct;

    @Container
    private static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @AfterEach
    public void execute() {
        jdbcTemplate.execute("TRUNCATE TABLE categories RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE attachments RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE baskets RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE basket_items RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE favourite_products RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE orders RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE permissions RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE products RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE roles RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE users RESTART IDENTITY CASCADE ");
    }

}
