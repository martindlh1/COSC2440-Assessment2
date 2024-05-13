/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.command;

import com.example.cosc2440assessment2.helper.Printer;
import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.repository.ClaimRepository;

public class PrintOneClaimCommand implements Command {
    private final ClaimRepository claimRepository = ClaimRepository.getInstance();

    @Override
    public void help() {
        Printer.hint("The 'printClaim' command display one detailed claim found by it's id");
        Printer.hint("USAGE:\n\tprintClaim id:integer");
    }

    @Override
    public Boolean exec(String[] params) {
        Number id = Long.parseLong(params[0]);
        Claim claim = claimRepository.getOne(id);
        if (claim == null) {
            Printer.error("Claim " + id + " not found.");
            return true;
        }
        Printer.result(claim.toDetailedString());
        return true;
    }

    @Override
    public boolean verifyParams(String[] params) {
        if (params.length < 1) {
            Printer.error("Command 'printClaim' take 1 parameter, type 'printClaim --h' to get more information.");
            return false;
        }
        try {
            Long.parseLong(params[0]);
        } catch (NumberFormatException e) {
            Printer.error("Parameter 'id' must be a number.");
            return false;
        }
        return true;
    }
}
