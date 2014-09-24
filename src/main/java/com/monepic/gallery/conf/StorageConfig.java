package com.monepic.gallery.conf;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monepic.gallery.obj.Album;
import com.monepic.gallery.obj.Image;
import com.monepic.gallery.repo.AlbumRepository;
import com.monepic.gallery.repo.ImageRepository;
import com.monepic.gallery.util.ImageUtils;

@Component
public class StorageConfig {

    public static String BASE_DIR = "/srv/albums";

    @Autowired AlbumRepository albumRepository;
    @Autowired ImageRepository imageRepository;

    @PostConstruct
    public void initializeData() {

        new Thread() {
            public void run() {

                File basedir = new File(BASE_DIR);

                LinkedList<Image> anonymousImages = new LinkedList<Image>();

                for (File f : basedir.listFiles()) {
                    if (f.isFile() && f.getName().endsWith("JPG")) { anonymousImages.add(toImage(f)); }
                    else if (f.isDirectory()) {
                      albumRepository.save(toAlbum(f));
                    }
                }

                if (!anonymousImages.isEmpty()) {
                    Album a = new Album();
                    a.setName("Untitled");
                    a.setImages(anonymousImages);
                    albumRepository.save(a);
                }
            }
        }.start();
    }

    private Image toImage(File file) {
        Image image = new Image();
        image.setName(file.getName());
        image.setPath(file.getAbsolutePath());
        image.setThumbnail(ImageUtils.toThumb(image.getResource()));
        image.setMedium(ImageUtils.toMediumSize(image.getResource()));
        return image;
    }

    private Album toAlbum(File directory) {

        Album a = new Album();
        a.setName(directory.getName());

        List<Image> images = new LinkedList<Image>();

        for (File f : directory.listFiles()) {
            if (f.isFile() && f.getName().endsWith("JPG")) { images.add(toImage(f)); }
        }

        a.setImages(images);

        return a;
    }



}
