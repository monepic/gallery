package com.monepic.gallery.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.ResourceSupport;

import com.monepic.api.AlbumController;
import com.monepic.api.Api;
import com.monepic.gallery.obj.Album;
import com.monepic.gallery.obj.Image;

public class AlbumResource extends ResourceSupport {

    public final UUID albumId;
    public final String name;

    public AlbumResource(UUID albumId, String name) {
        this.name = name;
        this.albumId = albumId;
    }

    public static AlbumResource fromAlbum(Album album) {

        List <ImageResource> images = new LinkedList<ImageResource>();

        for(Image image : album.getImages()) {
            images.add(ImageResource.fromImage(image));
        }

        AlbumResource resource = new AlbumResource(album.getId(), album.getName());
                resource.add(linkTo(AlbumController.class).slash(album).withSelfRel());
        resource.add(linkTo(AlbumController.class).slash(album).slash(Api.IMAGES).withRel(Api.IMAGES));
        return resource;
    }
}
