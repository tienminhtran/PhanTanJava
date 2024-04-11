/*
 * @(#)text.java 1.0  21/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     21/2/2024
 *@Version:  1.0
 */


import entity.Student;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;

public class text {
    public static void main(String[] args) {
        String uri = "bolt://localhost:7687";
        String user = "neo4j";
        String password = "12345678";
        Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));

        Session session = driver.session(SessionConfig.forDatabase("coursedb"));
        Transaction tr = session.beginTransaction();

        //Tìm kiếm sinh viên khi biết mã số
        String ma ="11";
        String query = "MATCH (s:Student) WHERE s.studentID = $ma RETURN s;";

        Result result = tr.run(query, Values.parameters("ma", ma));

        Record record = result.single();

        Node node = record.get("s").asNode();




        Student st = new Student();
        st.setStudentID(node.get("studentID").asString());
        st.setName(node.get("name").asString());
        st.setGpa((float) node.get("gpa").asFloat());

        System.out.println(st);




    }
}
