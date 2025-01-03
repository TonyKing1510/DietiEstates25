package it.unina.webtech.dao;

import it.unina.webtech.model.Image;

import java.io.File;
import java.util.List;

public interface ImageDao {
    void addImage(String assert_id);
    List<Image> getAllImages();
}
