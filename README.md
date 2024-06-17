 # Hotel Management System
## Project Overview
The Hotel Management System is designed to streamline operations for both hotel adminstrators and guests. It offers functionalities for customers such as searching for available rooms, making reservations, customer check-ins and check-outs, and invoice generation. Adimnstrators can manage hotel employees and staff, room availability, and tracking housekeeping schedules. The system includes role-based access control, providing different functionalities based on user roles (admin, customer).
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
- Attributes: ID, first name, last name, phone number,email, password, role (e.g. ROLE_USER, ROLE_ADMIN) 
- Relationships:
  - Supplies Products

### Employee
- Description: Represents the employees in the hotel
- Attributes: ID, first name, last name, phone number,email, address, salary, hireDate, department (e.g.     FRONT_DESK, HOUSEKEEPING, MAINTENANCE, KITCHEN, SECURITY, HR, MARKETING, IT, FINANCE)
- Relationships:
  - Employees in houseKeeping department is assigned for specific tasks.


### Room
- Description: Represents the rooms in the hotel
- Attributes: ID, Room number, Floor number, view (e.g. Pool, garden, sea), status (e.g. Available, reserved , checked_Out, checked_In), cleanliness_status (e.g. Dirty, underMaintenance, cleaned)
- Relationships:
  - Customer books a room


### Room type 
- Description: Represents the rooms in the hotel
- Attributes: ID, price, type name , room size, number of adults, number of childrens
- Relationships:
  - Each room is related to room type. 

### Features
- Description: Represents the room features in the hotel
- Attributes: ID, feature and description.
- Relationships:
  - Assign feature to room type.
    
###  Room Type Feature 
- Description: Each room type has specific features.
- Attributes: ID, room type id, feature id
- Relationships:
  - Each room type has a features.

### Bed type
- Description: Represents the beds in the hotel
- Attributes: ID, bed type.
- Relationships:
  - Each bed type is assigned for specific Room Type.
 
###  Room Type Bed 
- Description: Each room type has specific type of beds.
- Attributes: ID, room type id, bed id, number of beds.
- Relationships:
  - Each room type has a a bed type.

###  House keeping task
- Description: This is for managing houe keeping taks in the hotel, employees are asigned for specific rooms.
- Attributes: ID, description, scheduledDate, room id, employee id, status (e.g. pending, inProgress, completed). 
- Relationships:
  - Each task is assigned for a specific employee for a specific room in specific date.



## Setup Instructions


## Lessons Learned
- Understanding of Spring Boot and RESTful API development.
- Hands-on experience with Docker and Docker Compose for containerization.
- Knowledge of JWT for securing APIs.
- Implementing role-based access control.
- Experience with API versioning and documentation using OAS 3.1.0.

## Contributors
- Student 1: Hala Abdel Halim.
- Student 2: Eliana Ellati
