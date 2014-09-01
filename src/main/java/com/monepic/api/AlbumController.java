package com.monepic.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.monepic.gallery.obj.Album;
import com.monepic.gallery.resource.AlbumResource;
import com.monepic.gallery.resource.ImageResource;
import com.monepic.gallery.service.AlbumService;

@Controller
@RequestMapping(Api.ROOT + Api.ALBUMS)
@ExposesResourceFor(AlbumResource.class)
public class AlbumController {

    @Autowired AlbumService albumService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<AlbumResource> albums() {

        List<AlbumResource> albums = new ArrayList<AlbumResource>();

        for (Album album : albumService.listAlbums()) {
            albums.add(AlbumResource.fromAlbum(album));	    
        }

        return albums;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<AlbumResource> get(@PathVariable UUID id) {

        Album album = albumService.getAlbumById(id);

        if (album == null) { return new ResponseEntity<AlbumResource>(HttpStatus.NOT_FOUND); }

        return new ResponseEntity<AlbumResource>(AlbumResource.fromAlbum(album), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}/" + Api.IMAGES, method = RequestMethod.GET)
    public ResponseEntity<List<ImageResource>> getImages(@PathVariable UUID id) {

        Album album = albumService.getAlbumById(id);

        if (album == null) { return new ResponseEntity<List<ImageResource>>(HttpStatus.NOT_FOUND); }

        return new ResponseEntity<List<ImageResource>>(ImageResource.fromImages(album.getImages()), HttpStatus.OK);
    }
}
