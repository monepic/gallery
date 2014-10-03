package com.monepic.gallery.obj;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Album extends BaseObject {

    @Column(unique = true)
    private String path;
    public void setPath(String path) { this.path = path; }
    public String getPath() { return path; }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Image> images = new LinkedList<Image>();
    public void setImages(List<Image> images) { this.images = images; }
    public List<Image> getImages() { return images; }

    private String name;
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

}
