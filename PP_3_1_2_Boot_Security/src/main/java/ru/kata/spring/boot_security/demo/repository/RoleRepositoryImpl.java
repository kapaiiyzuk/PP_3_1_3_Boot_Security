package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public Role getRoleByName(String name) {
        Role role = null;
        try{
            role = getEntityManager().createQuery("select r from Role r where r.name =: name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public Role getRoleById(Long id) {
        return getEntityManager().find(Role.class, id);
    }

    @Override
    public List<Role> allRoles() {
        return getEntityManager().createQuery("select r from Role r", Role.class)
                .getResultList();
    }

    @Override
    public Role getDefaultRole() {
        return getRoleByName("ROLE_USER");
    }
}
