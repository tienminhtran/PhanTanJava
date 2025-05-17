package server;

import dao.impl.CustomerDao;
import dao.impl.RoomDao;
import entity.Customer;
import entity.Room;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(0611)) {
            System.out.println("Ready....");
            while (true) {
                Socket socket = server.accept();
                System.out.println("Host's client: " + socket.getInetAddress().getHostName());
                server temp = new server();
                new Thread(temp.new Handler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Handler implements Runnable {
        private Socket socket;
        private CustomerDao customerDao;

        private RoomDao roomDao;

        public Handler(Socket socket) {
            this.socket = socket;
            customerDao = new CustomerDao();
            roomDao = new RoomDao();
        }

        @Override
        public void run() {
            try {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                int choice;
                while (true) {
                    choice = dis.readInt();
                    switch (choice) {
                        case 1:
                            String type = dis.readUTF();
                            double priceFrom = dis.readDouble();
                            double priceTo = dis.readDouble();

                            List<Room> list = roomDao.listRooms(type, priceFrom, priceTo);
                            oos.writeObject(list);

                            break;
                        case 2:
                           String type1 = dis.readUTF();
                            int month = dis.readInt();
                            int year = dis.readInt();

                            List< Customer> list1 = customerDao.listCustoersByCheckMonthYear(type1, month, year);
                            oos.writeObject(list1);
                            break;
                        case 3:
                            String bed = dis.readUTF();
                            List<String> list2 = new ArrayList<>();
                            Set<String> set = roomDao.listRoomTypes(bed);
                            list2.addAll(set);
                            oos.writeObject(list2);
                            break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

