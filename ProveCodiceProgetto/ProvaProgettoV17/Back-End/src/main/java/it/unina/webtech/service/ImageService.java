package it.unina.webtech.service;

import it.unina.webtech.dao.ImageDaoImpl;

public class ImageService {

    private final ImageDaoImpl imageDao;

    public ImageService() {
        this.imageDao = new ImageDaoImpl();
    }

    // Metodo per aggiungere un'immagine
    public void addImage(String file) {
        if (file != null && !file.isEmpty()) {
            imageDao.addImage(file);  // Usa il DAO per inserire l'immagine nel DB
        } else {
            System.out.println("Il file non è valido.");
        }
    }

}
