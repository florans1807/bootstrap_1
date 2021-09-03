package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> typedQuery = em.createQuery("from Role", Role.class);
        return typedQuery.getResultList();
    }

    @Override
    public Role findRoleByName(String role) {
        return em.createQuery("FROM Role role WHERE role.role = :role", Role.class)
                .setParameter("role", role).getSingleResult();
    }

    @Override
    public Set<Role> getSetOfRolesFromList(String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String r : roles) {
            roleSet.add(findRoleByName(r));
        }
        return roleSet;
    }
}
