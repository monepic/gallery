package com.monepic.gallery.conf;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monepic.gallery.obj.Album;
import com.monepic.gallery.obj.Image;
import com.monepic.gallery.repo.AlbumRepository;
import com.monepic.gallery.repo.ImageRepository;

@Component
public class StorageConfig {

    private static final Logger LOG = LoggerFactory.getLogger(StorageConfig.class);

    public static String BASE_DIR = "/srv/albums";

    @Autowired AlbumRepository albumRepository;
    @Autowired ImageRepository imageRepository;

    @PostConstruct
    public void initializeData() {

        new Thread() {
            public void run() {
                LOG.info("Starting loading files");

                File basedir = new File(BASE_DIR);

                LinkedList<Image> newAnonymousImages = new LinkedList<Image>();

                for (File f : basedir.listFiles()) {
                    if (f.isFile() 
                            && f.getName().endsWith("JPG")
                            && imageRepository.findByPath(f.getAbsolutePath()) == null) { 

                        newAnonymousImages.add(Image.fromFile(f)); 

                    } else if (f.isDirectory()) {
                        Album a = toAlbum(f);
                        albumRepository.save(a);
                        LOG.info("Saved album {}", a);
                    }
                }

                if (!newAnonymousImages.isEmpty()) {

                    Album a = albumRepository.findByPath(BASE_DIR);

                    if (a == null) { a = new Album(); a.setName("Untitled"); }

                    a.getImages().addAll(newAnonymousImages);

                    albumRepository.save(a);

                    LOG.info("Saved untitled album {}", a);
                }

                LOG.info("** Image load completed **");
            }
        }.start();
    }

    private Album toAlbum(File directory) {

        Album a = albumRepository.findByPath(directory.getAbsolutePath());

        if (a == null) { a = new Album(); }

        a.setName(directory.getName());

        List<Image> images = a.getImages();

        for (File f : directory.listFiles()) {
            if (f.isFile() 
                    && f.getName().endsWith("JPG")
                    && imageRepository.findByPath(f.getAbsolutePath()) == null) {
                images.add(Image.fromFile(f)); 
            }
        }

        return a;
    }

}
