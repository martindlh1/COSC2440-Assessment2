/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.repository;

import com.example.cosc2440assessment2.model.Customer;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerRepository implements ProcessManager<Customer> {

    private final List<Customer> customers;
    private static final CustomerRepository instance = new CustomerRepository();

    public CustomerRepository() {
        try {
            Gson gson = new Gson();
            File file = new File("db/customer.json");
            Scanner myReader = new Scanner(file);
            customers = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                customers.add(gson.fromJson(data, Customer.class));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static CustomerRepository getInstance() {
        return instance;
    }

    @Override
    public void add(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void update(Customer customer) {
        customers.removeIf(c -> c.getId().equals(customer.getId()));
        customers.add(customer);
    }

    @Override
    public boolean delete(Customer customer) {
        return customers.remove(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customers;
    }

    @Override
    public Customer getOne(Number id) {
        for (Customer customer : customers) {
            if (customer.getId().toString().equals(id.toString()))
                return customer;
        }
        return null;
    }

    @Override
    public void save() {
        try {
            FileWriter myWriter = new FileWriter("db/customer.json");
            String data = "";
            for (Customer customer : customers) {
                data = data.concat(customer.toJson());
            }
            myWriter.write(data);
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
