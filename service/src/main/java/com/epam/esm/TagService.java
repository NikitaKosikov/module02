package com.epam.esm;

import java.util.List;

public interface TagService {

    /**
     * Insert tag in database.
     *
     * @param tag the tag.
     */
    void insert(Tag tag);

    /**
     *Update tag in database by specific id.
     *
     * @param tag the tag.
     * @param id the id of the tag.
     */
    void update(Tag tag, int id);

    /**
     * Delete the tag by specific id.
     *
     * @param id the id of the tag.
     */
    void delete(int id);

    /**
     * Looking for a set of tags.
     *
     * @return set of found tags.
     */
    List<Tag> read();

    /**
     * Looking for the tag by specific id.
     *
     * @param id the id of tag.
     * @return found tag.
     */
    Tag readById(int id);
}
