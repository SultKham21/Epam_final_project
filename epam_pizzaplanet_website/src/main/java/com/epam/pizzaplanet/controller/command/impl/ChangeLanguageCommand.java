package com.epam.pizzaplanet.controller.command.impl;

import com.epam.pizzaplanet.controller.command.Command;
import com.epam.pizzaplanet.controller.command.CommandResult;
import com.epam.pizzaplanet.controller.command.RequestParameter;
import com.epam.pizzaplanet.controller.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;


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
