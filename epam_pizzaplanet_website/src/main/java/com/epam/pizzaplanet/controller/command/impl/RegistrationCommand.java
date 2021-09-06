package com.epam.pizzaplanet.controller.command.impl;


import com.epam.pizzaplanet.controller.command.*;
import com.epam.pizzaplanet.entity.Role;
import com.epam.pizzaplanet.entity.User;
import com.epam.pizzaplanet.exception.ServiceException;
import com.epam.pizzaplanet.model.service.UserService;
import com.epam.pizzaplanet.model.service.iml.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

public class RegistrationCommand implements Command {
    private static final String EMPTY_STRING = "\s";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        CommandResult commandResult;
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String telephone = request.getParameter(RequestParameter.TELEPHONE);
        String role = request.getParameter(RequestParameter.ROLE);

        UserService userService = UserServiceImpl.getInstance();
        Map<String, String> mapData =  userService.isFormValid(login, password, firstName, lastName, email, telephone);
        request.setAttribute("mapData", mapData);
        if (mapData.containsValue(EMPTY_STRING)) {
            request.setAttribute(RequestAttribute.REGISTRATION_ERROR, Message.DATA_REGISTRATION_ERROR);
            return new CommandResult(PagePath.REGISTRATION, CommandResult.RoutingType.FORWARD);
        }

        try {
            Optional<User> user = userService.createUser(login, password, firstName, lastName, email, telephone, role);
            if (user.isPresent()) {
                if (user.get().getRole().equals(Role.CUSTOMER)) {
                    commandResult = new CommandResult(PagePath.VERIFICATION_CUSTOMER, CommandResult.RoutingType.REDIRECT);
                } else {
                    commandResult = new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.FORWARD);
                }
            } else {
                mapData.put(RequestParameter.LOGIN, EMPTY_STRING);
                request.setAttribute(RequestAttribute.REGISTRATION_ERROR, Message.REGISTRATION_ERROR);
                commandResult = new CommandResult(PagePath.REGISTRATION, CommandResult.RoutingType.FORWARD);
            }
        } catch (ServiceException e) {
            commandResult = new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        return commandResult;
    }
}
