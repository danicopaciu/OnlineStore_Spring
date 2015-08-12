package persistence.dao;

import persistence.model.User;

public interface UserDao {

    public User findByUserName(String username);

    public void addUser(User u);
}
