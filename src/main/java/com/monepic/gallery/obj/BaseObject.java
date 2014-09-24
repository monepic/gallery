package com.monepic.gallery.obj;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.hateoas.Identifiable;

@MappedSuperclass
public class BaseObject implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected long id;// = UUID.randomUUID();


    public Long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    @Override
    public int hashCode() { return (int) (id ^ (id >>> 32)); }

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
