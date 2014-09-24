package com.monepic.gallery.obj;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Album extends BaseObject {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images;
    public void setImages(List<Image> images) { this.images = images; }
    public List<Image> getImages() { return images; }

    private String name;

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

}
