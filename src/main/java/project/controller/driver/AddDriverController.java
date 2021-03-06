package project.controller.driver;

import project.lib.Injector;
import project.model.Driver;
import project.service.DriverService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("project");
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/drivers/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Driver driver = new Driver(req.getParameter("name"),
                req.getParameter("licence_number"),
                req.getParameter("login"),
                req.getParameter("password"));
        driverService.create(driver);
        resp.sendRedirect("/login");
    }
}
