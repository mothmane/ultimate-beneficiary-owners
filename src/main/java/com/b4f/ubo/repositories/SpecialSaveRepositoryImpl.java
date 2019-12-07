package com.b4f.ubo.repositories;

import com.b4f.ubo.domain.LegalPerson;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
@Transactional(readOnly = true)
public class SpecialSaveRepositoryImpl implements  SpecialSaveRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void specialSave(LegalPerson legalPerson) {
        entityManager.persist(legalPerson);
    }
}
