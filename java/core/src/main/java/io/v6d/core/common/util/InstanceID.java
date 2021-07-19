package io.v6d.core.common.util;

/**
 * Vineyard InstanceID definition.
 */
public class InstanceID
{
    public static InstanceID UnspecifiedInstanceID = new InstanceID(-1L);
    private long id = -1L;

    public InstanceID(long id) {
        this.id = id;
    }

    public static InstanceID fromString(String id) {
        return new InstanceID(Long.parseUnsignedLong(id.substring(1), 16));
    }

    @Override
    public String toString() {
        return String.format("o%016x", id);
    }

    @Override
    public boolean equals(Object other) {
        return this.id == ((InstanceID) other).id;
    }
}
