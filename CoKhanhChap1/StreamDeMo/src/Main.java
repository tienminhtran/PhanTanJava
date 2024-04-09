import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Student> students = StudentData.getStudents();
        // số sinh viên theo từng lớp
        Map<String, Long> rs1 = students
                .stream()
                .sorted(Comparator.comparing(Student-> Student.getClassName()))
                .collect(Collectors.groupingBy(st -> st.getClassName(), LinkedHashMap::new, Collectors.counting()));
        System.out.println(rs1);


        // Cách 2: số sinh viên theo từng lớp
        students.stream()
                .collect(Collectors.groupingBy(st -> st.getClassName(), Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + " : " + v));



        // Cách 2: sort theo số sinh viên theo từng lớp
        students.stream()
                .collect(Collectors.groupingBy(st -> st.getClassName(), Collectors.counting()))
                .entrySet() // chuyển map thành set
                .stream()
                .sorted(Comparator.comparing(Map-> Map.getValue()))
                .forEach(System.out::println);

        // Tìm sinh viên có tên bắt đầu bằng H và tuổi là 20
        List<Student> result3 = students
                .stream()
                .filter(student -> student.getName().startsWith("H") && student.getAge() == 20)
                .collect(Collectors.toList());
        System.out.println(result3);



    }

}