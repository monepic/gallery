package com.monepic.gallery.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.monepic.api.AlbumController;
import com.monepic.gallery.obj.Album;
import com.monepic.gallery.obj.Image;

public class AlbumResource extends ResourceSupport {

	public final String name;
	public final List<ImageResource> images;

	public AlbumResource(String name, List<ImageResource> images) {
		this.name = name;
		this.images = images;
	}

	public static AlbumResource fromAlbum(Album album) {

		List <ImageResource> images = new LinkedList<ImageResource>();

		for(Image image : album.getImages()) {
			images.add(ImageResource.fromImage(image));
		}

		AlbumResource resource = new AlbumResource(album.getName(), images);
		resource.add(linkTo(AlbumController.class).slash(album).withSelfRel());

		return resource;
	}
}
