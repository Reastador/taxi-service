package project.service;

import project.lib.Injector;
import project.lib.Service;
import project.model.Driver;

import java.util.Optional;
import javax.naming.AuthenticationException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Injector injector = Injector.getInstance("project");
    private final DriverService driverService =
            (DriverService) injector
                    .getInstance(DriverService.class);

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Optional<Driver> driver = driverService.findByLogin(login);
        if (driver.isPresent() && driver.get().getPassword().equals(password)) {
            return driver.get();
        }
        throw new AuthenticationException("Login or password was incorrect!");
    }
}
