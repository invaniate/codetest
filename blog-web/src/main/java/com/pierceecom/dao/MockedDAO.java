package com.pierceecom.dao;

import com.google.common.collect.Lists;
import com.pierceecom.general.abstracts.AbstractEntity;
import com.pierceecom.general.exceptions.PierceNoResultException;

import javax.ejb.Singleton;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/*********************
 * Created by Alex S
 * on 20.09.2017
 ********************/

@Singleton
public class MockedDAO<T extends AbstractEntity> {

    private List<T> mockedDB;
    private Long idTracker;

    public MockedDAO() {
        this.mockedDB = Lists.newArrayList();
        this.idTracker = 0L;
    }

    public List<T> getAll() {
        return mockedDB;
    }

    public T get(Long id) throws PierceNoResultException {
        return findOrThrow(id, new PierceNoResultException(Response.Status.NO_CONTENT));
    }

    public T persist(T entity){
        entity.setId(getIncrementedId());
        mockedDB.add(entity);

        return entity;
    }

    public T delete(Long id) throws PierceNoResultException {
        T found = findOrThrow(id, new PierceNoResultException(Response.Status.NOT_FOUND));

        mockedDB = mockedDB.stream()
                .filter(entity -> {
                    return Long.compare(entity.getId(), found.getId()) != 0;
                })
                .collect(Collectors.toList());

        return found;
    }

    protected T findOrThrow(Long id, PierceNoResultException ex) throws PierceNoResultException {
        return mockedDB.stream()
                .filter(entity -> {
                    return Long.compare(entity.getId(), id) == 0;
                })
                .findFirst()
                .orElseThrow(() -> {
                    return ex;
                });
    }

    protected Long getIncrementedId(){
        idTracker++;

        return idTracker;
    }
}
