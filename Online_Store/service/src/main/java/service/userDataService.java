package service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import persistence.dao.UserDao;
import persistence.model.User;
import persistence.model.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDataService implements UserDetailsService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByUserName(s);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoles());
        return buildUserAuthentication(user, authorities);
    }

    private UserDetails buildUserAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
       List<GrantedAuthority> authsList = new ArrayList<GrantedAuthority>();

        for(UserRole userRole : userRoles){
            authsList.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return authsList;
    }
}
