package service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import persistence.dao.UserDao;
import persistence.model.User;
import persistence.model.UserRole;

import java.util.HashSet;
import java.util.Set;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User registerNewAccount(String username, String password) {
        User user = null;
        if(!usernameExists(username)){
            user = getNewUser(username, password);
            userDao.addUser(user);
        }
        return user;
    }

    private User getNewUser(String username, String password) {
        User user;
        String encodedPassword = passwordEncoder.encode(password);
        user = new User(username, encodedPassword, true);
        Set<UserRole> userRoles = getUserRoles(user);
        user.setUserRoles(userRoles);
        return user;
    }

    private Set<UserRole> getUserRoles(User user) {
        Set<UserRole> userRoles = new HashSet<UserRole>();
        UserRole userRole = new UserRole(user, "ROLE_USER");
        userRoles.add(userRole);
        return userRoles;
    }

    private boolean usernameExists(String username){
        User u = userDao.findByUserName(username);
        return u != null;
    }
}
