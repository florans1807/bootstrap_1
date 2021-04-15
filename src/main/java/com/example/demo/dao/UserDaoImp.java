package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAll() {
        TypedQuery<User> typedQuery = em.createQuery("from User", User.class);
        return typedQuery.getResultList();
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    public User loadUserByUsername(String username) {
        return em.createQuery("FROM User user WHERE user.username = :username", User.class)
                .setParameter("username", username).getSingleResult();
    }

}