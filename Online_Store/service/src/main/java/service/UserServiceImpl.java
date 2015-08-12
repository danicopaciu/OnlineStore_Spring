package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import persistence.dao.UserDao;
import persistence.model.User;
import persistence.model.UserRole;
import utils.UserUtils;

import java.util.HashSet;
import java.util.Set;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User registerNewAccount(String username, String password) {
        User user = null;
        if (!usernameExists(username)) {
            user = getNewUser(username, password);
            userDao.addUser(user);
        }
        return user;
    }

    @Override
    @Transactional
    public void removeAccount() {
        org.springframework.security.core.userdetails.User user = UserUtils.getAuthenticatedUser();
        if (user != null) {
            userDao.removeUser(user.getUsername());
        }
    }

    @Override
    @Transactional
    public void changePassword(String newPassword) {
        org.springframework.security.core.userdetails.User user = UserUtils.getAuthenticatedUser();
        if (user != null) {
            String username = user.getUsername();
            User dbUser = userDao.findByUserName(username);
            String encodedPassword = passwordEncoder.encode(newPassword);
            dbUser.setPassword(encodedPassword);
        }
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

    private boolean usernameExists(String username) {
        User u = userDao.findByUserName(username);
        return u != null;
    }
}
