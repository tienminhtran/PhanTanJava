import entity.Customer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("Admin-PC", 0611);
            System.out.println("Connected to server");
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("1. ");
                System.out.println("2. ");
                System.out.println("3. ");

                System.out.println("Enter your choice: ");
                int choice = sc.nextInt();
                dos.writeInt(choice);
                switch (choice) {
                    case 1:
                        System.out.println("Enter type ");
                        String type = sc.next();

                        System.out.println("Enter priceFrom ");
                        double priceFrom = sc.nextDouble();

                        System.out.println("Enter priceTo ");
                        double priceTo = sc.nextDouble();


                        dos.writeUTF(type);
                        dos.writeDouble(priceFrom);
                        dos.writeDouble(priceTo);

                        List<entity.Room> list = (List<entity.Room>) ois.readObject();
                           for (entity.Room room : list) {
                                System.out.println(room);
                            }

                        break;
                    case 2:
                        System.out.println("Enter type ");
                        String type1 = sc.next();
                        System.out.println("Enter month ");
                        int month = sc.nextInt();
                        System.out.println("Enter year ");
                        int year = sc.nextInt();

                        dos.writeUTF(type1);
                        dos.writeInt(month);
                        dos.writeInt(year);
                        List<Customer> list1 = (List<Customer>) ois.readObject();
                        for (Customer customer : list1) {
                            System.out.println(customer);
                        }

                        break;
                    case 3:
                        System.out.println("Bed options: ");
                        String bed = sc.next();
                        dos.writeUTF(bed);

                        Set<String> set = (Set<String>) ois.readObject();
                        for (String s : set) {
                            System.out.println(s);
                        }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
