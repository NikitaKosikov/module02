package com.epam.esm;

public interface TagDAO {
    void insert(String name);
    void update(String name);
    void delete(int id);
    void read();
}
