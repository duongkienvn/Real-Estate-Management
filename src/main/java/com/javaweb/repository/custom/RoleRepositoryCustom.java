package com.javaweb.repository.custom;

import com.javaweb.entity.RoleEntity;

import java.util.List;

public interface RoleRepositoryCustom {
    RoleEntity findOneByCode(String code);
    List<RoleEntity> findAll();
}
