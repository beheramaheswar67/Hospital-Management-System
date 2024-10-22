package HospitalManagementSystem;
import java.sql.*;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient(){
        System.out.print("Patient Name: ");
        String patient_name = scanner.next();
        scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Gender: ");
        String gender = scanner.nextLine();
        try{
            String Query = "INSERT INTO patients (name, age, gender) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setString(1,patient_name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Patient Added Successfully!!");
            }else {
                System.out.println("Failed!!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void ViewPatient(){
        String Query = "SELECT *FROM Patients";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+------------+----------------------------+-----------+----------+");
            System.out.println("| Patient ID | Name                       | Age       | Gender   |");
            System.out.println("+------------+----------------------------+-----------+----------+");
            while (resultSet.next()){
               int Id = resultSet.getInt("id");
                String Name = resultSet.getString("name");
                int Age = resultSet.getInt("age");
                String Gender = resultSet.getString("gender");
                System.out.printf("|%-12s|%-28s|%-11s|%-10s|\n",Id, Name, Age, Gender); //%-12s means 12 time spase
                System.out.println("+------------+----------------------------+-----------+----------+");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id){
        String Query = " SELECT *FROM patients WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
