package com.epam.pizzaplanet.controller.command.impl;

import com.epam.pizzaplanet.controller.command.Command;
import com.epam.pizzaplanet.controller.command.CommandResult;
import com.epam.pizzaplanet.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.REDIRECT);
    }
}
