package com.monepic.gallery.obj;

import java.util.List;

public class Album extends BaseObject {
	
	private List<Image> images;
	
	public void setImages(List<Image> images) { this.images = images; }
	public List<Image> getImages() { return images; }
	
	private String name;
	
	public void setName(String name) { this.name = name; }
	public String getName() { return name; }
	
}
