package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctors {
    private Connection connection;
    private Scanner scanner;

    public Doctors(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public void ViewDoctors(){
        String Query = "SELECT * FROM doctors";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+-------+---------------------+--------------------------+");
            System.out.println("| ID    | Name                | Specialization           |");
            System.out.println("+-------+---------------------+--------------------------+");

            while (resultSet.next()){
                int Id = resultSet.getInt("id");
                String Name = resultSet.getString("name");
                String splz = resultSet.getString("specialization");
                System.out.printf("|%-7s|%-21s|%-26s|\n", Id, Name, splz);
                System.out.println("+-------+---------------------+--------------------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void CheckDoctors(){
        System.out.print("Enter Doctors ID: ");
        int id = scanner.nextInt();
        if(CheckDoctorById(id) == true) {
            String Query = "SELECT name, specialization FROM doctors WHERE id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(Query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println("Here is the Doctors Details");
                System.out.println("+----------------+----------------------------+");
                System.out.println("| Name           | Specialization             |");
                System.out.println("+----------------+----------------------------+");
                while (resultSet.next()) {
                    String Name = resultSet.getString("name");
                    String spl = resultSet.getString("specialization");
                    System.out.printf("|%-16s|%-28s|\n", Name, spl);
                    System.out.println("+----------------+----------------------------+");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Doctors ID not found !!");
        }
    }

    public boolean CheckDoctorById(int id){
        String Query = "SELECT *FROM doctors WHERE id = ?";
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
