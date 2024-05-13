/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.command;

import com.example.cosc2440assessment2.helper.Printer;

public class HelpCommand implements Command {
    @Override
    public void help() {
        Printer.hint("The 'help' command display a brief description of available commands");
        Printer.hint("USAGE:\n\thelp");
    }

    @Override
    public Boolean exec(String[] params) {
        Printer.hint("""
                USAGE:
                \tCOMMAND\t\t\tPARAMS\t\t\tDESCRIPTION
                \thelp\t\t\t\t\t\t\tdisplay available commands
                \texit\t\t\t\t\t\t\texit the program
                \tprintCustomers\t\t\t\t\tdisplay the customers list
                \tprintCustomer\tid:int\t\t\tdisplay one detailed customer
                \tprintClaims\t\t\t\t\t\tdisplay the claims list
                \tprintClaim\t\tid:int\t\t\tdisplay one detailed claim
                \tadd\t\t\t\t[...]\t\t\tcreate a new claim
                \tdelete\t\t\tid:int\t\t\tdelete one claim
                \tupdate\t\t\tid:int [...]\tupdate one claim
                [...] means multiple argument/options
                Add --h flags after a command to get more information""");
        return true;
    }

    @Override
    public boolean verifyParams(String[] params) {
        return true;
    }
}
