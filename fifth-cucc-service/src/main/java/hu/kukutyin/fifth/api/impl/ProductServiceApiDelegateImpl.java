package hu.kukutyin.fifth.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hu.kukutyin.fifth.api.ProductServiceApi;
import hu.kukutyin.fifth.api.ProductServiceApiDelegate;
import hu.kukutyin.fifth.domain.Product;
import hu.kukutyin.fifth.domain.Rate;

/**
 * see https://www.techiedelight.com/remove-elements-list-satisfies-predicate-java/
 */
@Slf4j
@Service
public class ProductServiceApiDelegateImpl implements ProductServiceApiDelegate {
    private static final Product EMPTY_PRODUCT = new Product().name("EMPTY");
    private static Integer ID = 0;

    private List<Product> products;

    public ProductServiceApiDelegateImpl() {
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Federer");
        product1.setDescription("The BEST");
        this.products.add(product1);
        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Ibrahimovic");
        product2.setDescription("The Best");
        this.products.add(product2);
        ID = this.products.size();
    }

    /**
     * POST /product
     * Add a new product to the store
     *
     * @param product       A JSON object containing Product information (required)
     * @return Success (status code 200)
     * or Invalid input (status code 405)
     * or Internal server error (status code 500)
     * @see ProductServiceApi#createProduct
     */
    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        Product newProduct = new Product();
        newProduct.setId(++ID);
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setRate(product.getRate());
        log.info("Body: {}", product);
        log.info("New product: {}", product);
        this.products.add(newProduct);
        return new ResponseEntity<>(
                this.products.stream()
                        .filter(p -> p.getName().equals(product.getName()))
                        .findAny()
                        .orElse(EMPTY_PRODUCT)
                , HttpStatus.OK);
    }

    /**
     * DELETE /product/{id}
     * delete a product by id
     *
     * @param id            Id of Product to return (required)
     * @return Success (status code 200)
     * or Invalid ID supplied (status code 400)
     * or Product not found (status code 404)
     * @see ProductServiceApi#deleteProductById
     */
    @Override
    public ResponseEntity<Void> deleteProductById(Integer id) {
        log.info("Deleted id  {}", id);
        List<Product> newList = this.products.stream()
                .filter(p -> !id.equals(p.getId()))
                .collect(Collectors.toList());
        log.info("New list: {}", newList);
        this.products = newList;
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * GET /product/1
     * Product 1 id
     *
     * @return Success (status code 200)
     * or Internal server error (status code 500)
     * @see ProductServiceApi#getProduct1
     */
    @Override
    public ResponseEntity<Product> getProduct1() {
        Integer one = 1;
        return new ResponseEntity<>(
                this.products.stream()
                        .filter(p -> one.equals(p.getId()))
                        .findAny()
                        .orElse(EMPTY_PRODUCT), HttpStatus.OK);
    }

    /**
     * GET /product/{id}
     * return a Product by id
     *
     * @param id            Id of Product to return (required)
     * @return Success (status code 200)
     * or Internal server error (status code 500)
     * @see ProductServiceApi#getProductId
     */
    @Override
    public ResponseEntity<Product> getProductId(Integer id) {
        return new ResponseEntity<>(
                this.products.stream()
                        .filter(p -> id.equals(p.getId()))
                        .findAny()
                        .orElse(EMPTY_PRODUCT), HttpStatus.OK);
    }

    /**
     * GET /product
     * All Products
     *
     * @return Success (status code 200)
     * or Internal server error (status code 500)
     * @see ProductServiceApi#getProducts
     */
    @Override
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(this.products, HttpStatus.OK);
    }

    /**
     * PUT /product/{id}
     * Update Product
     *
     * @param id            Id of product to return (required)
     * @param product       A JSON object containing Product information (required)
     * @return Success (status code 200)
     * or Internal server error (status code 500)
     * @see ProductServiceApi#updateProductId
     */
    @Override
    public ResponseEntity<Product> updateProductId(Integer id, Product product) {
        log.info("Id: {}, Product: {}", id, product);
        products = products.stream()
                .map(p -> {
                    if (id.equals(p.getId())) {
                        return product;
                    } else {
                        return p;
                    }
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(
                products.stream()
                        .filter(p -> id.equals(p.getId()))
                        .findAny()
                        .orElse(EMPTY_PRODUCT), HttpStatus.OK);
    }

    /**
     * PUT /product/{id}/rate
     * Update Product Rate
     *
     * @param id   Id of product to return (required)
     * @param rate A JSON object containing Product information (required)
     * @return Success (status code 200)
     * or Internal server error (status code 500)
     * @see ProductServiceApi#updateProductRateById
     */
    @Override
    public ResponseEntity<Product> updateProductRateById(Integer id, Rate rate) {
        log.info("Id: {}, Rate: {}", id, rate);
        Product product = products.stream()
                .filter(p -> id.equals(p.getId()))
                .findAny()
                .orElse(EMPTY_PRODUCT);
        products = products.stream()
                .map(p -> {
                    if (id.equals(p.getId())) {
                        product.setRate(rate.getRate());
                        return product;
                    } else {
                        return p;
                    }
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(
                products.stream()
                        .filter(p -> id.equals(p.getId()))
                        .findAny()
                        .orElse(EMPTY_PRODUCT), HttpStatus.OK);
    }
}
