package entity;
//course_id	name	hours
//CS101	Programming	4

public class Course {
	private String courseId;
	private String name;
	private int hours;
	
	private Department department;

	public Course() {
		super();
	}

	public Course(String courseId, String name, int hours) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.hours = hours;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public Department getDepartment() {
		return department;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", name=" + name + ", hours=" + hours + "]";
	}
}
