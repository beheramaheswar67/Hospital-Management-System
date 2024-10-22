package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;


public class Hospital_Management_System {
    // private coz no one can access outside from the class coz it is confidential
    // static for use we don't need to create methods for access
    // final for no one can change these values
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "mahesh123";


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Successfully Done!!");
            Patient patient = new Patient(connection, scanner);
            Doctors doctors = new Doctors(connection, scanner);
            while (true) {
                System.out.println("HOSPITAL MANAGEMENT SYSTEM ");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patient");
                System.out.println("3. View Doctors");
                System.out.println("4. Check Doctor By ID");
                System.out.println("5. Book Appointment");
                System.out.println("6. View Appointment");
                System.out.println("0. Exit");
                System.out.print("Enter any Choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        patient.ViewPatient();
                        System.out.println();
                        break;
                    case 3:
                        doctors.ViewDoctors();
                        System.out.println();
                        break;
                    case 4:
                        doctors.CheckDoctors();
                        System.out.println();
                        break;
                    case 5:
                        Appointment(patient, doctors, connection, scanner);
                        System.out.println();
                        break;
                    case 6:
                        ViewAppointment(connection);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Please enter valid input ");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public static void Appointment(Patient patient, Doctors doctors, Connection connection,Scanner scanner){
            System.out.print("Enter Patient ID: ");
            int patientId = scanner.nextInt();
            System.out.print("Enter Doctors ID: ");
            int doctorId = scanner.nextInt();
            System.out.print("Enter Appointment Date (YYYY-MM-DD):");
            String appointmentDate = scanner.next();
            if(patient.getPatientById(patientId) && doctors.CheckDoctorById(doctorId)){
                if(checkDoctorAvailablity(doctorId, appointmentDate, connection)){
                    String appointmentQuery = "INSERT INTO appointments (patient_id, doctor_id, appoinment_date) VALUES(?, ?, ?)";
                    try{
                        PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                        preparedStatement.setInt(1,patientId);
                        preparedStatement.setInt(2,doctorId);
                        preparedStatement.setString(3,appointmentDate);
                        int AffectedRow = preparedStatement.executeUpdate();
                        if(AffectedRow>0){
                            System.out.println("Appointment Booking Successfully Done!!");
                        }else {
                            System.out.println("Failed!!");
                        }

                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }else {
                    System.out.println("Doctor not available on this date!!");
                }

            }else {
                System.out.println("Either doctor or patient doesn't exist!!!");
            }
        }
        public static boolean checkDoctorAvailablity(int doctorId, String appointmentDate, Connection connection){
        String Query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appoinment_date = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1,doctorId);
            preparedStatement.setString(2,appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                if(count== 0){
                    return true;
                }else {
                    return false;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
        }

        public static void ViewAppointment(Connection connection){
        String Query = "SELECT *FROM appointments";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Appointments Details");
            System.out.println("+-------+------------+-----------+------------------+");
            System.out.println("| ID    | Patient ID | Doctor ID | Appointment Date |");
            System.out.println("+-------+------------+-----------+------------------+");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int p_id = resultSet.getInt("patient_id");
                int d_id = resultSet.getInt("doctor_id");
                String date = String.valueOf(resultSet.getDate("appoinment_date"));
                System.out.printf("|%-7s|%-12s|%-11s|%-18s|\n", id, p_id, d_id, date);
                System.out.println("+-------+------------+-----------+------------------+");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        }

    }

