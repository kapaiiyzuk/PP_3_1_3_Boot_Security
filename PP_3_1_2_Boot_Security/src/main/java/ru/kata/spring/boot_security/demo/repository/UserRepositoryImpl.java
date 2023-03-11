package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
    @Override
    public void addUser(User user) {
        getEntityManager().persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        try{
            User user = getEntityManager().find(User.class, id);
            if(user != null) {
                getEntityManager().remove(id);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void editUser(User user) {
        getEntityManager().merge(user);
    }

    @Override
    public User getUserById(Long id) {
        return getEntityManager().find(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        return getEntityManager().find(User.class, username);
    }

    @Override
    public List<User> getAllUsers() {
        return getEntityManager().createQuery("select u from User u", User.class)
                .getResultList();
    }
}
