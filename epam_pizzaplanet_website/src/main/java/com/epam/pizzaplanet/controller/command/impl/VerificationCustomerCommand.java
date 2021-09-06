package com.epam.pizzaplanet.controller.command.impl;


import com.epam.pizzaplanet.controller.command.*;
import com.epam.pizzaplanet.exception.ServiceException;
import com.epam.pizzaplanet.model.service.UserService;
import com.epam.pizzaplanet.model.service.iml.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VerificationCustomerCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        String code = request.getParameter(RequestParameter.CODE);
        try {
            if (userService.updateCustomerStatus(code)) {
              return new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.REDIRECT);
            } else {
                session.setAttribute(SessionAttribute.CODE_VERIFICATION_ERROR, Message.CODE_VERIFICATION_ERROR);
                return new CommandResult(PagePath.VERIFICATION_CUSTOMER, CommandResult.RoutingType.REDIRECT);
            }
        } catch (ServiceException e) {
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
    }
}
