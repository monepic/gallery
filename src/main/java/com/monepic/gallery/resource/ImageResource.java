package com.monepic.gallery.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.monepic.api.ImageController;
import com.monepic.gallery.obj.Image;

public class ImageResource extends ResourceSupport {

    public final String name;
    public final String imageHref;


    public ImageResource(String name, Link href) {
        this.name = name;
        this.imageHref = href.getHref();
    }


    public static ImageResource fromImage(Image image) {

        Link href = linkTo(ImageController.class).slash(image).slash("src").withRel("src");

        ImageResource resource = new ImageResource(image.getName(), href);

        resource.add(linkTo(ImageController.class).slash(image).withSelfRel());

        return resource;
    }

    public static List<ImageResource> fromImages(Iterable<Image> images) {

        List<ImageResource> imageList = new LinkedList<ImageResource>();

        for(Image image : images) { imageList.add(fromImage(image)); }

        return imageList;
    }
}
