package com.epam.pizzaplanet.controller.listener;

import com.epam.pizzaplanet.controller.command.SessionAttribute;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "En";
    private static final String SECOND_LOCALE = "Ru";
    private static final String DEFAULT_BUNDLE = "prop.pagecontent_en_En";
    private static final String DEFAULT_PREVIOUS_QUERY = "/pages/login.jsp";


    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();

        session.setAttribute(SessionAttribute.CURRENT_LOCALE, DEFAULT_LOCALE);
        session.setAttribute(SessionAttribute.SECOND_LOCALE, SECOND_LOCALE);
        session.setAttribute(SessionAttribute.CURRENT_BUNDLE, DEFAULT_BUNDLE);
        session.setAttribute(SessionAttribute.PREVIOUS_QUERY, DEFAULT_PREVIOUS_QUERY);
    }
}
