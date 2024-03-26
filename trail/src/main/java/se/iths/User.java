package se.iths;
 
public class User {
   
    private double length;
    private double weight;
 
    public User(double length, double weight) {
        this.length = length;
        this.weight = weight;
    }
 
    public double getLength() {
        return length;
    }
 
    public double getWeight() {
        return weight;
    }
 
    public double calculateBMI() {
        if (length <= 0 || weight <= 0) {
            return Double.NaN;  // Returnera NaN för ogiltiga värden
        }
 
        double heightInMeters = length / 100.0;
        return weight / Math.pow(heightInMeters, 2);
    }
   
}