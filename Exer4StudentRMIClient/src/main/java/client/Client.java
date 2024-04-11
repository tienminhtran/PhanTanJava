package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;

import dao.CourseDao;
import dao.DepartmentDao;
import dao.StudentDao;
import entity.Student;


public class Client {
	private static final String URL = "rmi://H92M17:7878/";

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		CourseDao courseDao = (CourseDao) Naming.lookup(URL + "courseDao");
		StudentDao studentDao = (StudentDao) Naming.lookup(	URL + "studentDao");
		DepartmentDao departmentDao = (DepartmentDao) Naming.lookup(URL + "departmentDao");


		Student student = new Student();
		student.setLastName("Tran Minh");
		student.setFirstName("Tien");

		studentDao.add(student);


		studentDao.findAll();


	}
}
