/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.command;

import com.example.cosc2440assessment2.helper.Printer;
import com.example.cosc2440assessment2.model.BankInfo;
import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.Customer;
import com.example.cosc2440assessment2.model.CustomerType;
import com.example.cosc2440assessment2.repository.ClaimRepository;
import com.example.cosc2440assessment2.repository.CustomerRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateCommand implements Command {
    private final ClaimRepository claimRepository = ClaimRepository.getInstance();
    private final CustomerRepository customerRepository = CustomerRepository.getInstance();

    @Override
    public void help() {
        Printer.hint("The 'update' command update the selected claim");
        Printer.hint("USAGE:\n\tupdate id:integer option=option_value");
        Printer.hint("OPTIONS:\n\tamount:integer\n\tinsured:integer\n\texam_date:mm/dd/yyyy\n\tbank_name:string\n\tcard_holder:string\n\tcard_number");
    }

    @Override
    public Boolean exec(String[] params) {
        Number id = Long.parseLong(params[0]);
        Claim claim = claimRepository.getOne(id);
        if (claim == null) {
            Printer.error("Claim " + id + " not found.");
            return true;
        }
        BankInfo bankInfo = claim.getBankInfo();
        if (bankInfo == null)
            bankInfo = new BankInfo();
        if (params.length == 1) {
            Printer.result("Nothing to update.");
            return true;
        }
        for (int i = 1; i < params.length; i+=1) {
            String param = params[i].split("=")[0];
            String value = params[i].split("=")[1];
            switch (param) {
                case "amount":
                    claim.setAmount(Long.parseLong(value));
                case "insured":
                    Customer oldInsured = customerRepository.getOne(claim.getInsured());
                    Customer newInsured = customerRepository.getOne(Long.parseLong(value));
                    oldInsured.removeClaim(claim);
                    newInsured.addClaim(claim);
                    customerRepository.update(oldInsured);
                    customerRepository.update(newInsured);
                    claim.setInsured(Long.parseLong(value));
                    claim.setCard_number(newInsured.getInsurance_card());
                case "exam_date":
                    try {
                        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                        Date date = df.parse(value);
                        claim.setExam_date(date);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                case "bank_name":
                    bankInfo.setBank(value);
                    break;
                case "card_holder":
                    bankInfo.setName(value);
                    break;
                case "card_number":
                    bankInfo.setNumber(Long.parseLong(value));
                    break;
            }
        }
        claim.setBankInfo(bankInfo);
        claimRepository.update(claim);
        Printer.result("Claim successfully updated !");
        return true;
    }

    @Override
    public boolean verifyParams(String[] params) {
        if (params.length < 1) {
            Printer.error("Command 'update' take at least 1 parameter, type 'update --h' to get more information.");
            return false;
        }
        try {
            Long.parseLong(params[0]);
        } catch (NumberFormatException e) {
            Printer.error("Parameter 'id' must be a number.");
            return false;
        }
        for (int i = 1; i < params.length; i+=1) {
            String[] parsedParams = params[i].split("=");
            if (parsedParams.length != 2) {
                Printer.error("Invalid option '" + params[i] + "', options must have the format option=option_value, type 'update --h' top get more information.");
                return false;
            }
            String param = parsedParams[0];
            String value = parsedParams[1];
            if (param.isEmpty() || value.isEmpty()) {
                Printer.error("Option or Option Value is missing, type 'update --h' top get more information.");
                return false;
            }
            switch (param) {
                case "amount":
                    try {
                        Long.parseLong(value);
                        continue;
                    } catch (NumberFormatException e) {
                        Printer.error("Option value for 'amount' must be a number.");
                        return false;
                    }
                case "insured":
                    try {
                        Number id = Long.parseLong(value);
                        Customer customer = customerRepository.getOne(id);
                        if (customer == null) {
                            Printer.error("Customer " + id + " not found.");
                            return false;
                        }
                        if (customer.getType() != CustomerType.DEPENDENT) {
                            Printer.error("Customer " + id + " is a policy owner not a dependent.");
                            return false;
                        }
                        continue;
                    } catch (NumberFormatException e) {
                        Printer.error("Option value for 'insured' must be a number.");
                        return false;
                    }
                case "exam_date":
                    try {
                        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                        Date date = df.parse(value);
                        continue;
                    } catch (ParseException e) {
                        Printer.error("Option value for 'exam_date' must follow the format 'mm/dd/yyyy'.");
                        return false;
                    }
                case "card_number":
                    try {
                        Long.parseLong(value);
                    } catch (NumberFormatException e) {
                        Printer.error("Option value for 'card_number' must be a number.");
                        return false;
                    }
                case "bank_name":
                    continue;
                case "card_holder":
                    continue;
                default:
                    Printer.error("Unknown parameter, type 'update --h' top get more information.");
                    return false;
            }
        }
        return true;

    }
}
