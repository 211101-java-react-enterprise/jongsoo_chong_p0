package com.revature.banking.daos;

import com.revature.banking.util.collections.List;

// CRUD: Create, Read, Update, Delete
public interface CrudDAO<T> {
    T save(T newObj);
    List<T> findAll();
    T findById(String id);
    boolean update(T updatedObj);
    boolean removeById(String id);
}
