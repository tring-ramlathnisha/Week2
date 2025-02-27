import java.util.Scanner;

public class VehicleRentalSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalService rentalSystem = new RentalService();

        // Adding vehicles
        rentalSystem.addVehicle(new Car("Car", "Toyota", "Supra", 2020, 100, 4, "Petrol"));
        rentalSystem.addVehicle(new Bike("Bike", "Honda", "CXR", 2000, 50, "Fuel-based"));
        rentalSystem.addVehicle(new Bike("Bike", "TVS", "Jupiter 125", 2024, 70, "Electric"));
        rentalSystem.addVehicle(new Car("Car", "Ford", "Figo", 2023, 200, 4, "Diesel"));

        while (true) {
            System.out.println("\n===== Vehicle Rental System =====");
            System.out.println("1. Rent a Vehicle");
            System.out.println("2. Return a Vehicle");
            System.out.println("3. Display Available and Rented Vehicles");
            System.out.println("4. Add vehicles");
            System.out.println("5. Remove vehicles");
            System.out.println("6. Update rental cost");
            System.out.println("7. Exit");
            System.out.print("\nEnter your choice: ");

            int choice = 0;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    rentVehicle(scanner, rentalSystem);
                    break;
                case 2:
                    returnVehicle(scanner, rentalSystem);
                    break;
                case 3:
                    rentalSystem.displayRentalInfo();
                    break;
                case 4:
                    addVehicle(scanner, rentalSystem);
                    break;
                case 5:
                    removeVehicle(scanner, rentalSystem);
                    break;
                case 6:
                    updateRentalCost(scanner, rentalSystem);
                    break;
                case 7:
                    System.out.println("Thank you for using the Vehicle Rental System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    // ✅ Rent a Vehicle with Exception Handling
    private static void rentVehicle(Scanner scanner, RentalService rentalSystem) {
        System.out.print("Enter the vehicle brand: ");
        String vehicleBrand = scanner.nextLine().trim();
        System.out.print("Enter the vehicle model: ");
        String vehicleModel = scanner.nextLine().trim();

        if (!vehicleBrand.matches("[A-Za-z\\s]+") || !vehicleModel.matches("[A-Za-z0-9\\s]+")) {
            System.out.println("Invalid input! Brand and model should contain only letters.");
            return;
        }

        Vehicle selectedVehicle = rentalSystem.findVehicle(rentalSystem,"available",vehicleBrand, vehicleModel);
        if (selectedVehicle == null) {
            System.out.println("Matching vehicle is not available for rent.");
            return;
        }

        rentalSystem.rentVehicle(selectedVehicle);

        int rentalDuration = getValidInt(scanner, "Enter the rental duration in days: ", 1, 365);
        double totalRentalCost = rentalSystem.calculateRentalCost(selectedVehicle, rentalDuration);
        System.out.println("Successfully rented.");
        System.out.println("Total Rental Cost: $" + totalRentalCost);
    }

    // ✅ Return a Vehicle with Exception Handling
    private static void returnVehicle(Scanner scanner, RentalService rentalSystem) {
        System.out.print("Enter the vehicle brand: ");
        String vehicleBrand = scanner.nextLine().trim();
        System.out.print("Enter the vehicle model: ");
        String vehicleModel = scanner.nextLine().trim();

        if (!vehicleBrand.matches("[A-Za-z\\s]+") || !vehicleModel.matches("[A-Za-z0-9\\s]+")) {
            System.out.println("Invalid input! Brand and model should contain only letters.");
            return;
        }

        Vehicle selectedVehicle = rentalSystem.findVehicle(rentalSystem,"rented",vehicleBrand, vehicleModel);
        if (selectedVehicle == null) {
            System.out.println("Invalid return. Vehicle not rented.");
            return;
        }

        rentalSystem.returnVehicle(selectedVehicle);
        System.out.println("Vehicle returned successfully.");
    }

    // ✅ Add a Vehicle with Exception Handling
    private static void addVehicle(Scanner scanner, RentalService rentalSystem) {
        System.out.print("Enter the vehicle type (Car/Bike): ");
        String vehicleType = scanner.nextLine().trim();
        System.out.print("Enter the vehicle brand: ");
        String vehicleBrand = scanner.nextLine().trim();
        System.out.print("Enter the vehicle model: ");
        String vehicleModel = scanner.nextLine().trim();

        if (!vehicleType.matches("[A-Za-z\\s]+") || !vehicleBrand.matches("[A-Za-z\\s]+") || !vehicleModel.matches("[A-Za-z0-9\\s]+")) {
            System.out.println("Invalid input! Only letters are allowed.");
            return;
        }

        int yearOfEstablishment = getValidInt(scanner, "Enter the year of establishment: ", 1900, 2025);
        double rentalCost = getValidDouble(scanner, "Enter the rental rate: ", 1, 10000);

        Vehicle vehicle;
        if (vehicleType.equalsIgnoreCase("car")) {
            int accommodatePersons = getValidInt(scanner, "No. of accommodated persons: ", 1, 10);
            System.out.print("Fuel Type: ");
            String fuelType = scanner.nextLine();
            vehicle = new Car(vehicleType, vehicleBrand, vehicleModel, yearOfEstablishment, rentalCost, accommodatePersons, fuelType);
        } else {
            System.out.print("Enter bike type (Fuel-based/Electric): ");
            String bikeType = scanner.nextLine();
            vehicle = new Bike(vehicleType, vehicleBrand, vehicleModel, yearOfEstablishment, rentalCost, bikeType);
        }

        rentalSystem.addVehicle(vehicle);
        System.out.println("Vehicle added successfully.");
    }

    // ✅ Remove a Vehicle with Exception Handling
    private static void removeVehicle(Scanner scanner, RentalService rentalSystem) {
        System.out.print("Enter the vehicle brand: ");
        String vehicleBrand = scanner.nextLine().trim();
        System.out.print("Enter the vehicle model: ");
        String vehicleModel = scanner.nextLine().trim();

        if (!vehicleBrand.matches("[A-Za-z\\s]+") || !vehicleModel.matches("[A-Za-z0-9\\s]+")) {
            System.out.println("Invalid input! Brand and model should contain only letters.");
            return;
        }

        Vehicle selectedVehicle = rentalSystem.findVehicle(rentalSystem,"available",vehicleBrand, vehicleModel);
        if (selectedVehicle == null) {
            System.out.println("No such vehicle found.");
            return;
        }

        rentalSystem.removeVehicle(selectedVehicle);
        System.out.println("Vehicle removed successfully.");
    }

    // ✅ Update Rental Cost with Exception Handling
    private static void updateRentalCost(Scanner scanner, RentalService rentalSystem) {
        System.out.print("Enter brand: ");
        String brandName = scanner.nextLine().trim();
        System.out.print("Enter model: ");
        String modelName = scanner.nextLine().trim();

        if (!brandName.matches("[A-Za-z\\s]+") || !modelName.matches("[A-Za-z0-9\\s]+")) {
            System.out.println("Invalid input! Only letters are allowed.");
            return;
        }

        double rentalCost = getValidDouble(scanner, "Enter the rental rate: ", 1, 10000);
        Vehicle selectedVehicle = rentalSystem.findVehicle(rentalSystem,"available",brandName, modelName);
        if (selectedVehicle == null) {
            System.out.println("No such vehicle found.");
            return;
        }

        selectedVehicle.updateRentalCost(rentalCost);
        System.out.println("Updated successfully.");
    }

    // ✅ Helper Methods for Valid Inputs
    private static int getValidInt(Scanner scanner, String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) return value;
                System.out.println("Invalid input. Enter a number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }
    }

    private static double getValidDouble(Scanner scanner, String message, double min, double max) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(scanner.nextLine());
                if (value >= min && value <= max) return value;
                System.out.println("Invalid input. Enter a value between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }
    }
}
