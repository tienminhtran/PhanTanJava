/*
 * @(#)Grades.java 1.0  23/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     23/2/2024
 *@Version:  1.0
 */


package iuh.se;

public class Grades {
    private Date date;
    private String grade;
    private int score;

    public Grades() {
    }

    public Grades(Date date, String grade, int score) {
        this.date = date;
        this.grade = grade;
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "date=" + date +
                ", grade='" + grade + '\'' +
                ", score=" + score +
                '}';
    }
}
