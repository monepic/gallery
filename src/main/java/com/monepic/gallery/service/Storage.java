package com.monepic.gallery.service;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.monepic.gallery.obj.Album;
import com.monepic.gallery.obj.Image;
import com.monepic.gallery.util.ThumbnailUtils;

@Service
public class Storage implements AlbumService, ImageService {

    private HashMap<UUID, Album> albumMap = new HashMap<UUID, Album>();
    private HashMap<UUID, Image> imageMap = new HashMap<UUID, Image>();

    public static String BASE_DIR = "/srv/albums";


    @PostConstruct
    public void init() {

        File basedir = new File(BASE_DIR);

        List<Album> albums = new LinkedList<Album>();

        LinkedList<Image> anonymousImages = new LinkedList<Image>();

        for (File f : basedir.listFiles()) {
            if (f.isFile()) { anonymousImages.add(toImage(f)); }
            else if (f.isDirectory()) {
                albums.add(toAlbum(f));
            }
        }

        if (!anonymousImages.isEmpty()) {
            Album a = new Album();
            a.setName("Untitled");
            a.setImages(anonymousImages);
            albums.add(a);
        }

        for (Album album : albums) {
            albumMap.put(album.getId(), album);
            for (Image image : album.getImages()) {
                imageMap.put(image.getId(),image);
            }
        }
    }

    private Image toImage(File file) {
        Image image = new Image();
        image.setName(file.getName());
        image.setResource(new FileSystemResource(file));
        image.setThumbnail(ThumbnailUtils.getThumb(image.getResource()));
        return image;
    }

    private Album toAlbum(File directory) {

        Album a = new Album();
        a.setName(directory.getName());

        List<Image> images = new LinkedList<Image>();

        for (File f : directory.listFiles()) {
            if (f.isFile()) { images.add(toImage(f)); }
        }

        a.setImages(images);

        return a;
    }



    @Override
    public Image getImageById(UUID id) {
        return imageMap.get(id);
    }

    @Override
    public List<Album> listAlbums() {
        return new LinkedList<Album>(albumMap.values());
    }

    @Override
    public Album getAlbumById(UUID id) {
        return albumMap.get(id);
    }

}
