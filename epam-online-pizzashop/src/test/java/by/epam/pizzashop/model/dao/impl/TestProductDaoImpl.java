package by.epam.pizzashop.model.dao.impl;

import by.epam.pizzashop.entity.Product;
import by.epam.pizzashop.exception.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestProductDaoImpl {

    @Mock
    private ProductDaoImpl productDao;
    private List<Product> products;
    List<Product> testProducts;
    private Product product;
    private Product testProduct;

    @BeforeEach
    public void setUp() {
        product = new Product.Builder()
                .setProductId(1)
                .setName("Concor")
                .setDose("5 mg №30 in tab.")
                .setGroup("cardiac")
                .setPrice(new BigDecimal("26.3"))
                .setPicture("D://")
                .setInstruction("instruction")
                .build();
        testProduct = new Product();
        products = new ArrayList<>();
        testProducts = new ArrayList<>();
        products.add(product);
    }

    @Test
    public void findListProductsTest() throws DaoException {
        when(productDao.findListProducts(1)).thenReturn(products);
        List<Product> actualProducts = productDao.findListProducts(1);
        assertEquals(products, actualProducts);
    }

    @Test
    public void findListProductsFalseTest() throws DaoException {
        when(productDao.findListProducts(1)).thenReturn(products);
        List<Product> actualProducts = productDao.findListProducts(1);
        assertNotEquals(testProducts, actualProducts);
    }

    @Test
    public void findListProductsByNameTest() throws DaoException {
        when(productDao.findListProductsByName("Concor")).thenReturn(products);
        List<Product> actualProducts = productDao.findListProductsByName("Concor");
        assertEquals(products, actualProducts);
    }

    @Test
    public void findListProductsByNameFalseTest() throws DaoException {
        when(productDao.findListProductsByName("Concor")).thenReturn(products);
        List<Product> actualProducts = productDao.findListProductsByName("Concor");
        assertNotEquals(testProducts, actualProducts);
    }

    @Test
    public void findListProductsByNonProprietaryNameTest() throws DaoException {
        when(productDao.findListProductsByName("Bisoprolol")).thenReturn(products);
        List<Product> actualProducts = productDao.findListProductsByName("Bisoprolol");
        assertEquals(products, actualProducts);
    }

    @Test
    public void findListProductsByNonProprietaryNameFalseTest() throws DaoException {
        when(productDao.findListProductsByName("Bisoprolol")).thenReturn(products);
        List<Product> actualProducts = productDao.findListProductsByName("Bisoprolol");
        assertNotEquals(testProducts, actualProducts);
    }

    @Test
    public void findProductsNumberTest() throws DaoException {
        when(productDao.findProductsNumber()).thenReturn(5);
        int actualResult = productDao.findProductsNumber();
        assertEquals(5, actualResult);
    }

    @Test
    public void findProductsNumberFalseTest() throws DaoException {
        when(productDao.findProductsNumber()).thenReturn(5);
        int actualResult = productDao.findProductsNumber();
        assertNotEquals(4, actualResult);
    }

    @Test
    public void findPathToPictureTest() throws DaoException {
        when(productDao.findPathToPicture(1)).thenReturn(Optional.of("D://"));
        Optional<String> actualPath = productDao.findPathToPicture(1);
        assertEquals(product.getPathToPicture(), actualPath.get());
    }

    @Test
    public void findPathToPictureFalseTest() throws DaoException {
        when(productDao.findPathToPicture(1)).thenReturn(Optional.of("D://"));
        Optional<String> actualPath = productDao.findPathToPicture(1);
        assertNotEquals(testProduct.getPathToPicture(), actualPath.get());
    }

    @Test
    public void findProductByIdTest() throws DaoException {
        when(productDao.findProductById(1)).thenReturn(Optional.of(product));
        Optional<Product> actualProduct = productDao.findProductById(1);
        assertEquals(product, actualProduct.get());
    }

    @Test
    public void findProductByIdFalseTest() throws DaoException {
        when(productDao.findProductById(1)).thenReturn(Optional.of(product));
        Optional<Product> actualProduct = productDao.findProductById(1);
        assertNotEquals(testProduct, actualProduct.get());
    }

    @Test
    public void findProductForOrderByIdTest() throws DaoException {
        when(productDao.findProductForOrderById(1)).thenReturn(Optional.of(product));
        Optional<Product> actualProduct = productDao.findProductForOrderById(1);
        assertEquals(product, actualProduct.get());
    }

    @Test
    public void findProductForOrderByIdFalseTest() throws DaoException {
        when(productDao.findProductForOrderById(1)).thenReturn(Optional.of(product));
        Optional<Product> actualProduct = productDao.findProductForOrderById(1);
        assertNotEquals(testProduct, actualProduct.get());
    }

}
