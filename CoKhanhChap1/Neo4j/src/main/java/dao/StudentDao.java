package dao;

import AppUtile.AppUtil;
import entity.Course;
import entity.Department;
import entity.Student;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDao {
    private Driver driver;

    private SessionConfig sessionConfig;


    public StudentDao(String database) {

        driver = AppUtil.initDriver();
        sessionConfig = SessionConfig.builder().withDatabase(database).build();


    }

    public List<Student> danhSachSV(int sosv) {
        String cql = "MATCH (s:Student) RETURN s LIMIT $sosv";
        Map<String, Object> map = Map.of("sosv", sosv);
        List<Student> students = new ArrayList<>();
        try (Session session = driver.session(sessionConfig)) {
            Result result = session.run(cql, map);
            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("s").asNode();
                Student student = AppUtil.nodeToPOJO(node, Student.class);
                students.add(student);
            }
            return students;
        }
    }

    /**
     * Match (s:Student) where s.studentID="11" RETURN s
     *
     * @param maSV
     * @return
     */
    public Student findStudentById(String maSV) {
        String q = "Match (s:Student) where s.studentID=$studentID RETURN s";
        Map<String, Object> map = Map.of("studentID", maSV);
        try (Session session = driver.session(sessionConfig)) {
            Result result = session.run(q, map);
            if (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("s").asNode();
                Student student = AppUtil.nodeToPOJO(node, Student.class);
                return student;
            }
            return null;
        }

    }

    public List<Student> danhSachSinhVienTheoMaLop(String malop) {
        String p = "MATCH (S:Student)-[ENROLLED]-> (C:Course) WHERE C.courseID = $courseID return S";
        List<Student> list = new ArrayList<>();
        try (Session session = driver.session(sessionConfig)) {
            Result result = session.run(p, Map.of("courseID", malop));
            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("S").asNode();
                Student student = new Student(
                        node.get("studentID").asString(),
                        node.get("name").asString(),
                        node.get("gpa").asDouble()
                );
                list.add(student);
            }
        }
        return list;
    }

    /**
     * MATCH (S:Student)-[ENROLLED]->(C:Course)
     * MATCH (C)-[BELONGS_TO]->(D:Department)
     * RETURN D.departmentID, count(S)
     */
    public Map<String, Integer> tongSoSVCuaMoiKhoa() {
        String q = "MATCH (S:Student)-[ENROLLED]->(C:Course) " +
                "MATCH (C)-[BELONGS_TO]->(D:Department) " +
                "RETURN D.departmentID, count(S) as tong ORDER BY tong";
        Map<String, Integer> map = new HashMap<>();
        try (Session session = driver.session(sessionConfig)) {
            Result result = session.run(q);
            while (result.hasNext()) {
                Record record = result.next();
                String tenKhoa = record.get("D.departmentID").asString();
                int sl = record.get("tong").asInt();
                map.put(tenKhoa, sl);
            }

        }
        return map;
    }


    //DESC giảm dan
    public Map<String, Integer> tongSoSVCuaMoiKhoa_SSMaKhoa() {
        String q = "MATCH (S:Student)-[ENROLLED]->(C:Course) " +
                "MATCH (C)-[BELONGS_TO]->(D:Department) " +
                "RETURN D.departmentID, count(S) as tong ORDER BY D.departmentID";
        Map<String, Integer> map = new HashMap<>();
        try (Session session = driver.session(sessionConfig)) {
            Result result = session.run(q);
            while (result.hasNext()) {
                Record record = result.next();
                String tenKhoa = record.get("D.departmentID").asString();
                int sl = record.get("tong").asInt();
                map.put(tenKhoa, sl);
            }

        }
        return map;
    }

    public void dachSachTruongKhoaKCoSV() {
        String q = "MATCH (S:Student)-[ENROLLED]->(C:Course)                    MATCH (C)-[BELONGS_TO]->(D:Department)   WITH D, COUNT(S) AS student_count\n" +
                "WHERE student_count = 3\n" +
                "RETURN D.dean";
        try (Session session = driver.session(sessionConfig)) {
            Result result = session.run(q);
            while (result.hasNext()) {
                Record record = result.next();
                String tenKhoa = record.get("D.dean").asString();
                System.out.println(tenKhoa);
            }
        }
    }

    public void close() {
        driver.close();
    }

    public static void main(String[] args) {
//        danhSachSinhVienTheoMaLop

        StudentDao studentDao = new StudentDao("coursedb");
//        List<Student> students = studentDao.danhSachSinhVienTheoMaLop("CS101");
//        System.out.println(students);
//        studentDao.close();

//        System.out.println(studentDao.tongSoSVCuaMoiKhoa());
//        System.out.println(studentDao.tongSoSVCuaMoiKhoa_SSMaKhoa());
        studentDao.dachSachTruongKhoaKCoSV();
    }
}
