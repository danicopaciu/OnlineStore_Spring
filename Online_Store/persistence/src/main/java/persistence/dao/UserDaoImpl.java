package persistence.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import persistence.model.User;

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
}
