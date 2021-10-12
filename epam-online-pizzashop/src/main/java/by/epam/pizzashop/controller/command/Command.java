package by.epam.pizzashop.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute command result.
     *
     * @param request the request
     * @return the command result
     */
    CommandResult execute(HttpServletRequest request);
}
