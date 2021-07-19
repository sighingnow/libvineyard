package io.v6d.core;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.v6d.core.common.util.ObjectID;

/**
 * Unit test for simple App.
 */
public class IdTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        ObjectID id = new ObjectID(1000L);
        assertEquals(id, ObjectID.fromString(id.toString()));
    }
}
