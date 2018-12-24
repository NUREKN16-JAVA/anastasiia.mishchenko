package ua.nure.kn.mishchenko.usermanagement.web;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn.mishchenko.usermanagement.User;
import ua.nure.kn.mishchenko.usermanagement.db.DaoFactory;
import ua.nure.kn.mishchenko.usermanagement.db.DatabaseException;

public class AddServlet extends EditServlet {
    protected void processUser(User user) throws DatabaseException {
        DaoFactory.getInstance().getUserDao().create(user);
    }

    
    protected void showPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }
}