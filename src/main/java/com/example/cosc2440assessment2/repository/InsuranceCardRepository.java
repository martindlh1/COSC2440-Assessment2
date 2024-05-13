/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.repository;

import com.example.cosc2440assessment2.model.InsuranceCard;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsuranceCardRepository implements ProcessManager<InsuranceCard> {
    private final List<InsuranceCard> insuranceCards;
    private static final InsuranceCardRepository instance = new InsuranceCardRepository();

    public InsuranceCardRepository() {
        try {
            Gson gson = new Gson();
            File file = new File("db/card.json");
            Scanner myReader = new Scanner(file);
            insuranceCards = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                insuranceCards.add(gson.fromJson(data, InsuranceCard.class));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static InsuranceCardRepository getInstance() {
        return instance;
    }

    @Override
    public void add(InsuranceCard insuranceCard) {
        insuranceCards.add(insuranceCard);
    }

    @Override
    public void update(InsuranceCard insuranceCard) {

    }

    @Override
    public boolean delete(InsuranceCard insuranceCard) {
        return insuranceCards.remove(insuranceCard);
    }

    @Override
    public List<InsuranceCard> getAll() {
        return insuranceCards;
    }

    @Override
    public InsuranceCard getOne(Number id) {
        for (InsuranceCard insuranceCard : insuranceCards) {
            if (insuranceCard.getId().equals(id))
                return insuranceCard;
        }
        return null;
    }

    @Override
    public void save() {
        try {
            FileWriter myWriter = new FileWriter("db/card.json");
            String data = "";
            for (InsuranceCard insuranceCard : insuranceCards) {
                data = data.concat(insuranceCard.toJson());
            }
            myWriter.write(data);
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
