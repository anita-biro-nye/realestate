package org.example;

import java.util.logging.*;

public class Main {

    private static final Logger logger = LoggerConfig.getLogger();

    public static void main(String[] args) {
        logger.info("Starting RealEstate application");

        try {
            RealEstate re = new RealEstate("Budapest", 4000, 50, 3, Genre.CONDOMINIUM);
            System.out.println(re);
            re.makeDiscount(10);
            System.out.println(re);

            //Panel 1:
            Panel panel1 = new Panel("Budapest", 500000, 60, 3, Genre.CONDOMINIUM, 1, true);
            //Panel 2:
            Panel panel2 = new Panel("Debrecen", 480000, 60, 3, Genre.CONDOMINIUM, 10, false);

            System.out.println("\n=== Panel 1 ===");
            System.out.println(panel1);

            System.out.println("\n=== Panel 2 ===");
            System.out.println(panel2);

            boolean samePrice = panel1.hasSameAmount(panel2);
            System.out.println("\nDo panel1 and panel2 have the same total price?: " + samePrice);

            System.out.println("\nAverage room price in Panel 1: " + panel1.roomprice() + " Ft");
            System.out.println("Average room price in Panel 2: " + panel2.roomprice() + " Ft");

            RealEstateAgent.loadFromFile("realestates.txt");
            RealEstateAgent.printAndSaveResults("outputRealEstate.txt");

            logger.info("Application finished successfully.");
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error in main", e);
        }
    }
}
