package com.contoso.dummydragon;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class UtilsTests {
    @Test
    public void testPrime() {
        assertEquals(false, Utils.isPrime(0));
        assertEquals(false, Utils.isPrime(1));
        assertEquals(true, Utils.isPrime(2));
        assertEquals(true, Utils.isPrime(3));
        assertEquals(false, Utils.isPrime(4));
        assertEquals(true, Utils.isPrime(10037));
    }
}
