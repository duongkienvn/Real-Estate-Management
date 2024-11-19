package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    private void joinTable(StringBuilder sql, BuildingSearchRequest buildingSearch) {
        if (buildingSearch.getAreaFrom() != null || buildingSearch.getAreaTo() != null) {
            sql.append(" join rentarea r on b.id = r.buildingid ");
        }
        if (buildingSearch.getStaffId() != null) {
            sql.append(" join assignmentbuilding a on b.id = a.buildingid ");
        }
    }

    private boolean isValid(String fieldName) {
        return !fieldName.equals("staffId")
                && !fieldName.startsWith("area")
                && !fieldName.startsWith("rentPrice");
    }

    private void appendCondition(StringBuilder where, String fieldName, Object value, Class<?> typeClass) {
        if (typeClass.equals(Long.class)) {
            where.append(" and b.").append(fieldName).append(" = ").append(value);
        } else if (typeClass.equals(String.class) && !((String) value).trim().isEmpty()) {
            where.append(" and b.").append(fieldName).append(" like '%").append(value).append("%' ");
        }
    }

    public void queryNormal(StringBuilder where, BuildingSearchRequest buildingSearch) {
        try {
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (isValid(fieldName)) {
                    Object value = field.get(buildingSearch);
                    if (value != null) {
                        appendCondition(where, fieldName, value, field.getType());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void querySpecial(StringBuilder where, BuildingSearchRequest buildingSearch) {
        if (buildingSearch.getStaffId() != null) {
            where.append(" and a.staffid = " + buildingSearch.getStaffId());
        }

        Long rentPriceFrom = buildingSearch.getRentPriceFrom();
        Long rentPriceTo = buildingSearch.getRentPriceTo();
        if (rentPriceFrom != null || rentPriceTo != null) {
            if (rentPriceFrom != null) {
                where.append(" and b.rentprice >= " + rentPriceFrom);
            }
            if (rentPriceTo != null) {
                where.append(" and b.rentprice <= " + rentPriceTo);
            }
        }

        Long rentAreaFrom = buildingSearch.getAreaFrom();
        Long rentAreaTo = buildingSearch.getAreaTo();
        if (rentAreaFrom != null || rentAreaTo != null) {
            if (rentAreaFrom != null) {
                where.append(" and r.value >= " + rentAreaFrom);
            }
            if (rentAreaTo != null) {
                where.append(" and r.value <= " + rentAreaTo);
            }
        }
    }

    @Override
    public List<BuildingEntity> findBuilding(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sql = new StringBuilder("select b.* from building b");
        StringBuilder where = new StringBuilder(" where 1 = 1 ");
        joinTable(sql, buildingSearchRequest);
        queryNormal(where, buildingSearchRequest);
        querySpecial(where, buildingSearchRequest);
        where.append(" group by b.id ");
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);

        return query.getResultList();
    }
}
