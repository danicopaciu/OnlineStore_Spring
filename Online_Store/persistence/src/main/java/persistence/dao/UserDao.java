package persistence.dao;

import persistence.model.User;

import java.util.List;

public interface UserDao {

    public User findByUserName(String username);

    public void addUser(User u);

    public void removeUser(String username);

    public List<User> viewUsers();
}
