package by.epam.pizzashop.controller.filter;

import by.epam.pizzashop.controller.command.PagePath;
import by.epam.pizzashop.controller.command.SessionAttribute;
import by.epam.pizzashop.entity.Role;
import by.epam.pizzashop.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;

import static by.epam.pizzashop.controller.command.PagePath.*;
import static by.epam.pizzashop.controller.command.PagePath.UPDATING_MANAGER_EMAIL;

/**
 * The type Page redirect security filter.
 */
@WebFilter(urlPatterns = {"/pages/*"})
public class PageRedirectSecurityFilter implements Filter {
    private Logger logger = LogManager.getLogger();
    private EnumMap<Role, List<String>> availablePages;
    private List<String> adminPages;
    private List<String> customerPages;
    private List<String> managerPages;
    private List<String> guestPages;

    @Override
    public void init(FilterConfig filterConfig) {
        availablePages = new EnumMap<>(Role.class);
        guestPages = List.of(ERROR_500_PAGE, ERROR_404_PAGE, LOGIN, REGISTRATION, VERIFICATION_CUSTOMER,
                HOW_TO_DO_ORDER, ABOUT_US, QUESTIONS);

        adminPages = List.of(ERROR_500_PAGE, ERROR_404_PAGE, MAIN_ADMIN, ALL_MANAGERS, INACTIVE_MANAGERS,
                ALL_RESTAURANTS, ALL_PRODUCTS,UPDATING_MANAGER_LOGIN, UPDATING_MANAGER_FIRST_NAME,
                UPDATING_MANAGER_LAST_NAME, UPDATING_MANAGER_EMAIL, UPDATING_MANAGER_TELEPHONE,
                ADDITION_PICTURE, SEE_PRODUCT, UPDATING_RESTAURANT_NUMBER, UPDATING_RESTAURANT_CITY,
                UPDATING_RESTAURANT_STREET, UPDATING_RESTAURANT_HOUSE, UPDATING_RESTAURANT_BLOCK, UPDATING_PRODUCT_NAME,
                UPDATING_PRODUCT_DOSE, UPDATING_PRODUCT_GROUP, UPDATING_PRODUCT_PRICE,
                UPDATING_PRODUCT_INSTRUCTION);

        customerPages = List.of(ERROR_500_PAGE, ERROR_404_PAGE,  HOW_TO_DO_ORDER, ABOUT_US, QUESTIONS, MAIN_CUSTOMER, VERIFICATION_CUSTOMER, SEARCH_PRODUCTS_BY_NAME,
                ABOUT_PRODUCT,
                BASKET, RESTAURANTS_FOR_CUSTOMER, ORDER, ORDER_ACCEPT);


        managerPages = List.of(ERROR_500_PAGE, ERROR_404_PAGE, MAIN_MANAGER, ALL_PROCESSING_ORDERS, BASKET_FOR_ORDER);

        availablePages.put(Role.GUEST, guestPages);
        availablePages.put(Role.ADMIN, adminPages);
        availablePages.put(Role.MANAGER, managerPages);
        availablePages.put(Role.CUSTOMER, customerPages);
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String servletPath = httpServletRequest.getServletPath();

        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER_AUTH);
        Role role;

        if (user == null) {
            role = Role.GUEST;
        } else {
            role = user.getRole();
        }

        if (role.equals(Role.ADMIN) && !adminPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(PagePath.LOGIN).forward(request, response);
        }

        if (role.equals(Role.CUSTOMER) && !customerPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(PagePath.LOGIN).forward(request, response);
        }

        if (role.equals(Role.MANAGER) && !managerPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(PagePath.LOGIN).forward(request, response);
        }

        if (role.equals(Role.GUEST) && !guestPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(PagePath.LOGIN).forward(request, response);
        }
        chain.doFilter(request, response);
    }
}
