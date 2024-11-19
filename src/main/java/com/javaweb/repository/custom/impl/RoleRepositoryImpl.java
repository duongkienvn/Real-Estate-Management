package com.javaweb.repository.custom.impl;

import com.javaweb.entity.RoleEntity;
import com.javaweb.repository.custom.RoleRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class RoleRepositoryImpl implements RoleRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public RoleEntity findOneByCode(String code) {
        String sql = "select * FROM role as r where r.code = '" + code + "'";
        Query query = entityManager.createNativeQuery(sql, RoleEntity.class);
        return (RoleEntity) query.getSingleResult();
    }

    @Override
    public List<RoleEntity> findAll() {
        String sql = "select * FROM role as r";
        Query query = entityManager.createNativeQuery(sql, RoleEntity.class);
        return query.getResultList();
    }
}