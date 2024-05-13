/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.helper;

public class IdGenerator {

    private static final long LIMIT10 = 10000000000L;
    private static long last10 = 0;

    private static final long LIMIT7 = 10000000L;
    private static long last7 = 0;

    public static Number generate10digitId() {
        long id = System.currentTimeMillis() % LIMIT10;
        if ( id <= last10 ) {
            id = (last10 + 1) % LIMIT10;
        }
        return last10 = id;
    }

    public static Number generate7digitId() {
        long id = System.currentTimeMillis() % LIMIT7;
        if ( id <= last7 ) {
            id = (last7 + 1) % LIMIT7;
        }
        return last7 = id;
    }
}
