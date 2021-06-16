package com.example.foodfight;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProfileHandler {
    static Gson gson = new Gson();
    //static Pro profile;

    public static Profile getProfile() {
        String data = "";
        try {
            File myObj = new File("profile.json");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return gson.fromJson(data, Profile.class);
    }

    public static void setProfile(Profile profile) {

        String save = gson.toJson(profile, Profile.class);
        //The next line calculates the bmi
        profile.bmi = profile.weight / profile.height * profile.height * profile.height;
        try {
            FileWriter myWriter = new FileWriter("profile.json");
            myWriter.write(save);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

