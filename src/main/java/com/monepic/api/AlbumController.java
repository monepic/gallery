package com.monepic.api;

import java.util.ArrayList;
import java.util.List;

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
import com.monepic.gallery.repo.AlbumRepository;
import com.monepic.gallery.resource.AlbumResource;
import com.monepic.gallery.resource.ImageResource;

@Controller
@RequestMapping(Api.ROOT + Api.ALBUMS)
@ExposesResourceFor(AlbumResource.class)
public class AlbumController {

    @Autowired AlbumRepository albumRepository;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<AlbumResource> albums() {

        List<AlbumResource> albums = new ArrayList<AlbumResource>();

        for (Album album : albumRepository.findAll()) {
            albums.add(AlbumResource.fromAlbum(album));
        }

        return albums;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<AlbumResource> get(@PathVariable long id) {

        Album album = albumRepository.findOne(id);

        if (album == null) { return new ResponseEntity<AlbumResource>(HttpStatus.NOT_FOUND); }

        return new ResponseEntity<AlbumResource>(AlbumResource.fromAlbum(album), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}/" + Api.IMAGES, method = RequestMethod.GET)
    public ResponseEntity<List<ImageResource>> getImages(@PathVariable long id) {

        Album album = albumRepository.findOne(id);

        if (album == null) { return new ResponseEntity<List<ImageResource>>(HttpStatus.NOT_FOUND); }

        return new ResponseEntity<List<ImageResource>>(ImageResource.fromImages(album.getImages()), HttpStatus.OK);
    }
}
