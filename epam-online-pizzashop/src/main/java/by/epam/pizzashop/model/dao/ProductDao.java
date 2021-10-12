package by.epam.pizzashop.model.dao;

import by.epam.pizzashop.entity.Product;
import by.epam.pizzashop.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface Product dao.
 */
public interface ProductDao {
    /**
     * Create product.
     *
     * @param product the product
     * @throws DaoException the dao exception
     */
    void createProduct(Product product) throws DaoException;

    /**
     * Add path to picture.
     *
     * @param id       the id
     * @param fileName the file name
     * @throws DaoException the dao exception
     */
    void addPathToPicture(long id, String fileName) throws DaoException;

    /**
     * Find list products list.
     *
     * @param startingProduct the starting product
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Product> findListProducts(int startingProduct) throws DaoException;

    /**
     * Find list products by name list.
     *
     * @param productName the product name
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Product> findListProductsByName(String productName) throws DaoException;

    /**
     * Find products number int.
     *
     * @return the int
     * @throws DaoException the dao exception
     */
    int findProductsNumber() throws DaoException;

    /**
     * Find product for order by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Product> findProductForOrderById(long id) throws DaoException;

    /**
     * Find path to picture optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<String> findPathToPicture(long id) throws DaoException;

    /**
     * Find product by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Product> findProductById(long id) throws DaoException;

    /**
     * Update product name.
     *
     * @param id   the id
     * @param name the name
     * @throws DaoException the dao exception
     */
    void updateProductName(long id, String name) throws DaoException;

    /**
     * Update product dose.
     *
     * @param id   the id
     * @param dose the dose
     * @throws DaoException the dao exception
     */
    void updateProductDose(long id, String dose) throws DaoException;

    /**
     * Update product group.
     *
     * @param id    the id
     * @param group the group
     * @throws DaoException the dao exception
     */
    void updateProductGroup(long id, String group) throws DaoException;

    /**
     * Update product price.
     *
     * @param id    the id
     * @param price the price
     * @throws DaoException the dao exception
     */
    void updateProductPrice(long id, BigDecimal price) throws DaoException;

    /**
     * Update product instruction.
     *
     * @param id          the id
     * @param instruction the instruction
     * @throws DaoException the dao exception
     */
    void updateProductInstruction(long id, String instruction) throws DaoException;
}
