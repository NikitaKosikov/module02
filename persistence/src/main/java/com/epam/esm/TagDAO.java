package com.epam.esm;

import java.util.List;

public interface TagDAO {
    void insert(String name);
    void update(String name, int id);
    void delete(int id);
    List<Tag> read();
}
