package org.example;
/*
city – the property is located in this city (textual)
price – the price of one square meter of the property (real)
sqm – the value of the property area in square meters (integer)
numberOfRooms – the number of rooms in the property (real)
genre – Type Genre
Genre – static enumerated type with the following options: FAMILYHOUSE, CONDOMINIUM,
FARM (enum)
 */
public class RealEstate implements PropertyInterface{
    String city;
    double price;
    int sqm;
    double numberOfRooms;
    Genre genre;

    public RealEstate() {
    }
    public RealEstate(String city, double price, int sqm, double numberOfRooms, Genre genre) {
        this.city = city;
        this.price = price;
        this.sqm = sqm;
        this.numberOfRooms = numberOfRooms;
        this.genre = genre;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getSqm() {
        return sqm;
    }
    public void setSqm(int sqm) {
        this.sqm = sqm;
    }
    public double getNumberOfRooms() {
        return numberOfRooms;
    }
    public void setNumberOfRooms(double numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public void makeDiscount(int percent) {
        double p = price;
        double discount = p*(percent/100.0);
        price -= discount;
    }

    @Override
    public int getTotalPrice() {
        double basePrice = price * sqm;
        if (city.equalsIgnoreCase("Budapest")) {
            basePrice *= 1.3;
        } else if (city.equalsIgnoreCase("Debrecen")) {
            basePrice *= 1.2;
        } else if (city.equalsIgnoreCase("Nyíregyháza")) {
            basePrice *= 1.15;
        }
        return (int) Math.round(basePrice);
    }

    @Override
    public double averageSqmPerRoom() {
        if (numberOfRooms == 0) return 0;
        return (double) sqm / numberOfRooms;
    }

    @Override
    public String toString() {
        return "RealEstate { " +
                "city='" + city + '\'' +
                ", price=" + price +
                ", sqm=" + sqm +
                ", numberOfRooms=" + numberOfRooms +
                ", genre=" + genre +
                ", totalPrice=" + getTotalPrice() +
                ", sqm/room=" + String.format("%.2f", averageSqmPerRoom()) +
                " }";
    }
}
