package com.revature.quizzard.daos;

import com.revature.quizzard.util.List;

// CRUD: Create, Read, Update, Delete
public interface CrudDAO<T> {
    T save(T newObj);
    List<T> findAll();
    T findById(String id);
    boolean update(T updatedObj);
    boolean removeById(String id);
}
