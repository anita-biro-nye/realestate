package org.example;

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class RealEstateAgent {

    private static final Logger logger = LoggerConfig.getLogger();

    private static TreeSet<RealEstate> properties =
            new TreeSet<>(Comparator.comparingInt(RealEstate::getTotalPrice));

    public static void loadFromFile(String filename) {
        logger.info("Entering loadFromFile() with filename: " + filename);

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("#");
                String className = parts[0];
                String city = parts[1];
                double price = Double.parseDouble(parts[2]);
                int sqm = Integer.parseInt(parts[3]);
                double numberOfRooms = Double.parseDouble(parts[4]);
                Genre genre = Genre.valueOf(parts[5]);

                if (className.equalsIgnoreCase("PANEL")) {
                    int floor = Integer.parseInt(parts[6]);
                    boolean isInsulated = parts[7].equalsIgnoreCase("yes");
                    properties.add(new Panel(city, price, sqm, numberOfRooms, genre, floor, isInsulated));
                } else {
                    properties.add(new RealEstate(city, price, sqm, numberOfRooms, genre));
                }
            }

            logger.info("Successfully loaded properties from file: " + filename);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + filename, e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error while loading from file", e);
        }
    }

    public static void printAndSaveResults(String outputFilename) {
        logger.info("Entering printAndSaveResults() with filename: " + outputFilename);

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilename))) {
            double totalSqmPrice = 0;
            int totalProperties = 0;
            int totalPriceAll = 0;
            RealEstate cheapest = null;
            RealEstate mostExpensiveBudapest = null;

            for (RealEstate re : properties) {
                totalSqmPrice += re.getPrice();
                totalPriceAll += re.getTotalPrice();
                totalProperties++;

                if (cheapest == null || re.getTotalPrice() < cheapest.getTotalPrice()) {
                    cheapest = re;
                }

                if ("Budapest".equalsIgnoreCase(re.getCity())) {
                    if (mostExpensiveBudapest == null || re.getTotalPrice() > mostExpensiveBudapest.getTotalPrice()) {
                        mostExpensiveBudapest = re;
                    }
                }
            }

            double averageSqmPrice = totalSqmPrice / totalProperties;
            double averageTotalPrice = (double) totalPriceAll / totalProperties;

            writer.println("Average square meter price of real estate: " + averageSqmPrice);
            writer.println("Price of the cheapest property: " + cheapest.getTotalPrice());
            writer.println("Average square meter value per room of the most expensive apartment in Budapest: " +
                    (mostExpensiveBudapest != null ? mostExpensiveBudapest.averageSqmPerRoom() : "N/A"));
            writer.println("Total price of all properties: " + totalPriceAll);
            writer.println("\nCondominium properties under average total price:");

            for (RealEstate re : properties) {
                if (re.getGenre() == Genre.CONDOMINIUM && re.getTotalPrice() <= averageTotalPrice) {
                    writer.println(re);
                }
            }

            writer.flush();
            System.out.println("Results written to " + outputFilename);
            logger.info("Successfully wrote results to file: " + outputFilename);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing output file: " + outputFilename, e);
        }
    }

    public static TreeSet<RealEstate> getProperties() {
        logger.info("Accessing property collection");
        return properties;
    }
}
