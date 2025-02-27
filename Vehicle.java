public class Vehicle {
 private String brand;
 private String model;
 private int yearOfEstablishment;
 private double rentalRate;
 private String vehicle_type;
 
 public Vehicle( String vehicle_type,String brand, String model, int yearOfEstablishment, double rentalRate){
  this.brand=brand;
  this.model = model;
  this.yearOfEstablishment = yearOfEstablishment;
  this.rentalRate = rentalRate;
  this.vehicle_type=vehicle_type;
 }

 public void displayInfo() {
  System.out.println("Vehicle Type: " +vehicle_type);
  System.out.println("Brand: " + brand);
  System.out.println("Model " + model);
  System.out.println("Year: " + yearOfEstablishment);
  System.out.println("Rental Rate: " + rentalRate);
 }
 
 public double getRentalRate() {
  return rentalRate;
 }
 
 public String getMake() {
  return brand;
 }
 
 public String getModel() {
  return model;
 }
  public String getType() {
  return vehicle_type;
 }
 public void updateRentalCost(Double rentalRate){
    this.rentalRate=rentalRate;
 }
}