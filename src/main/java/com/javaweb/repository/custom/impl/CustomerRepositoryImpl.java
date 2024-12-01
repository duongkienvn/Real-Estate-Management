package com.javaweb.repository.custom.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    public void joinTable(StringBuilder sql, CustomerSearchRequest customerSearchRequest) {
        if (customerSearchRequest.getStaffId() != null) {
            sql.append(" join assignmentcustomer ac on ac.customerid = c.id");
        }
    }

    public void query(StringBuilder where, CustomerSearchRequest customerSearchRequest) {
        if (customerSearchRequest.getStaffId() != null) {
            where.append(" and ac.staffid = " + customerSearchRequest.getStaffId());
        }

        String name = customerSearchRequest.getCustomerName();
        if (name != null) {
            where.append(" and c.fullname like '%" + name + "%' ");
        }

        String phone = customerSearchRequest.getCustomerPhoneNumber();
        if (phone != null) {
            where.append(" and c.phone like '%" + phone + "%' ");
        }

        String email = customerSearchRequest.getCustomerEmail();
        if (email != null) {
            where.append(" and c.email like '%" + email + "%' ");
        }
    }

    @Override
    public List<CustomerEntity> findCustomer(CustomerSearchRequest customerSearchRequest) {
        StringBuilder sql = new StringBuilder("select c.* from customer c ");
        StringBuilder where = new StringBuilder(" where 1 = 1 and c.is_active = 1 ");
        joinTable(sql, customerSearchRequest);
        query(where, customerSearchRequest);
        where.append(" group by c.id ");
        sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }
}
