package com.monepic.gallery.obj;

import java.util.Objects;
import java.util.UUID;

import org.springframework.hateoas.Identifiable;


public class BaseObject implements Identifiable<UUID> {

    protected final UUID id = UUID.randomUUID();

    public UUID getId() { return id; }

    @Override
    public int hashCode() { return id.hashCode(); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (!getClass().equals(obj.getClass())) { return false; }

        return Objects.equals(this.getId(), ((BaseObject)obj).getId());
    }

    @Override
    public String toString() { 
        return String.format("%s(%d)", getClass(), id);
    }

}
