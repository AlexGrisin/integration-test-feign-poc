package com.ford.client.stibo;

import com.ford.model.stibo.Product;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface ProductsClient {
    @RequestLine("GET /restapiv2/products/{sku}?context=ROOT")
    Product getProductBySku(@Param("sku") String sku);

    @RequestLine("GET /restapiv2/products/{sku}?context=ROOT")
    Response getProductBySkuResponse(@Param("sku") String sku);
}
