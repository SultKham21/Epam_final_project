package by.epam.pizzashop.model.service.impl;

import by.epam.pizzashop.controller.command.RequestParameter;
import by.epam.pizzashop.dto.ProductDto;
import by.epam.pizzashop.entity.Product;
import by.epam.pizzashop.exception.DaoException;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.dao.ProductDao;
import by.epam.pizzashop.model.dao.impl.ProductDaoImpl;
import by.epam.pizzashop.model.service.ProductService;
import by.epam.pizzashop.model.validation.ProductValidator;
import by.epam.pizzashop.model.validation.impl.ProductValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Product service.
 */
public class ProductServiceImpl implements ProductService {
    private Logger logger = LogManager.getLogger();


    private static final int RECORD_PER_PAGE = 5;
    private static final String EMPTY_STRING = "\s";
    private ProductDao productDao = ProductDaoImpl.getInstance();
    private ProductValidator productValidator = ProductValidatorImpl.getInstance();

    private ProductServiceImpl() {
    }

    private static ProductServiceImpl instance = new ProductServiceImpl();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ProductServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<ProductDto> createProduct(String name,  String dose, String group,
                                          String price, String instruction) throws ServiceException {
        List<Product> currentProducts;
        Product product = new Product.Builder()
                .setName(name)
                .setDose(dose)
                .setGroup(group)
                .setPrice(BigDecimal.valueOf(Double.parseDouble(price)))
                .setInstruction(instruction)
                .build();
        try {
            productDao.createProduct(product);
            int productsNumber = productDao.findProductsNumber();
            int productsOnLastPage = productsNumber % RECORD_PER_PAGE;
            int pages = productsNumber / RECORD_PER_PAGE;
            if (productsOnLastPage == 0) {
                currentProducts = productDao.findListProducts(pages * RECORD_PER_PAGE - RECORD_PER_PAGE);
            } else {
                currentProducts = productDao.findListProducts(pages * RECORD_PER_PAGE);
            }

        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method createProduct() ", e);
            throw new ServiceException("DaoException is in method createProduct() ", e);
        }
        List<ProductDto> products = convertListProductToListProductDto(currentProducts);
        return products;
    }

    @Override
    public Map<String, String> isValidParameters(String name, String dose,
                                                 String group, String price, String instruction) {
        Map<String, String> productParameters = new HashMap<>();
        productParameters.put(RequestParameter.NAME, name);
        productParameters.put(RequestParameter.DOSE, dose);
        productParameters.put(RequestParameter.GROUP, group);
        productParameters.put(RequestParameter.PRICE, price);
        productParameters.put(RequestParameter.INSTRUCTION, instruction);
        productValidator.isValidForm(productParameters);
        return productParameters;
    }


    @Override
    public void addPathToPicture(long id, String fileName) throws ServiceException {
        try {
            productDao.addPathToPicture(id, fileName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method addPathToPicture() ", e);
            throw new ServiceException("DaoException is in method addPathToProduct() ", e);
        }
    }

    @Override
    public String findPathToPicture(String productId) throws ServiceException {
        long id = Long.parseLong(productId);
        Optional<String> pathToPicture;
        try {
            pathToPicture = productDao.findPathToPicture(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findPathToPicture() ", e);
            throw new ServiceException("DaoException is in method findPathToProduct() ", e);
        }
        String path = pathToPicture.orElse(EMPTY_STRING);
        return path;
    }

    @Override
    public List<ProductDto> findListProducts(int startingProduct) throws ServiceException {
        List<Product> productsDb;
        try {
            productsDb = productDao.findListProducts(startingProduct);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findListProducts() ", e);
            throw new ServiceException("DaoException is in method findListProducts() ", e);
        }
        List<ProductDto> products = convertListProductToListProductDto(productsDb);
        return products;
    }

    @Override
    public List<ProductDto> findListProductsByName(String productName) throws ServiceException {
        List<Product> productsDb;
        try {
            productsDb = productDao.findListProductsByName(productName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findListProductsByName() ", e);
            throw new ServiceException("DaoException is in method findListProductsByName() ", e);
        }
        List<ProductDto> products = convertListProductToListProductDto(productsDb);
        return products;
    }


    @Override
    public int findCurrentPage() throws ServiceException {
        int productsNumber;
        int currentPage;
        try {
            productsNumber = productDao.findProductsNumber();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findCurrentPage() ", e);
            throw new ServiceException("DaoException is in method findCurrentPage() ", e);
        }
        if (productsNumber % RECORD_PER_PAGE == 0) {
            currentPage = productsNumber / RECORD_PER_PAGE;
        } else {
            currentPage = productsNumber / RECORD_PER_PAGE + 1;
        }
        return currentPage;
    }

    @Override
    public ProductDto findProductById(String id) throws ServiceException {
        Optional<Product> product;
        Product productDb;
        try {
            long productId = Long.parseLong(id);
            product = productDao.findProductById(productId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findProductById() ", e);
            throw new ServiceException("DaoException is in method findProductById() ", e);
        }
        productDb = product.orElse(new Product());
        ProductDto productDto = convertProductToProductDto(productDb);
        return productDto;
    }

    @Override
    public Map<Product, Integer> addProductToOrder(String id, Map<Product, Integer> order) throws ServiceException {
        Optional<Product> product;
        try {
            long productId = Long.parseLong(id);
            product = productDao.findProductForOrderById(productId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method addProductToOrder() ", e);
            throw new ServiceException("DaoException is in method addProductToOrder() ", e);
        }
        product.ifPresent(productToOrder -> {
            order.put(productToOrder, 1);
        });
        return order;
    }

    @Override
    public ProductDto findInstructionByProductId(long productId) throws ServiceException {
        ProductDto productDto;
        try {
            Product product = productDao.findProductById(productId).orElse(new Product());
            productDto = new ProductDto.Builder()
                    .setInstruction(product.getInstruction())
                    .build();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method findInstructionByProductId() ", e);
            throw new ServiceException("DaoException is in method findInstructionByProductId() ", e);
        }
        return productDto;
    }

    @Override
    public void updateProductQuantityInOrder(String productId, String quantity, Map<Product, Integer> order) {
        long id = Long.parseLong(productId);
        Integer newQuantity = Integer.valueOf(quantity);
        Optional<Product> updatingProduct = order.keySet().stream()
                .filter(product -> product.getProductId() == id)
                .findFirst();
        updatingProduct.ifPresent(product -> {
            order.put(updatingProduct.get(), newQuantity);
        });
    }

    @Override
    public void updateProductName(long productId, String name) throws ServiceException {
        try {
            productDao.updateProductName(productId, name);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateProductName() ", e);
            throw new ServiceException("DaoException is in method updateProductName() ", e);
        }
    }


    @Override
    public void updateProductDose(long productId, String dose) throws ServiceException {
        try {
            productDao.updateProductDose(productId, dose);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateProductDose() ", e);
            throw new ServiceException("DaoException is in method updateProductDose() ", e);
        }
    }


    @Override
    public void updateProductGroup(long productId, String group) throws ServiceException {
        try {
            productDao.updateProductGroup(productId, group);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateProductGroup() ", e);
            throw new ServiceException("DaoException is in method updateProductGroup() ", e);
        }
    }

    @Override
    public void updateProductPrice(long productId, String price) throws ServiceException {
        BigDecimal newPrice = new BigDecimal(price);
        try {
            productDao.updateProductPrice(productId, newPrice);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateProductPrice() ", e);
            throw new ServiceException("DaoException is in method updateProductPrice() ", e);
        }
    }


    @Override
    public void updateProductInstruction(long productId, String instruction) throws ServiceException {
        try {
            productDao.updateProductInstruction(productId, instruction);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException is in method updateProductInstruction() ", e);
            throw new ServiceException("DaoException is in method updateProductInstruction() ", e);
        }
    }


    private List<ProductDto> convertListProductToListProductDto(List<Product> products) {
        return products.stream()
                .map(product -> new ProductDto.Builder()
                        .setProductId(product.getProductId())
                        .setName(product.getName())
                        .setDose(product.getDose())
                        .setGroup(product.getGroup())
                        .setPrice(product.getPrice())
                        .setInstruction(product.getInstruction())
                        .build()).collect(Collectors.toList());

    }

    private ProductDto convertProductToProductDto(Product productDb) {
        return  new ProductDto.Builder()
                .setName(productDb.getName())
                .setDose(productDb.getDose())
                .setGroup(productDb.getGroup())
                .setPrice(productDb.getPrice())
                .setInstruction(productDb.getInstruction())
                .build();
    }
}
