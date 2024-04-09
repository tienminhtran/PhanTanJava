package dao;

import AppUtile.AppUtil;
import entity.Course;
import entity.Student;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.types.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CDao {
    private Driver driver;

    private SessionConfig sessionConfig;

    public CDao(String database) {

        driver = AppUtil.initDriver();
        sessionConfig = SessionConfig.builder().withDatabase(database).build();
    }


    /**
     * MATCH (c:Course)-[BELONGS_TO]->(d:Department) where d.departmentID = "CS" return c
     *
     * @param maKhoa
     * @return
     */
    public List<Course> danhSachKhoaHocTheoKhoa(String maKhoa) {
        String cql = "MATCH (c:Course)-[BELONGS_TO]->(d:Department) where d.departmentID = $departmentID return c";
        Map<String, Object> map = Map.of("departmentID", maKhoa);
        List<Course> courses = new ArrayList<>();
        try (Session session = driver.session(sessionConfig)) {
            Result result = session.run(cql, map);
            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("c").asNode();
                Course course = AppUtil.nodeToPOJO(node, Course.class);
                courses.add(course);
            }
            return courses;
        }


    }

    public void addCourse(Course course) {
        String cpl = "CREATE (c:Course) SET c.courseID=$courseID, c.name = $name, c.hours = $hours";
        Map<String, Object> map = Map.of("courseID", course.getCourseID(),
                "name", course.getName(), "hours", course.getHours());
        try (Session session = driver.session(sessionConfig)) {
            session.run(cpl, map);
        }

    }

    /**
     * Liệt ke khoa học của cs va ie
     * có 2 cách viết truy vân MATCH (n:Course)
     * WHERE n.courseID STARTS WITH 'CS' or  n.courseID STARTS WITH 'IE'
     * RETURN n
     * <p>
     * <p>
     * cach 2 sài
     * MATCH (d:Department)
     * WHERE d.departmentID = "CS" OR d.departmentID = "IE"
     * MATCH (c:Course)-[:BELONGS_TO]->(d)
     * RETURN c;
     */
    public List<Course> danhSachKhoaHocKhoaCSandIE() {
        String p = "MATCH (n:Course) WHERE n.courseID STARTS WITH 'CS' or  n.courseID STARTS WITH 'IE' RETURN n";
        List<Course> list = new ArrayList<>();
        try (Session session = driver.session(sessionConfig)) {
            Result result = session.run(p);
            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("n").asNode();
                Course course = new Course(
                        node.get("courseID").asString(),
                        node.get("name").asString(),
                        node.get("hours").asInt(), null
                );
                list.add(course);
            }
        }
        return list;
    }


    public void close() {
        driver.close();
    }

    public static void main(String[] args) {


    }
}
