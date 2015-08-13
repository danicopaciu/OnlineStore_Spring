package persistence.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import persistence.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByUserName(String username) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.get(User.class, username);
    }

    @Override
    public void addUser(User u) {
        if (u != null) {
            Session session = sessionFactory.getCurrentSession();
            session.save(u);
        }
    }

    @Override
    public void removeUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        User u = (User) session.get(User.class, username);
        if (u != null) {
            session.delete(u);
        }
    }

    @Override
    public List<User> viewUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }
}
