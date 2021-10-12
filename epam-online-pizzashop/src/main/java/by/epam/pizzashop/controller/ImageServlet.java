package by.epam.pizzashop.controller;

import by.epam.pizzashop.controller.command.PagePath;
import by.epam.pizzashop.controller.command.RequestParameter;
import by.epam.pizzashop.controller.command.SessionAttribute;
import by.epam.pizzashop.exception.ServiceException;
import by.epam.pizzashop.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * The type Image servlet.
 */
@WebServlet(urlPatterns = "/addImage")
@MultipartConfig(location = "D:/Intelij Ultimate Project/WEBProjectEpam/EPAMFinal/epam-online-pizzashop/pictures/", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 25)
public class ImageServlet extends HttpServlet {
    private static  final Logger logger = LogManager.getLogger();
    private static final Properties properties = new Properties();
    private static final String DATABASE_PROPERTIES = "prop/picture.properties";
    private static final String UPLOAD_DIR = "image.path";
    private static final String CONTENT_TYPE = "image/jpeg";
    private static final String PATH;

    static {
        try (InputStream inputStream = ImageServlet.class.getClassLoader().getResourceAsStream(DATABASE_PROPERTIES)) {
            properties.load(inputStream);
            PATH = properties.getProperty(UPLOAD_DIR);
        } catch (FileNotFoundException e) {
            logger.log(Level.ERROR, "File with properties" + DATABASE_PROPERTIES + " not found " + e);
            throw new RuntimeException("File with properties" + DATABASE_PROPERTIES + " not found: " + e);
        } catch (IOException e) {
            logger.log(Level.ERROR, "Reading error: ", e);
            throw new RuntimeException("Reading error: ", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = new File(PATH);
        StringBuilder fileName = new StringBuilder();
        try {
            for (Part part : req.getParts()) {
                part.write(part.getSubmittedFileName());
                fileName.append(part.getSubmittedFileName());
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "File can't be write ", e);
            resp.sendRedirect(PagePath.ERROR_500_PAGE);
        }

        ProductServiceImpl productService = ProductServiceImpl.getInstance();
        long id = (long) req.getSession().getAttribute(SessionAttribute.PRODUCT_ID);
        try {
            productService.addPathToPicture(id, PATH + fileName);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while added path to picture ", e);
            resp.sendRedirect(PagePath.ERROR_500_PAGE);
        }
        resp.sendRedirect(PagePath.ADDITION_PICTURE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter(RequestParameter.PRODUCT_ID);
        ProductServiceImpl productService = ProductServiceImpl.getInstance();
        String path = null;
        try {
            path = productService.findPathToPicture(productId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method doGet while found path to picture ", e);
            resp.sendRedirect(PagePath.ERROR_500_PAGE);
        }
        byte[] imageBytes = Files.readAllBytes(Paths.get(path));
        resp.setContentType(CONTENT_TYPE);
        resp.setContentLength(imageBytes.length);
        resp.getOutputStream().write(imageBytes);
    }
}