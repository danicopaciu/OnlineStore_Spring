package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import persistence.dao.UserDao;
import persistence.model.User;
import persistence.model.UserRole;
import utils.Constants;
import utils.UserUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final String MAKE_ADMIN = "makeAdmin";
    private static final String REMOVE_ADMIN = "removeAdmin";

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
    public void removeAccount(String username) {
        if (username != null) {
            userDao.removeUser(username);
        }
    }

    @Override
    @Transactional
    public boolean changePassword(String currentPassword, String newPassword, String newMatchingPassword) {
        org.springframework.security.core.userdetails.User user = UserUtils.getAuthenticatedUser();
        if (user != null) {
            String username = user.getUsername();
            User dbUser = userDao.findByUserName(username);
            if(passwordEncoder.matches(currentPassword, dbUser.getPassword())){
                if(newPassword.equals(newMatchingPassword)){
                    String encodedPassword = passwordEncoder.encode(newPassword);
                    dbUser.setPassword(encodedPassword);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    @Transactional
    public List<User> viewUsers() {
        return userDao.viewUsers();
    }

    @Override
    @Transactional
    public void changeAuthority(String username, String type) {
        if (username != null) {
            User u = userDao.findByUserName(username);
            if (type != null) {
                if (type.equals(MAKE_ADMIN)) {
                    for (UserRole userRole : u.getUserRoles()) {
                        if (userRole.getRole().equals(Constants.UserAuthority.ROLE_ADMIN)) {
                            return;
                        }
                    }
                    UserRole adminRole = new UserRole(u, Constants.UserAuthority.ROLE_ADMIN);
                    u.getUserRoles().add(adminRole);
                } else if (type.equals(REMOVE_ADMIN)) {
                    Set<UserRole> userRoles = u.getUserRoles();
                    for (UserRole userRole : userRoles) {
                        String role = userRole.getRole();
                        if (role.equals(Constants.UserAuthority.ROLE_ADMIN)) {
                            userRoles.remove(userRole);
                            break;
                        }
                    }
                }
            }
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
        UserRole userRole = new UserRole(user, Constants.UserAuthority.ROLE_USER);
        userRoles.add(userRole);
        return userRoles;
    }

    private boolean usernameExists(String username) {
        User u = userDao.findByUserName(username);
        return u != null;
    }
}
