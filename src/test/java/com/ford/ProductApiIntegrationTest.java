package com.ford;

import com.ford.client.ProductsClient;
import com.ford.config.BasicAuthRequestInterceptor;
import com.ford.config.FeignFactory;
import com.ford.model.Product;
import com.ford.util.ConfigLoader;
import feign.FeignException;
import feign.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        Assert.assertEquals(response.status(), 200, "Expected 200 for valid SKU");
    }

    @Test
    public void testGetProductBySkuInvalidSkuStatusCode() {
        String invalidSku = "NOT_A_VALID_SKU";

        Response response = productsClient.getProductBySkuResponse(invalidSku);
        Assert.assertEquals(response.status(), 404, "Expected 404 Not Found for invalid SKU");
    }

    @Test
    public void testGetProductBySku() {
        String testSku = "AL3Z1813300AA";
        Product product = productsClient.getProductBySku(testSku);
        Assert.assertNotNull(product, "Product should not be null for valid SKU");
        Assert.assertEquals(product.id, testSku, "ID should match the requested value");
        Assert.assertEquals(product.objectType, "Accessory", "ObjectType should match Accessory");
    }
}
