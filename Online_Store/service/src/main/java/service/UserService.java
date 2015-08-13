package service;

import persistence.model.User;

import java.util.List;

public interface UserService {

    public User registerNewAccount(String username, String password);

    public void removeAccount();

    public void removeAccount(String username);

    public void changePassword(String newPassword);

    public List<User> viewUsers();

    public void changeAuthority(String username, String type);
}
