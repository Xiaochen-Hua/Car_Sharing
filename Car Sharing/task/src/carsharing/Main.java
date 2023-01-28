package carsharing;
import carsharing.Database;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        String name = "carsharing.mv.db";
        if(args.length > 1 && args[0].equals("-databaseFileName")) {
            name = args[1];
        }
        Database.init(name);
        Menu menu = new Menu();
        menu.startMainMenu();
    }
}