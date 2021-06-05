package com.epam.esm;

import java.util.List;

public interface TagService {
    void insert(Tag tag);
    void update(Tag tag, int id);
    void delete(int id);
    List<Tag> read();
    Tag readById(int id);
}
