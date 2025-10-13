package org.example;

import java.io.*;
import java.util.*;

public class RealEstateAgent {
    private static TreeSet<RealEstate> properties = new TreeSet<>(Comparator.comparingInt(RealEstate::getTotalPrice));

    public static void loadFromFile(String filename) {
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
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void printAndSaveResults(String outputFilename) {
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

            writer.println("Condominium properties under average total price:");
            for (RealEstate re : properties) {
                if (re.getGenre() == Genre.CONDOMINIUM && re.getTotalPrice() <= averageTotalPrice) {
                    writer.println(re);
                }
            }

            writer.flush();
            System.out.println("Results written to " + outputFilename);
        } catch (IOException e) {
            System.out.println("Error writing output: " + e.getMessage());
        }
    }

    public static TreeSet<RealEstate> getProperties() {
        return properties;
    }
}
