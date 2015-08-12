package service;

import persistence.model.User;

public interface UserService {

    public User registerNewAccount(String username, String password);

    public void removeAccount();

    public void changePassword(String newPassword);
}
