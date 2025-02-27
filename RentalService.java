import java.util.ArrayList;
import java.util.List;

public class RentalService {
 
 private List<Vehicle> availableVehicles;
 private List<Vehicle> rentedVehicles;
 
 public RentalService() {
  availableVehicles = new ArrayList<>();
  rentedVehicles = new ArrayList<>();
 }
 
 //to add vehicles to rental system
 public void addVehicle(Vehicle vehicle) {
  availableVehicles.add(vehicle);
 }
 
 public List<Vehicle> getAvailableVehicles(){
  return availableVehicles;
 }
 
 public List<Vehicle> getRentedVehicles(){
  return rentedVehicles;
 }
 
 //to rent vehicle
 public void rentVehicle(Vehicle vehicle) {

    try {
        if(!availableVehicles.contains(vehicle)){
            throw new Exception("Vehicle is not available");        
        }
        availableVehicles.remove(vehicle);
        rentedVehicles.add(vehicle);    
        
    } catch (Exception e) {

        System.out.println("Error renting vehicle:"+e.getMessage());
    }
  
 }
 
 public Vehicle findVehicle(RentalService rentalsystem,String variable,String brandName,String ModelName){
   List<Vehicle> vehicleList; 
   Vehicle selectedVehicle=null;
    if(variable.equalsIgnoreCase("rented")){
        vehicleList=rentalsystem.getRentedVehicles();
    }
    else{
        vehicleList=rentalsystem.getAvailableVehicles();
    }
    for(Vehicle vehicle:vehicleList){
        if(vehicle.getMake().equalsIgnoreCase(brandName) && vehicle.getModel().equalsIgnoreCase(ModelName)){
            selectedVehicle=vehicle;
        }
    }
    return selectedVehicle;
 }
 
 //to return rented vehicle
 public void returnVehicle(Vehicle vehicle) {
    try {
        if(!rentedVehicles.contains(vehicle)){
            throw new Exception("Invalid return. Vehicle not rented.");
        }
        rentedVehicles.remove(vehicle);
        availableVehicles.add(vehicle);
    } catch (Exception e) {
        System.out.println("Error returning vehicle:"+e.getMessage());
    }
 }
 
//to remove vehicle
public void removeVehicle(Vehicle vehicle) {

  try {
    if(!availableVehicles.contains(vehicle)) {
        throw new Exception("There is no vehicle available");
  }
     availableVehicles.remove(vehicle);
  } catch (Exception e) {
    System.out.println("Error removing vehicle:"+e.getMessage());
  }
  
 }

 //To display available and rented vehicles
 public void displayRentalInfo() {
  System.out.println("Available vehicles: ");
  for(Vehicle availaVehicle : availableVehicles) {
   availaVehicle.displayInfo();
   System.out.println();
  }
  
  System.out.println("Rented vehicles: ");
  for(Vehicle rentedVehicle : rentedVehicles) {
   rentedVehicle.displayInfo();
   System.out.println();
  }
 }
 


 
 //To calculate total rental cost
 public double calculateRentalCost(Vehicle vehicle, int rentalDuration) {
try {
    double rentalRate = vehicle.getRentalRate();
     return rentalRate * rentalDuration;
   
} catch (Exception e) {
    System.out.println("Error calculating rental cost: " + e.getMessage());
            return 0;
}
 }

}