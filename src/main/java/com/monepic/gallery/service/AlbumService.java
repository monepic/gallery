package com.monepic.gallery.service;

import java.util.List;
import java.util.UUID;

import com.monepic.gallery.obj.Album;

public interface AlbumService {

    List<Album> listAlbums();

    Album getAlbumById(UUID id);

}
