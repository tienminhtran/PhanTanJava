package server;

import dao.CourseDao;
import dao.DepartmentDao;
import dao.StudentGradeDao;
import entity.Course;
import entity.Department;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(1234)) {
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
        private DepartmentDao departmentDao;

        private CourseDao courseDao;

        private StudentGradeDao studentGradeDao;

        public Handler(Socket socket) {
            this.socket = socket;
            departmentDao = new DepartmentDao();
            courseDao = new CourseDao();
            studentGradeDao = new StudentGradeDao();

        }

        @Override
        public void run() {
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                ObjectOutputStream outs = new ObjectOutputStream(socket.getOutputStream());



                int choose;
                while (true) {
                    choose = in.readInt();
                    System.out.println("Client choose: " + choose);
                    switch (choose) {
                        case 1:
                            List<Department> departments = departmentDao.getAllDepartments();
                            outs.writeObject(departments);
                            break;
                        case 2:
                            int ma;
                            ma = in.readInt();
                            outs.writeObject(departmentDao.getAllDepartmentsById(ma));
                            break;
                        case 3:
                            int ma1;
                            ma1 = in.readInt();
                            List<Course> courses = courseDao.getAllCoursesByDepartmentId(ma1);
                            outs.writeObject(courses);
                            break;
                        case 4:
                            int ma3;
                            ma3 = in.readInt();
                            System.out.println(ma3 = in.readInt());


                            int administrartor;
                            System.out.println(administrartor = in.readInt());
                            double budget;
                            System.out.println(budget = in.readDouble());
                            String name;

                            administrartor = in.readInt();
                            budget = in.readDouble();
                            name = in.readUTF();

                            Department department = new Department(administrartor, budget, ma3, name, LocalDate.now());
                            departmentDao.updateId(ma3, department);
                            outs.writeObject(department);
                            break;
                        case 5:
                            int ma2;
                            ma2 = in.readInt();
                            outs.writeObject(courseDao.getCourseById(ma2));
                            break;
                        case 6:
                            int ma4 = in.readInt(); // Read the int value sent by the client
                            long grade = studentGradeDao.getStudent_PropertiesOne_Grade(ma4); // Get the grade
                            out.writeLong(grade); // Send the grade as a long value
                            break;


                    }
                }
            }  catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
