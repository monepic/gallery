package com.monepic.gallery.obj;

import javax.persistence.Entity;
import javax.persistence.Lob;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Entity
public class Image extends BaseObject {

    private String name;
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    private String path;
    public void setPath(String path) { this.path = path; }
    public String getPath() { return path; }

    public Resource getResource() { return new FileSystemResource(path); }

    @Lob
    private byte[] thumbnail;
    public void setThumbnail(byte[] thumbnail) { this.thumbnail = thumbnail; }
    public Resource getThumbnail() { return new ByteArrayResource(thumbnail); }

    @Lob
    private byte[] medium;
    public void setMedium(byte[] medium) { this.medium = medium; }
    public Resource getMedium() { return new ByteArrayResource(medium); }

 }
