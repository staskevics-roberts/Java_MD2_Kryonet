/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kryonet;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Roberts Staskevics
 */
public class ClientApp {

    private static Variation variation;
    private static int userInputOptions;
    private static int userInputOptionsVariants;

    /**
     *
     */
    public ClientApp() {
        variation = new Variation();
        //List<Integer> selectedNumbers = variation.getList();
        userInputOptions = 0;
        userInputOptionsVariants = 0;
    }

    /**
     *
     */
    public void askHowManyOptions() {
        String errorMessage = "";
        do {
            // Show input dialog with current error message, if any
            String stringInput = JOptionPane.showInputDialog(null, errorMessage + "Enter number (1-5):", "Input number...");
            try {
                userInputOptions = Integer.parseInt(stringInput);
                variation.setVariationNr(userInputOptions);
                //System.out.println("userInputOptions: " + userInputOptions);
                if (userInputOptions <= 0 || userInputOptions >= 6) {
                    errorMessage = "That number is not within the \n" + "allowed range!\n";
                } else {
                    JOptionPane.showMessageDialog(null, "Thank you! You inserted "
                            + userInputOptions + " options!",
                            "User Input", JOptionPane.INFORMATION_MESSAGE);
                    errorMessage = ""; // no more error
                }
            } catch (NumberFormatException ex) {
                // The typed text was not an integer
                errorMessage = "The text you typed is not a number.\n";
            }
        } while (!errorMessage.isEmpty());
    }

    /**
     *
     */
    public void askWhichVariantsToFill() {
        String errorMessage = "";
        do {
            // Show input dialog with current error message, if any
            String stringInput = JOptionPane.showInputDialog(null, errorMessage
                    + "How many variants you want to fill by hand? \n"
                    + "You can choose: " + userInputOptions + " or less.",
                    "Input number of variants...");
            try {
                userInputOptionsVariants = Integer.parseInt(stringInput);
                //System.out.println("userInputOptionsVariants: " + userInputOptionsVariants);
                if (userInputOptionsVariants < 0 || userInputOptionsVariants > userInputOptions) {
                    errorMessage = "That number is not within the \n" + "allowed range!\n";
                } else {
                    JOptionPane.showMessageDialog(null, "Thank you! You inserted "
                            + userInputOptionsVariants + " options! "
                            + "\nOther variants will fill in automaticaly!",
                            "User Input", JOptionPane.INFORMATION_MESSAGE);
                    errorMessage = ""; // no more error
                }
            } catch (NumberFormatException ex) {
                // The typed text was not an integer
                errorMessage = "The text you typed is not a number.\n";
            }
        } while (!errorMessage.isEmpty());
    }

    /**
     *
     */
    public void userFillVariant() {
        int userInputNumber;
        String errorMessage = "";
        //for (int j = 0; j < userInputOptionsVariants; j++) {
        //if (userInputOptionsVariants == 1) {
        int index = 1;
        variation.setVariationNr(index);
        for (int i = 0; i < 5; i++) {
            do {
                // Show input dialog with current error message, if any
                String stringInput = JOptionPane.showInputDialog(null, errorMessage
                        + "Enter number Nr. " + index + " number in range (1-35):",
                        "Enter number...");
                try {
                    userInputNumber = Integer.parseInt(stringInput);
                    //System.out.println("userInputOptions: " + userInputOptions);
                    if (userInputNumber <= 0 || userInputNumber > 35) {
                        errorMessage = "That number is not within the \n" + "allowed range!\n";
                    } else {
                        JOptionPane.showMessageDialog(null, "Thank you! You inserted number Nr. "
                                + index + " !",
                                "User Input", JOptionPane.INFORMATION_MESSAGE);
                        errorMessage = ""; // no more error
                        index++;
                        variation.insertIntoArrayList(userInputNumber);
                    }
                } catch (NumberFormatException ex) {
                    // The typed text was not an integer
                    errorMessage = "The text you typed is not a number.\n";
                }
            } while (!errorMessage.isEmpty());
            //}
        }
        //for (int j = 0; j < userInputOptionsVariants; j++) {
        // array output           
        System.out.println("User Variation Nr." + variation.getVariationNr() + " Numbers:");
        for (int i = 0; i < variation.getList().size(); i++) {
            if (i == 0) {
                System.out.print("[ " + variation.getList().get(i));
            } else if (i == variation.getList().size() - 1) {
                System.out.print(" , " + variation.getList().get(i) + " ]\n");
            } else {
                System.out.print(" , " + variation.getList().get(i));
            }
        }
        System.out.println("\n");
        //}
    }

    /**
     *
     */
    public void randomFillVariant() {
        int random;
        for (int j = 0; j < userInputOptions - userInputOptionsVariants; j++) {
            for (int i = 0; i < 5; i++) {
                random = randInt(1, 35);
                variation.insertIntoArrayList(random);
            }
        }
        System.out.println("User Variation Nr." + variation.getVariationNr() + " Numbers:");
        for (int i = 0; i < variation.getList().size(); i++) {
            if (i == 0) {
                System.out.print("[ " + variation.getList().get(i));
            } else if (i == variation.getList().size() - 1) {
                System.out.print(" , " + variation.getList().get(i) + " ]\n");
            } else {
                System.out.print(" , " + variation.getList().get(i));
            }
        }
        System.out.println("\n");
    }

    /**
     *
     * @param min
     * @param max
     * @return randomNum
     */
    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    /**
     * 
     * @return userInputOptionsVariants
     */
    public int getUserInputOptionsVariants() {
        return userInputOptionsVariants;
    }
}
