/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.command;

import com.example.cosc2440assessment2.helper.Printer;
import com.example.cosc2440assessment2.repository.ClaimRepository;
import com.example.cosc2440assessment2.repository.CustomerRepository;
import com.example.cosc2440assessment2.repository.InsuranceCardRepository;

public class ExitCommand implements Command {
    private final ClaimRepository claimRepository = ClaimRepository.getInstance();
    private final CustomerRepository customerRepository = CustomerRepository.getInstance();
    private final InsuranceCardRepository insuranceCardRepository = InsuranceCardRepository.getInstance();

    @Override
    public void help() {
        Printer.hint("The 'exit' command exit properly the program after saving data");
        Printer.hint("USAGE:\n\texit");
    }

    @Override
    public Boolean exec(String[] params) {
        claimRepository.save();
        customerRepository.save();
        insuranceCardRepository.save();
        Printer.result("Data successfully save, goodbye !");
        return false;
    }

    @Override
    public boolean verifyParams(String[] params) {
        return true;
    }
}
