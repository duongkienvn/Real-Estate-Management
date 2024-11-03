package com.javaweb.repository;

import com.javaweb.entity.RoleEntity;
import com.javaweb.repository.custom.RoleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>, RoleRepositoryCustom {
    RoleEntity findOneByCode(String code);
}
