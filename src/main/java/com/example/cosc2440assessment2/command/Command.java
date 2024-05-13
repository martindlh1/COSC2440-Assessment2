/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.command;

public interface Command {
    void help();
    Boolean exec(String[] params);
    boolean verifyParams(String[] params);
}
