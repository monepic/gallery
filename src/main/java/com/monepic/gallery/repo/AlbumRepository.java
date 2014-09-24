package com.monepic.gallery.repo;

import com.monepic.gallery.obj.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Long> {

}
