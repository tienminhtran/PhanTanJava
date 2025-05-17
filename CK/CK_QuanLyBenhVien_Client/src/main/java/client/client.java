package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {

        try{
            Socket socket = new Socket("Admin-PC", 0611);
            System.out.println("Connected to server");
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Scanner sc = new Scanner(System.in);
            while (true){
                System.out.println("1. ");
                System.out.println("2. ");
                System.out.println("3. ");
                System.out.println("Enter your choice: ");
                int choice = sc.nextInt();
                dos.writeInt(choice);
                switch (choice) {
                    case 1:
                        System.out.println("Enter patient id format xxx-xx-xxxx: ");
                        String id = sc.next();

                        System.out.println("Enter patient name: ");
                        String name = sc.next();

                        System.out.println("Enter patient phone: ");
                        String phone = sc.next();

                        System.out.println("Enter patient address: ");
                        String address = sc.next();

                        System.out.println("Enter patient date of birth format yyyy-mm-dd: ");
                        System.out.println("Enter year: ");
                        int year = sc.nextInt();
                        System.out.println("Enter month: ");
                        int month = sc.nextInt();
                        System.out.println("Enter day: ");
                        int day = sc.nextInt();

                        System.out.println("Gioi tinh: ");
                        String gt = sc.next();

                        dos.writeUTF(id);
                        dos.writeUTF(name);
                        dos.writeUTF(phone);
                        dos.writeUTF(address);
                        dos.writeInt(year);
                        dos.writeInt(month);
                        dos.writeInt(day);
                        dos.writeUTF(gt);

                        break;

                    case 2:
                        System.out.println("Enter doctor ii de tim keim bds bac si , nhậpdeptName ");
                        String deptName = sc.next();
                        dos.writeUTF(deptName);

                        List<entity.Doctor> doctors = (List<entity.Doctor>) ois.readObject();
                        for (entity.Doctor doctor : doctors) {
                            System.out.println(doctor);
                        }


                        break;


                    case 3:

                            break;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
