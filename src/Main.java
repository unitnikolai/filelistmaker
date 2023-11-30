import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
    static ArrayList<String> List = new ArrayList<>();


    public static void main(String[] args) {

        String cmd = "";
        Boolean done = false;
        boolean run = true;
        Scanner in = new Scanner(System.in);
        boolean needsToBeSaved = false;
        String fileName = "";
        do {
            final String menu = "A - add E - append D – Delete V – View Q – Quit  O – Open  S – Save   C – Clear";
            cmd = SafeInput.getRegExString(in, menu, "[AaEeDdPpQqOoSsCcVv]");
            cmd = cmd.toUpperCase();
            switch (cmd) {
                case "A":
                    adding(List);
                    needsToBeSaved = true;
                    break;
                case "E":
                    appending(List);
                    needsToBeSaved = true;
                    break;
                case "D":
                    deleting(List);
                    needsToBeSaved = true;
                    break;
                case "V":
                    displayList(List);
                    needsToBeSaved = true;
                    break;
                case "Q":
                    if (SafeInput.getYNConfirm(in, "Are you sure")) {
                        if (needsToBeSaved) {
                            save(fileName, List);
                        }
                        run = false;
                    }
                case "O":
                    fileName = openListFile(in, List, needsToBeSaved);
                    break;
                case "S":
                    save(fileName, List);
                    needsToBeSaved = false;
                    break;
                case "C":
                    clearList(List);
            }
            Scanner input = new Scanner(System.in);
            done = SafeInput.getYNConfirm(input, "are you done? [Y or N]");
        }
        while (!done);
    }

    private static void adding(ArrayList List) {
        System.out.println("Enter your item");
        Scanner in = new Scanner(System.in);
        String item = in.nextLine();
        List.add(item);
    }

    private static void appending(ArrayList List) {
        int entry = 0;
        Scanner in = new Scanner(System.in);
        entry = SafeInput.getRangeInt(in, "Which number would you like to append into?", 1, List.size());
        entry = entry - 1;
        System.out.println("Enter your item");
        String item = in.nextLine();
        List.add(entry, item);

    }

    private static void deleting(ArrayList List) {
        int entry = 0;
        Scanner in = new Scanner(System.in);
        entry = SafeInput.getRangeInt(in, "Which number would you like to delete?", 1, List.size());
        entry = entry - 1;
        List.remove(entry);
    }

    public static void displayList(ArrayList List) {
        for (int i = 0; i < List.size(); i++) {
            System.out.println(List.get(i));
        }
    }

    public static String openListFile(Scanner in, ArrayList List, boolean needsToBeSaved) {
        if (needsToBeSaved) {
            String prompt = "Opening a new list will result in losing your current one. Are you sure?";
            boolean burnListYN = SafeInput.getYNConfirm(in, prompt);
            if (!burnListYN) {
                return "";
            }
        }
        clearList(List);
        Scanner inFile;
        JFileChooser chooser = new JFileChooser();

        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());
        System.out.println("Select a file");
        try {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();
                inFile = new Scanner(target);
                System.out.println("Opening File: " + target.getFileName());
                while (inFile.hasNextLine()) {
                    String line = inFile.nextLine();
                    List.add(line);
                }
            } else {
                System.out.println("You must select a file! Returning to menu...");
            }
        } catch (IOException e) {
            System.out.println("IOException Error");
        }
        return target.toFile().toString();
    }


    public static void save(String fileName, ArrayList List) {
        PrintWriter outFile;
        Path target = new File(System.getProperty("user.dir")).toPath();
        if (fileName.equals("")) {
            target = target.resolve("src\\list.txt");
        } else {
            target = target.resolve(fileName);
        }

        try {
            outFile = new PrintWriter(target.toString());
            for (int i = 0; i < List.size(); i++) {
                outFile.println(List.get(i));
            }
            outFile.close();
            System.out.printf("File \"%s\" saved!\n", target.getFileName());
        } catch (IOException e) {
            System.out.println("IOException Error");
        }
    }

    public static void clearList(ArrayList List){
        List.clear();
    }


}