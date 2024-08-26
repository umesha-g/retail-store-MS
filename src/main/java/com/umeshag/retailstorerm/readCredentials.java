package com.umeshag.retailstorerm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readCredentials {
    public static void main(String[] args) {
        String filePath = "credentials.txt";
        String username = null;
        String password = null;

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("username:")) {
                    username = line.substring("username:".length()).trim();
                } else if (line.startsWith("password:")) {
                    password = line.substring("password:".length()).trim();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }
}
