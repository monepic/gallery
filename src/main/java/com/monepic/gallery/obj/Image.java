package com.monepic.gallery.obj;

import java.io.File;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.monepic.gallery.util.ImageUtils;

@Entity
public class Image extends BaseObject {

    private String name;
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    @Column(unique = true)
    private String path;
    public void setPath(String path) { this.path = path; }
    public String getPath() { return path; }

    public Resource getResource() { return new FileSystemResource(path); }

    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[] thumbnail;
    public void setThumbnail(byte[] thumbnail) { this.thumbnail = thumbnail; }
    public Resource getThumbnail() { return new ByteArrayResource(thumbnail); }


    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[] medium;
    public void setMedium(byte[] medium) { this.medium = medium; }
    public Resource getMedium() { return new ByteArrayResource(medium); }


    public static Image fromFile(File file) {
        Image image = new Image();
        image.setName(file.getName());
        image.setPath(file.getAbsolutePath());
        image.setThumbnail(ImageUtils.toThumb(image.getResource()));
        image.setMedium(ImageUtils.toMediumSize(image.getResource()));
        return image;
    }

}
