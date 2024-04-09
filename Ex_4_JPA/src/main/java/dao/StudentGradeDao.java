package dao;

import entity.Department;
import entity.StudentGrade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class StudentGradeDao extends GenericDao<StudentGrade>{
    private EntityManager em;
    public StudentGradeDao() {
        em = Persistence.createEntityManagerFactory("ex4")
                .createEntityManager();
    }
    public boolean addStudentGrade(StudentGrade studentGrade) {
        return add(studentGrade);
    }
    public boolean updateStudentGrade(StudentGrade studentGrade) {
        return update(studentGrade);
    }
    public boolean deleteStudentGrade(StudentGrade studentGrade) {
        return delete(studentGrade);
    }
    public int getStudent_PropertiesOne_Grade(int maMon) {
        Long result = em.createNamedQuery("StudentGrade.TotalStudents", Long.class)
                .setParameter("id", maMon)
                .getSingleResult();
        return result.intValue();

    }

    public static void main(String[] args) {
        StudentGradeDao studentGradeDao = new StudentGradeDao();
        System.out.println(studentGradeDao.getStudent_PropertiesOne_Grade(2021)) ;

    }



}
