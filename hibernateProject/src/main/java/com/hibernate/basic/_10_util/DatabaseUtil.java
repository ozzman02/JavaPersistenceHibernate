package com.hibernate.basic._10_util;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DatabaseUtil {

    public static <T> void clear(Session session, Class<T> className) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(className);
        criteria.from(className);
        List<?> instances = session.createQuery(criteria).getResultList();
        for (Object obj : instances) {
            session.delete(obj);
        }
    }

}
