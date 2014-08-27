package com.monepic.gallery.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.ResourceSupport;

import com.monepic.api.ImageController;
import com.monepic.gallery.obj.Image;

public class ImageResource extends ResourceSupport {

	public final String name;


	public ImageResource(String name) {
		this.name = name;
	}


	public static ImageResource fromImage(Image image) {
		ImageResource resource = new ImageResource(image.getName());

		resource.add(linkTo(ImageController.class).slash(image).withSelfRel());
		resource.add(linkTo(ImageController.class).slash(image).slash("src").withRel("src"));

		return resource;
	}
}
