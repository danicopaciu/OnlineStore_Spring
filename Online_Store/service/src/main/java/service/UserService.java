package service;

import persistence.model.User;

public interface UserService {

    public User registerNewAccount(String username, String password);
}
