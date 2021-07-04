package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagService {

    /**
     * Create new tag
     *
     * @param tag the tag which will be created
     * @return created tag
     */
    Tag insert(Tag tag);

    /**
     * Update tag
     *
     * @param tag the new tag which will be created
     * @param id the id of tag which should be updated
     */
    void update(Tag tag, long id);

    /**
     * Delete tag by id
     *
     * @param id the id of tag which will be delete
     */
    void delete(long id);

    /**
     * Looking for all tags
     *
     * @return the found list of tags
     */
    List<Tag> read();

    /**
     * Looking for tag by id
     *
     * @param id the id of tag
     * @return the found tag
     */
    Tag readById(long id);

    /**
     * Looking for  tag by name
     *
     * @param tagName the name of tag by which we are looking for tag
     * @return the found tag
     */
    Tag findTagByName(String tagName);

    /**
     * Looking for  all tags by specific gift certificate
     * @param id the id of gift certificate
     * @return the found list of tags
     */
    List<Tag> readByCertificateId(long id);

}
