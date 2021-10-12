package by.epam.pizzashop.controller.command.impl;

import by.epam.pizzashop.controller.command.Command;
import by.epam.pizzashop.controller.command.CommandResult;
import by.epam.pizzashop.controller.command.RequestParameter;
import by.epam.pizzashop.controller.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * The type Change language command.
 */
public class ChangeLanguageCommand implements Command {
    private static final String RUSSIAN_LOCALE = "Ru";
    private static final String ENGLISH_LOCALE = "En";
    private static final String ENGLISH_BUNDLE = "prop.pagecontent_en_En";
    private static final String RUSSIAN_BUNDLE = "prop.pagecontent_ru_Ru";

    @Override
    public CommandResult execute(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        String currentUrl = httpServletRequest.getParameter(RequestParameter.CURRENT_URL);
        String url = currentUrl.substring(21);
        String language = httpServletRequest.getParameter(RequestParameter.LANGUAGE);
        if (language.equals(ENGLISH_LOCALE)) {
            session.setAttribute(SessionAttribute.CURRENT_LOCALE, RUSSIAN_LOCALE);
            session.setAttribute(SessionAttribute.CURRENT_BUNDLE, RUSSIAN_BUNDLE);
            session.setAttribute(SessionAttribute.SECOND_LOCALE, ENGLISH_LOCALE);
        } else {
            session.setAttribute(SessionAttribute.CURRENT_LOCALE, ENGLISH_LOCALE);
            session.setAttribute(SessionAttribute.CURRENT_BUNDLE, ENGLISH_BUNDLE);
            session.setAttribute(SessionAttribute.SECOND_LOCALE, RUSSIAN_LOCALE);
        }
        return new CommandResult(url, CommandResult.RoutingType.REDIRECT);
    }
}

