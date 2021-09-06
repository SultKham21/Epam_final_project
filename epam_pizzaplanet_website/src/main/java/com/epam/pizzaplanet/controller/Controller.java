package com.epam.pizzaplanet.controller;

import com.epam.pizzaplanet.controller.command.Command;
import com.epam.pizzaplanet.controller.command.CommandProvider;
import com.epam.pizzaplanet.controller.command.CommandResult;
import com.epam.pizzaplanet.controller.command.RequestParameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandString = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandProvider.getInstance().getCommand(commandString);
        CommandResult commandResult = command.execute(request);
        switch (commandResult.getRoutingType()) {
            case FORWARD:
                request.getRequestDispatcher(commandResult.getPage()).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + commandResult.getPage());
                break;
            default:
//                logger.log(Level.ERROR, "Illegal routing type");
//                response.sendRedirect(PagePath.ERROR_500_PAGE);
        }
    }
}

