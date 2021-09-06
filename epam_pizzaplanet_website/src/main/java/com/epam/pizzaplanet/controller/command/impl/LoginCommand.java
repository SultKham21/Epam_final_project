package com.epam.pizzaplanet.controller.command.impl;


import com.epam.pizzaplanet.controller.command.*;
import com.epam.pizzaplanet.entity.User;
import com.epam.pizzaplanet.exception.ServiceException;
import com.epam.pizzaplanet.model.service.UserService;
import com.epam.pizzaplanet.model.service.iml.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request)  {
        CommandResult commandResult = null;
        HttpSession session = request.getSession();
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        try {
            Optional<User> user = userService.authenticationUser(login, password);
            if (user.isPresent()) {
                User userAuth = user.get();
                session.setAttribute(SessionAttribute.USER_AUTH, userAuth);
                switch (user.get().getRole()) {
//                //TODO roles
                  //  case PHARMACIST -> {
                    //    commandResult = new CommandResult(PagePath.MAIN_PHARMACIST, CommandResult.RoutingType.REDIRECT);
                   // }
                    case CUSTOMER -> {
                        commandResult = new CommandResult(PagePath.MAIN_CUSTOMER, CommandResult.RoutingType.REDIRECT);
                    }
                    case ADMIN -> {
                        commandResult = new CommandResult(PagePath.MAIN_ADMIN, CommandResult.RoutingType.REDIRECT);
                    }
                }
            } else {
                session.setAttribute(SessionAttribute.LOGIN_ERROR, Message.LOGIN_ERROR);
                return new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.REDIRECT);
            }
        } catch (ServiceException e) {
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        return commandResult;
    }
}


