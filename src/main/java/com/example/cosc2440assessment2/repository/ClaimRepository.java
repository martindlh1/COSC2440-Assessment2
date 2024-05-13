/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.repository;

import com.example.cosc2440assessment2.model.Claim;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClaimRepository implements ProcessManager<Claim> {

    private final List<Claim> claims;
    private static final ClaimRepository instance = new ClaimRepository();

    public ClaimRepository() {
        try {
            Gson gson = new Gson();
            File file = new File("db/claim.json");
            Scanner myReader = new Scanner(file);
            claims = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                claims.add(gson.fromJson(data, Claim.class));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ClaimRepository getInstance() {
        return instance;
    }

    @Override
    public void add(Claim claim) {
        claims.add(claim);
    }

    @Override
    public void update(Claim claim) {
        claims.removeIf(c -> c.getId().equals(claim.getId()));
        claims.add(claim);
    }

    @Override
    public boolean delete(Claim claim) {
        return claims.remove(claim);
    }

    @Override
    public List<Claim> getAll() {
        return claims;
    }

    @Override
    public Claim getOne(Number id) {
        for (Claim claim : claims) {
            if (claim.getId().toString().equals(id.toString()))
                return claim;
        }
        return null;
    }

    @Override
    public void save() {
        try {
            FileWriter myWriter = new FileWriter("db/claim.json");
            String data = "";
            for (Claim claim : claims) {
                data = data.concat(claim.toJson());
            }
            myWriter.write(data);
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
