/*
 * This work is licensed under the Creative Commons Attribution-NonCommercial 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/4.0/.
 */

// @author Umesha Madushan

package com.umeshag.retailstorerm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BillNumberGenerator {
    //method for concatenate bill number ---------------------------------------
    public static String generateBillNumber() {
        // Define the prefix
        String prefix = "B";

        // Define the length of the random alphanumeric sequence
        int sequenceLength = 4;

        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");

        String date = dateFormat.format(new Date());
        String time = timeFormat.format(new Date());

        // Generate a random alphanumeric sequence
        String alphanumericSequence = generateRandomAlphanumeric(sequenceLength);

        // Concatenate the prefix and the random sequence
        String billNumber = prefix+"-"+date+"-"+time+"-"+alphanumericSequence;

        return billNumber;
    }

    // method for generate random alpha numeric sequance -----------------------
    public static String generateRandomAlphanumeric(int length) {
        // characters for the alphanumeric sequence
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        //random object
        Random random = new Random();

        StringBuilder sb = new StringBuilder(length);

        // Generate the alphanumeric sequence
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}