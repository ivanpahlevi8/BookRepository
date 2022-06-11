package com.example.thymeleafdemo.repository;

import java.util.Collection;

public interface CommonRepository<T> {
    public Iterable<T> findAll();
    public T findById(String id);
    public T save(T domain);
    public T findByIsbn(String isbn);
    public T findByTitle(String title);
    public T findByReader(String reader);
    public T delter(T domain);
    public Iterable<T> save(Collection<T> domain);
}
