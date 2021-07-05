package project.controller.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.lib.Injector;
import project.model.Driver;
import project.service.AuthenticationService;

import java.io.IOException;
import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private static final String DRIVER_ID = "driver_id";
    private static final Logger logger = LogManager.getLogger(LoginController.class);
    private static final Injector injector = Injector.getInstance("project");
    private final AuthenticationService authenticationService =
            (AuthenticationService) injector
                    .getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Driver driver = authenticationService.login(req.getParameter("login"),
                    req.getParameter("password"));
            req.getSession().setAttribute(DRIVER_ID, driver.getId());
            resp.sendRedirect("/index");
        } catch (AuthenticationException e) {
            logger.error("Error during authentication by login: " + req.getParameter("login"), e);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
