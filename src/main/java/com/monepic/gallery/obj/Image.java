package com.monepic.gallery.obj;

import org.springframework.core.io.Resource;

public class Image extends BaseObject {

    private String name;
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    private Resource resource;
    public void setResource(Resource resource) { this.resource = resource; }
    public Resource getResource() { return resource; }

    private Resource thumbnail;
    public void setThumbnail(Resource thumbnail) { this.thumbnail = thumbnail; }
    public Resource getThumbnail() { return thumbnail; }
    
}
