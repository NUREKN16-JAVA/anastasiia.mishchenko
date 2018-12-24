package ua.nure.kn.mishchenko.usermanagement.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailsServlet extends BrowseServlet {

    public DetailsServlet() {}
    
    public void service(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("okButton") != null) {
            doOk(request, response);
        } else {
            showPage(request, response);
        }
    }

    private void doOk(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/browse").forward(request, response);
    }


    protected void showPage(HttpServletRequest request, 
                            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/details.jsp").forward(request, response);        
    }

}