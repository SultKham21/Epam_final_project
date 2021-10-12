package by.epam.pizzashop.controller;

import by.epam.pizzashop.controller.command.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Controller.
 */
@WebServlet(urlPatterns = "/controller")
public class Controller extends HttpServlet {
    private Logger logger = LogManager.getLogger();

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
            case FORWARD -> request.getRequestDispatcher(commandResult.getPage()).forward(request, response);
            case REDIRECT -> response.sendRedirect(request.getContextPath() + commandResult.getPage());
            default -> {
                logger.log(Level.ERROR, "Illegal routing type");
                response.sendRedirect(PagePath.ERROR_500_PAGE);
            }
        }
    }
}

