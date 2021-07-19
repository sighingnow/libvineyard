package io.v6d.core.common.util;

/**
 * Vineyard ObjectID definition.
 */
public class ObjectID
{
    public static ObjectID InvalidObjectID = new ObjectID(-1L);
    private long id = -1L;

    public ObjectID(long id) {
        this.id = id;
    }

    public static ObjectID fromString(String id) {
        return new ObjectID(Long.parseUnsignedLong(id.substring(1), 16));
    }

    @Override
    public String toString() {
        return String.format("o%016x", id);
    }

    @Override
    public boolean equals(Object other) {
        return this.id == ((ObjectID) other).id;
    }
}
