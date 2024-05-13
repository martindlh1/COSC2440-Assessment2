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

public class CreateClaimCommand implements Command {
    private final ClaimRepository claimRepository = ClaimRepository.getInstance();
    private final CustomerRepository customerRepository = CustomerRepository.getInstance();

    @Override
    public void help() {
        Printer.hint("The 'add' command create a new claim");
        Printer.hint("USAGE:\n\tadd customerId:integer amount:integer exam_date:mm/dd/yyyy bank_info:bankName/holderName/cardNumber documents:doc1.pdf/doc2.pdf/etc..");
    }

    @Override
    public Boolean exec(String[] params) {
        Number amount = Long.parseLong(params[1]);
        Number customerId = Long.parseLong(params[0]);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date date;
        try {
            date = df.parse(params[2]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Customer customer = customerRepository.getOne(customerId);
        if (customer == null) {
            Printer.error("Customer " + customerId + " not found.");
            return true;
        }
        String[] bankInfoParams = params[3].split("/");
        BankInfo bankInfo = new BankInfo(bankInfoParams[0], bankInfoParams[1], Long.parseLong(bankInfoParams[2]));
        Claim claim = new Claim(customer, date, null, amount, bankInfo);
        String[] formattedDocs = null;
        if (params.length > 4) {
            String[] docs = params[4].split("/");
            formattedDocs = new String[docs.length];
            for (int i = 0; i < docs.length; i++) {
                formattedDocs[i] =  claim.getId() + "_" + claim.getCard_number() + "_" + docs[i];
            }
        }
        claim.setDoc(formattedDocs);
        customer.addClaim(claim);
        customerRepository.update(customer);
        claimRepository.add(claim);
        Printer.result("Claim " + claim.getId() + " successfully created !");
        return true;
    }

    @Override
    public boolean verifyParams(String[] params) {
        if (params.length < 4) {
            Printer.error("Command 'add' take 4 or 5 parameter, type 'add --h' to get more information.");
            return false;
        }

        try {
            Number id = Long.parseLong(params[0]);
            Customer customer = customerRepository.getOne(id);
            if (customer == null) {
                Printer.error("Customer " + id + " not found.");
                return false;
            }
            if (customer.getType() != CustomerType.DEPENDENT) {
                Printer.error("Customer " + id + " is a policy owner not a dependent.");
                return false;
            }
        } catch (NumberFormatException e) {
            Printer.error("Parameter 'customerId' must be a number, type 'add --h' to get more information.");
            return false;
        }
        try {
            Long.parseLong(params[1]);
        } catch (NumberFormatException e) {
            Printer.error("Parameter 'amount' must be a number, type 'add --h' to get more information.");
            return false;
        }
        try {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = df.parse(params[2]);
        } catch (ParseException e) {
            Printer.error("Parameter 'exam_date' must follow the format 'mm/dd/yyyy', type 'add --h' to get more information.");
            return false;
        }
        try {
            String[] bankInfo = params[3].split("/");
            if (bankInfo.length != 3) {
                Printer.error("Parameter 'bank_info' must follow the format 'bankName/holderName/cardNumber', type 'add --h' to get more information.");
                return false;
            }
            Long.parseLong(bankInfo[2]);
        } catch (NumberFormatException e) {
            Printer.error("3th part of parameter 'bank_info' (cardNumber) must be a number, type 'add --h' to get more information.");
            return false;
        }
        if (params.length > 4) {
            String[] docs = params[4].split("/");
            for (String doc : docs) {
                if (!doc.contains(".pdf")) {
                    Printer.error("Documents must be pdf, type 'add --h' to get more information.");
                    return false;
                }
            }
        }
        return true;
    }
}
