package com.epam.khamroev.exception;

import java.sql.SQLException;

public class DaoException extends Throwable {
    public DaoException(String s, SQLException e) {
    }

    public DaoException(String s) {
    }
}
