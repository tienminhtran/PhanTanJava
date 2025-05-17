package sever;

import dao.impl.DoctorDao;
import dao.impl.PatientDao;
import entity.Doctor;
import entity.Patient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(0611)) {
            System.out.println("Ready....");
            while (true) {
                Socket socket = server.accept();
                System.out.println("Host's client: " + socket.getInetAddress().getHostName());
                Server temp = new Server();
                new Thread(temp.new Handler(socket)).start();

            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    private class Handler implements Runnable {
        private Socket socket;
        private DoctorDao doctorDao;

        private PatientDao patientDao;


        public Handler(Socket socket) {
            this.socket = socket;
            doctorDao = new DoctorDao();
            patientDao = new PatientDao();

        }

        @Override
        public void run() {
            try{
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                int choice;
                while(true){
                    choice = dis.readInt();

                    switch (choice) {
                        case 1:
                            String id = dis.readUTF();
                            String name = dis.readUTF();
                            String phone = dis.readUTF();
                            String address = dis.readUTF();
                            int year = dis.readInt();
                            int month = dis.readInt();
                            int day = dis.readInt();
                            LocalDate dateOfBirth = LocalDate.of(year, month, day);
                            String gt = dis.readUTF();
                            Patient P = new Patient(id, name, phone, gt, dateOfBirth, address);
                            if(patientDao.addPatient(P)){
                                dos.writeUTF("Patient added successfully");
                            }else{
                                dos.writeUTF("Patient added failed");
                            }
                            break;
                        case 2:
                            String dept = dis.readUTF();
                            List<Doctor> list = doctorDao.getDoctorByDepartment(dept);

                            oos.writeObject(list);

                            break;

                    }
                }


            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}



