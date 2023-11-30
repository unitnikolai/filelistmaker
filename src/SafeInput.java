//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Scanner;

public class SafeInput {
    public SafeInput() {
    }

    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        boolean done = false;
        String response = "";

        do {
            System.out.print(prompt + " : ");
            response = pipe.nextLine();
            if (response.length() > 0) {
                done = true;
            } else {
                System.out.println("\nEnter at least one character\n");
            }
        } while(!done);

        return response;
    }

    public static int getInt(Scanner pipe, String prompt) {
        boolean done = false;
        String response = "";
        int value = 0;
        String trash = "";

        do {
            System.out.print(prompt + " : ");
            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("\nEnter at least one character\n");
            }
        } while(!done);

        return value;
    }

    public static int getRangeInt(Scanner pipe, String prompt, int low, int high) {
        boolean done = false;
        String response = "";
        int value = 0;
        String trash = "";

        do {
            System.out.print(prompt);
            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                pipe.nextLine();
                if (value >= low && value <= high) {
                    done = true;
                } else {
                    System.out.println("Invalid input try again");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("\nEnter at least one character\n");
            }
        } while(!done);

        return value;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        boolean done = false;
        String response = "";
        double value = 0.0;
        String trash = "";

        do {
            System.out.print(prompt + " : ");
            if (pipe.hasNextDouble()) {
                value = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("\nEnter at least one character\n");
            }
        } while(!done);

        return value;
    }

    public static double getRangeDouble(Scanner pipe, String prompt, double low, double high) {
        boolean done = false;
        String response = "";
        double value = 0.0;
        String trash = "";

        do {
            System.out.print(prompt + "With in range [" + low + "-" + high + "]: ");
            if (pipe.hasNextDouble()) {
                value = pipe.nextDouble();
                pipe.nextLine();
                if (value >= 1.0 && value <= 10.0) {
                    done = true;
                } else {
                    System.out.println("Invalid input try again");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("\nEnter at least one character\n");
            }
        } while(!done);

        return value;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean done = false;
        boolean retVal = false;
        String response = "";

        do {
            System.out.print("" + prompt);
            response = pipe.nextLine();
            if (response.toUpperCase().matches("[YN]")) {
                done = true;
                if (response.equalsIgnoreCase("Y")) {
                    retVal = true;
                } else {
                    retVal = false;
                }
            } else {
                System.out.println("");
            }
        } while(!done);

        return retVal;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        boolean done = false;
        String response = "";

        do {
            System.out.print(prompt + " " + regEx + ": ");
            response = pipe.nextLine();
            if (response.matches(regEx)) {
                done = true;
            } else {
                System.out.println("\nEnter string that matchesn" + regEx);
            }
        } while(!done);

        return response;
    }
}
