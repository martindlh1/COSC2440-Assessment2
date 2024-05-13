/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.command;

import com.example.cosc2440assessment2.helper.Printer;
import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.repository.ClaimRepository;

public class PrintClaimsCommand implements Command {
    private final ClaimRepository claimRepository = ClaimRepository.getInstance();

    @Override
    public void help() {
        Printer.hint("The 'printClaims' command display the claims list");
        Printer.hint("USAGE:\n\tprintClaims");
    }

    @Override
    public Boolean exec(String[] params) {
        for (Claim claim : claimRepository.getAll()) {
            Printer.result(claim.toString());
        }
        return true;
    }

    @Override
    public boolean verifyParams(String[] params) {
        return true;
    }
}
