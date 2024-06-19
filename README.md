 # Hotel Management System
## Project Overview
The Hotel Management System is designed to streamline operations for both hotel administrators and guests. It offers functionalities for customers such as searching for available rooms, making reservations, customer check-ins and check-outs, and invoice generation. Administrators can manage hotel employees and staff, room availability, and housekeeping schedules. The system includes role-based access control, providing different functionalities based on user roles (admin, customer).
## Features
### Customer Management
- Register: Allows new users to create an account.
- Login: Enables existing users to log in.
- Profile Management: Users can view, update, and change their profile information and passwords.
### Employee Management
- Admin Functionality: Admin users can manage hotel employees and staff.
### Search Functionality
- Reservations: Search for reservations by customer name or ID and date.
- Customer Info: Search for customer information.
- Available Rooms: Search for available rooms and view details such as price, facilities, capacity, size, and features.
### Reservation Management
- Book Room: Customers can book rooms.
- Modify Reservation: Modify existing reservations.
- Cancel Reservation: Request cancellation of reservations (requires admin approval).
### Room Management
- Room Types: Manage different types of rooms.
- Availability: Manage room availability.
- Status: Update room status.
### Check-In/Check-Out
- Check-In: Manage customer arrival processes.
- Check-Out: Manage customer departure processes.
### Housekeeping Management
- Scheduling: Schedule housekeeping tasks.
- Tracking: Track housekeeping tasks and employees.
### Billing
- Invoice Generation: Generate and manage invoices for customer reservations.

### Role-Based Access Control
- Different functionalities are available based on user roles (admin, customer).

## Resources

### User
- Description: Represents the user in the hotel
- Attributes: ID, first name, last name, phone number, email, password, role (e.g. ROLE_USER, ROLE_ADMIN) 
- Relationships:
  - if it is a customer, then the customer is related to a specific reservation.

### Employee
- Description: Represents the employees in the hotel
- Attributes: ID, first name, last name, phone number, email, address, salary, hire date, department (e.g. FRONT_DESK, HOUSEKEEPING, MAINTENANCE, KITCHEN, SECURITY, HR, MARKETING, IT, FINANCE)
- Relationships:
  - Employees in the housekeeping department are assigned to a specific task.


### Room
- Description: Represents the rooms in the hotel
- Attributes: ID, Room number, Floor number, view (e.g. Pool, garden, sea), status (e.g. Available, reserved, checked_Out, checked_In), cleanliness_status (e.g. Dirty, under maintenance, cleaned)
- Relationships:
  - Customer books a room.


### Room type 
- Description: Represents the room types in the hotel
- Attributes: ID, price, type name, room size, number of adults, number of children
- Relationships:
  - Each room is related to room type. 

### Features
- Description: Represents the room features in the hotel
- Attributes: ID, feature, and description.
- Relationships:
  - Assign features to specific room types.
    
###  Room Type Feature 
- Description: Each room type has specific features.
- Attributes: ID, room type ID, feature ID
- Relationships:
  - Each room type has specific features.

### Bed type
- Description: Represents the beds in the hotel
- Attributes: ID, bed type.
- Relationships:
  - Bed type is assigned for a Room Type.
 
###  Room Type Bed 
- Description: Each room type has a specific type of bed.
- Attributes: ID, room type ID, bed ID, number of beds.
- Relationships:
  - Each room type has a list of bed types.

###  Housekeeping task
- Description: This is for managing housekeeping tasks in the hotel, employees are assigned to specific rooms.
- Attributes: ID, description, scheduled date, room ID, employee id, status (e.g. pending, in progress, completed). 
- Relationships:
  - Each task is assigned to a specific employee for a specific room on a specific date.



###  Add on feature
- Description: This is for additional features that the user adds in making reservations such as breakfast.
- Attributes: ID, name, price.
- Relationships: Each additional feature is assigned for a reservation.

    
### Reservation
- Description: This is for reservation.
- Attributes: ID, List of rooms reserved, user id, number of adults, number of children, check-in date, check-out date, payment method (e.g. pay now, pay later), invoice. 
- Relationships:
  - Each booking has one or more rooms reserved.
  - Each task is assigned to a specific employee for a specific room in a specific date.


### Reserve Room
- Description: This is for reserving a room for a specific booking.
- Attributes: ID, booking id, room id.
- Relationships:
  - Each room is reserved for one reservation.

### Reserve add-on
- Description: This is for adding additional features in the reservation.
- Attributes: ID, booking id, addition feature id.
- Relationships:
  - Addition features are added for specific reservations, then the invoice will be recalculated based on the additions.

### Invoice
- Description: This is for calculating the invoice for a specific reservation.
- Attributes: ID, booking id, total price.
- Relationships:
  - Each invoice is related to a specific reservation.
 
## ER diagram
![image](https://github.com/elianaellati/Hotel_Management_System/assets/132192886/a3abdb8a-5702-4924-ac25-81aa854383f5)


## How to Build, Package, and Run the Application
- By Using the Following command building the project and its JAR files will be completed :
- ./mvnw.cmd package -DskipTests
##  After the build process completes, you will find the JAR file in the target directory:
- target/Hotel_Management_System-0.0.1-SNAPSHOT.jar
-After That we Create a Dockerfile in the root directory of our project.
## Build the Docker Image:
- By Using the following command the Docker image will be built:
- docker build -t halamo1/hotel_management_system:latest .
## Pushing the Docker Image to DockerHub Using the following command :
-  docker push hala1mo/hotel_management_system:latest
## Running the Application with Docker Compose 
- Pull the Docker image from DockerHub using the following command docker pull halamo1/hotel_management_system:latest
- Create a docker-compose.yml file
- Navigate to the directory containing the docker-compose.yml file.
- Run Docker Compose using docker-compose up command.

## DockerHub Repository
You can find the Docker image for this project on DockerHub hub
## Lessons Learned
- Understanding of Spring Boot and RESTful API development.
- Hands-on experience with Docker and Docker Compose for containerization.
- Knowledge of JWT for securing APIs.
- Implementing role-based access control.
- Experience with API versioning and documentation using OAS 3.1.0.

## Contributors
- Student 1: Hala Abdel Halim.
- Student 2: Eliana Ellati.

## OpenApISpecification Link:
- http://localhost:8080/swagger-ui/index.html#/

