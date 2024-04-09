package dao;

import AppUtile.AppUtil;
import entity.Department;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDao {
    private Driver driver;
    private SessionConfig sessionConfig;

    public DepartmentDao(String database) {
        driver = AppUtil.initDriver();
        sessionConfig = SessionConfig.builder().withDatabase(database).build();
    }

    /**
     * Match (d:Department) where d.departmentID = "Math" set d.name = "aaaa"
     *
     * @param departmentID
     * @param newName
     */
    public void upDateName(String departmentID, String newName) {
        String cql = "MATCH (d:Department) where d.departmentID = $departmentID " +
                "set d.departmentName = $departmentName";
        Map<String, Object> map = Map.of("departmentID", departmentID, "departmentName", newName);
        try (Session session = driver.session(sessionConfig)) {
            session.run(cql, map);
        }
    }

    /**
     * MATCH (d:Department)
     * WHERE d.departmentID = "Math"
     * SET d.name = "aaaa", d.room = 120
     *
     * @param department
     */

    public void upDateDepartment(Department department) {
        String cql = "MATCH (d:Department) \n" +
                "WHERE d.departmentID = \"Math\" \n" +
                "SET d.name = $name, d.building = $building";
        Map<String, Object> map = Map.of("name", department.getName(), "building", department.getBuilding());
        try (Session session = driver.session(sessionConfig)) {
            session.run(cql, map);
        }

    }

    public List<Department> danhSachKhoa() {
        String cql = "MATCH (d:Department) return d";
        List<Department> list =  new ArrayList<>();
        try (Session session = driver.session(sessionConfig)) {
            Result result = session.run(cql);
            while (result.hasNext()) {
                Node node = result.next().get("d").asNode();
                Department department = AppUtil.nodeToPOJO(node, Department.class);
                list.add(department);
            }

        }
        return list;
    }

    /**
     * Dùng index để lấy danh sách các trưởng khoa
     * MATCH (n:Department) RETURN n.dean
     * @return
     */
    public Map<Integer,String> danhSachCacTruongKhoa() {
        String cql = "MATCH (n:Department) RETURN n.dean";
        Map<Integer, String> map = new HashMap<>();
        int dem = 0;
        try (Session session = driver.session(sessionConfig)) {
            Result result = session.run(cql);
            while (result.hasNext()) {
                 Record record = result.next();
                String tenTk = record.get("n.dean").asString();
                map.put(dem++, tenTk);

            }
        }
        return map;
    }

    public Map<String,String> danhSachCacTruongKhoa_maKhoa() {
        String cql = "MATCH (n:Department) RETURN n.departmentID, n.dean";
        Map<String, String> map = new HashMap<>();
        try (Session session = driver.session(sessionConfig)) {
            Result result = session.run(cql);
            while (result.hasNext()) {
                Record record = result.next();
                String maKhoa = record.get("n.departmentID").asString();
                String tenTk = record.get("n.dean").asString();
                map.put(maKhoa, tenTk);

            }
        }
        return map;
    }


    /**
     * MATCH (n:Department) WHERE n.departmentID = $departmentID RETURN n.departmentID, n.dean
     */

    public Map<String,String> danhSachCacTruongKhoa_TheoMa(String maKhoa) {
        String cql = "MATCH (n:Department) WHERE n.departmentID = $MA " +
                "RETURN n.departmentID, n.dean";

        Map<String, String> map = new HashMap<>();
        try (Session session = driver.session(sessionConfig)) {
            Result result = session.run(cql, Map.of("MA", maKhoa));
            while (result.hasNext()) {
                Record record = result.next();
                String maKhoa1 = record.get("n.departmentID").asString();
                String tenTk = record.get("n.dean").asString();
                map.put(maKhoa1, tenTk);

            }
        }
        return map;
    }
    public void close() {
        driver.close();
    }

    public static void main(String[] args) {
        DepartmentDao departmentDao = new DepartmentDao("coursedb");
//        departmentDao.upDateName("Math", "bbb");

//        departmentDao.upDateDepartment(new Department("Math", "qqqq", "Carson", "USA", null));


        departmentDao.close();
    }


}
