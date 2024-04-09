import entity.Course;
import entity.Department;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 1234)) {
            System.out.println("Connected to server");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Enter your chosing: " +
                        "\n1. get All Departments: " +
                        "\n2. get Department By Id: " +
                        "\n3. get All Courses By Department Id" +
                        "\n4. Add new Department" +
                        "\n5. Course find By Id" +
                        "\n6. get All Courses By Department Id"

                );
                int choose = sc.nextInt();
                out.writeInt(choose);
                switch (choose) {
                    case 1:
                        // sc.nextInt();
                        List<Department> departments = (List<Department>) ois.readObject();
                        for (Department department : departments) {
                            System.out.println(department);
                        }
                        break;
                    case 2:
                        System.out.println("Enter id find: ");
                        int id = sc.nextInt();
                        out.writeInt(id);

                        List<Department> departments1 = (List<Department>) ois.readObject();
                        for (Department department : departments1) {
                            System.out.println(department);
                        }

                        break;

                    case 3:
                        System.out.println("Enter department Id  find: ");
                        int ma = sc.nextInt();
                        out.writeInt(ma);

                        List<Course> courses = (List<Course>) ois.readObject();
                        for (Course course : courses) {
                            System.out.println(course);
                        }
                        break;

                    case 4:
                        System.out.println("  -Nhap ID Department you wish update {int} : ");
                        int id_department = sc.nextInt();
                        out.writeInt(id_department);

                        System.out.println("  -Nhap Administrartor_new {int} : ");
                        int administrartor = sc.nextInt();
                        out.writeInt(administrartor);

                        System.out.println("  -Nhap budget_new {dobule} : ");
                        double budget = sc.nextDouble();
                        out.writeDouble(budget);

                        sc = new Scanner(System.in);
                        System.out.println("  -Nhap name_new {String} : ");
                        String name = sc.nextLine();
                        out.writeBytes(name);

//                        System.out.println("  -Nhap startDate: ");
//                        System.out.println("        +Nhap day {int} : ");
//                        int day = sc.nextInt();
//                        out.writeInt(day);
//
//                        System.out.println("        +Nhap month {int} : ");
//                        int month = sc.nextInt();
//                        out.writeInt(month);
//
//                        System.out.println("        +Nhap year {int} : ");
//                        int year = sc.nextInt();
//                        out.writeInt(year);


                        break;

                    case 5:
                        System.out.println("Enter id find: ");
                        int id3 = sc.nextInt();
                        out.writeInt(id3);

                        Course course = (Course) ois.readObject();
                        System.out.println(course);

                        break;

                    case 6:
                        System.out.println("Enter id course : ");
                        int id4 = sc.nextInt();
                        out.writeInt(id4); // Send the course ID to the server
                        long grade = in.readLong(); // Receive the grade from the server
                        System.out.println(grade);
                        break;


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
