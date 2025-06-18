package com.ford.gfa;

import com.ford.client.stibo.ProductsClient;
import com.ford.config.FeignFactory;
import com.ford.model.stibo.Product;
import com.ford.util.ConfigLoader;
import feign.Response;
import feign.auth.BasicAuthRequestInterceptor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class ProductApiIntegrationTest {
    private ProductsClient productsClient;

    @BeforeClass
    public void setUp() {
        String baseUrl = ConfigLoader.get("stibo.url");
        String username = ConfigLoader.get("auth.username");
        String password = ConfigLoader.get("auth.password");
        String env = ConfigLoader.get("env");

        productsClient = FeignFactory.buildClient(ProductsClient.class, baseUrl, new BasicAuthRequestInterceptor(username, password));
    }

    @Test
    public void testGetProductBySkuValidSkuStatusCode() {
        String invalidSku = "AL3Z1813300AA";

        Response response = productsClient.getProductBySkuResponse(invalidSku);
        assertThat(response.status()).as("Expected 200 for valid SKU").isEqualTo(200);
    }

    @Test
    public void testGetProductBySkuInvalidSkuStatusCode() {
        String invalidSku = "NOT_A_VALID_SKU";

        Response response = productsClient.getProductBySkuResponse(invalidSku);
        assertThat(response.status()).as("Expected 404 Not Found for invalid SKU").isEqualTo(404);
    }

    @Test
    public void testGetProductBySku() {
        String testSku = "AL3Z1813300AA";
        Product product = productsClient.getProductBySku(testSku);
        assertThat(product).as("Product should not be null for valid SKU").isNotNull();
        assertThat(product.id).as("ID should match the requested value").isEqualTo(testSku);
        assertThat(product.objectType).as("ObjectType should match Accessory").isEqualTo("Accessory");
    }
}
