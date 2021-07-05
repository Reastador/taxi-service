package project.service;

import project.model.Driver;

import javax.naming.AuthenticationException;

public interface AuthenticationService {
    Driver login(String username, String password) throws AuthenticationException;
}
