package com.hibernate.advanced.preinsertidentifiergeneration.service.impl;

import com.hibernate.advanced.preinsertidentifiergeneration.entity.GenerationTypeSequenceStudent;
import com.hibernate.advanced.preinsertidentifiergeneration.entity.GenerationTypeTableStudent;
import com.hibernate.advanced.preinsertidentifiergeneration.service.PreInsertIdentifierGenerationService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static com.hibernate.advanced.constants.Constants.PERSISTENCE_UNIT_NAME;

public class PreInsertIdentifierGenerationServiceImpl implements PreInsertIdentifierGenerationService {

    @Override
    public void createStudentsWithGenerationTypeSequence() {
        /*
            If generation strategy is SEQUENCE and If the property hibernate.hbm2ddl.auto is set to "create",
            hibernate will drop and create a schema when an EntityManagerFactory is created.
            The following sql statements are issued when line 21 is executed:
                drop table if exists hibernate_sequence;
                drop table if exists IdentifierGenerationStudent;
                create table hibernate_sequence (next_val bigint);
                insert into hibernate_sequence values (1);
                create table IdentifierGenerationStudent (id bigint not null, enrollment_id varchar(255) not null, name varchar(255), primary key(id));
                alter table IdentifierGenerationStudent add constraint UK_polj93m8xf0b8k5nejmjkeapg unique (enrollment_id);
        */
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        /* EntityManager represents a persistence context */
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            /* student1 will be in transient state and id is null */
            GenerationTypeSequenceStudent student = new GenerationTypeSequenceStudent("2003CH50897","Kevin Smith");

            /* student2 will be in transient state and id is null */
            GenerationTypeSequenceStudent student2 = new GenerationTypeSequenceStudent("2008EE10999","Sherry Morgan");

            /* student3 will be in transient state and id is null */
            GenerationTypeSequenceStudent student3 = new GenerationTypeSequenceStudent("1987CS10007","Sara Shield");

            /*
                student1 will be moved from transient to the persistence state and the value 1 is taken from the hibernate_sequence
                sql statements issued:
                    select next_val as id_val from hibernate_sequence for update
                    update hibernate_sequence set next_val = ? where next_val = ?
            */
            entityManager.persist(student);

            /*
                student2 will be moved from transient to the persistence state and the value 2 is taken from the hibernate_sequence
                sql statements issued:
                    select next_val as id_val from hibernate_sequence for update
                    update hibernate_sequence set next_val = ? where next_val = ?
            */
            entityManager.persist(student2);

            /*
                student3 will be moved from transient to the persistence state and the value 3 is taken from the hibernate_sequence
                sql statements issued:
                    select next_val as id_val from hibernate_sequence for update
                    update hibernate_sequence set next_val = ? where next_val = ?
                final value of hibernate_sequence is 4
            */
            entityManager.persist(student3);

            /* insert statements are issued */
            entityTransaction.commit();
        } catch (Exception ex) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void createStudentsWithGenerationTypeTable() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            GenerationTypeTableStudent student = new GenerationTypeTableStudent("2003CH50897","Kevin Smith");
            GenerationTypeTableStudent student2 = new GenerationTypeTableStudent("2008EE10999","Sherry Morgan");
            GenerationTypeTableStudent student3 = new GenerationTypeTableStudent("1987CS10007","Sara Shield");
            entityManager.persist(student);     // insert statement not issued, just ID assigned
            entityManager.persist(student2);    // insert statement not issued, just ID assigned
            entityManager.persist(student3);    // insert statement not issued, just ID assigned
            entityTransaction.commit();         // insert statements issued
        } catch (Exception ex) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

}
