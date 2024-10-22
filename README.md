# Hospital Management System

## Project Overview

This Hospital Management System is a terminal-based application that helps in managing hospital-related activities like handling patient records, appointments, doctor information, and much more. The project is built using **Java** and **MySQL**.

## Features

- Patient Management:
  - Add, view, update, and delete patient records.
- Doctor Management:
  - Manage doctor details.
- Appointment Management:
  - Schedule, view, and cancel appointments.
- Search:
  - Search patients by ID.
  
## Technologies Used

- **Java**: Backend logic and business layer.
- **MySQL**: Database to store patient, doctor, and appointment data.
- **JDBC**: Java Database Connectivity to connect Java with MySQL.

## How to Run the Project

### Prerequisites

1. **Java**: Ensure that Java is installed on your machine. You can download it from [here](https://www.oracle.com/java/technologies/javase-downloads.html).
2. **MySQL**: Install MySQL and set up the necessary database. You can get MySQL [here](https://dev.mysql.com/downloads/).

### Steps to Run

1. Clone the repository:
    ```bash
    git clone https://github.com/beheramaheswar67/Hospital-Management-System.git
    ```

2. Navigate to the project directory:
    ```bash
    cd Hospital-Management-System
    ```

3. Create the MySQL database:
    - Set up the database using the scripts in the `sql` folder (if any).
    - Update the database connection in the Java files (if needed).

4. Compile the project:
    ```bash
    javac Main.java
    ```

5. Run the project:
    ```bash
    java Main
    ```

## Database Schema

- **patients** table:
  - `id`: int (Primary Key)
  - `name`: varchar
  - `age`: int
  - `gender`: varchar
  - `address`: varchar

- **doctors** table:
  - `id`: int (Primary Key)
  - `name`: varchar
  - `specialization`: varchar
  - `experience`: int

- **appointments** table:
  - `id`: int (Primary Key)
  - `patient_id`: int (Foreign Key)
  - `doctor_id`: int (Foreign Key)
  - `appointment_date`: date

## Contributing

Feel free to submit pull requests or report any issues. Contributions are always welcome!

## License

No
