package com.hlorka.domain;

/**
 * Created by lbilenki on 1/13/2017.
 */
public abstract class ObjectWithId {
    protected final int id;

    protected ObjectWithId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectWithId that = (ObjectWithId) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
