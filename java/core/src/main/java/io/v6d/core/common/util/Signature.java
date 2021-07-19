package io.v6d.core.common.util;

/**
 * Vineyard Signature definition.
 */
public class Signature
{
    public static Signature InvalidSignature = new Signature(-1L);
    private long id = -1L;

    public Signature(long id) {
        this.id = id;
    }

    public static Signature fromString(String id) {
        return new Signature(Long.parseUnsignedLong(id.substring(1), 16));
    }

    @Override
    public String toString() {
        return String.format("o%016x", id);
    }

    @Override
    public boolean equals(Object other) {
        return this.id == ((Signature) other).id;
    }
}
