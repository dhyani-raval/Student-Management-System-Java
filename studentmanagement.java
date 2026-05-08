import java.sql.*;
import java.util.Scanner;

public class studentmanagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dhyani",
                "root",
                "1403"
            );

            int choice;

            do {

                System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
                System.out.println("1. Insert Student");
                System.out.println("2. Display Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");

                System.out.print("Enter Choice: ");
                choice = sc.nextInt();

                switch(choice) {

                    case 1:

                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Course: ");
                        String course = sc.nextLine();

                        System.out.print("Enter Marks: ");
                        int marks = sc.nextInt();

                        String insertQuery =
                            "INSERT INTO students(name,course,marks) VALUES(?,?,?)";

                        PreparedStatement ps1 =
                            con.prepareStatement(insertQuery);

                        ps1.setString(1, name);
                        ps1.setString(2, course);
                        ps1.setInt(3, marks);

                        int rows = ps1.executeUpdate();

                        if(rows > 0) {
                            System.out.println("Student Added Successfully");
                        }

                        break;

                    case 2:

                        Statement stmt = con.createStatement();

                        ResultSet rs =
                            stmt.executeQuery("SELECT * FROM students");

                        System.out.println("\nID\tNAME\tCOURSE\tMARKS");

                        while(rs.next()) {

                            System.out.println(
                                rs.getInt("id") + "\t" +
                                rs.getString("name") + "\t" +
                                rs.getString("course") + "\t" +
                                rs.getInt("marks")
                            );
                        }

                        break;

                    case 3:

                        System.out.print("Enter Student ID to Update: ");
                        int uid = sc.nextInt();

                        System.out.print("Enter New Marks: ");
                        int newMarks = sc.nextInt();

                        String updateQuery =
                            "UPDATE students SET marks=? WHERE id=?";

                        PreparedStatement ps2 =
                            con.prepareStatement(updateQuery);

                        ps2.setInt(1, newMarks);
                        ps2.setInt(2, uid);

                        int update = ps2.executeUpdate();

                        if(update > 0) {
                            System.out.println("Updated Successfully");
                        }

                        break;

                    case 4:

                        System.out.print("Enter Student ID to Delete: ");
                        int did = sc.nextInt();

                        String deleteQuery =
                            "DELETE FROM students WHERE id=?";

                        PreparedStatement ps3 =
                            con.prepareStatement(deleteQuery);

                        ps3.setInt(1, did);

                        int del = ps3.executeUpdate();

                        if(del > 0) {
                            System.out.println("Deleted Successfully");
                        }

                        break;

                    case 5:

                        System.out.println("Exiting Program...");
                        break;

                    default:

                        System.out.println("Invalid Choice");
                }

            } while(choice != 5);

            con.close();

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}