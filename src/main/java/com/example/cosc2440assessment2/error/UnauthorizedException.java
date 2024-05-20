/**
 * @author <Team 8>
 */
package com.example.cosc2440assessment2.error;

public class UnauthorizedException extends Exception {
    public UnauthorizedException(String error) {
        super(error);
    }
}
