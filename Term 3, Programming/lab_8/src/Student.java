/**
 * Created by Yule Cat on 08.11.2017.
 */
public class Student implements Comparable<Student>{
    private String name;
    private int course;
    private int group;

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getName() {

        return name;
    }

    public int getCourse() {
        return course;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", course=" + course +
                ", group=" + group +
                '}';
    }

    public Student(String name, int course, int group) {
        this.name = name;
        this.course = course;
        this.group = group;
    }

    @Override
    public int compareTo(Student o) {
        if(course == o.course){
            if(group == o.group) return name.compareTo(o.name);
            return group - o.group;
        }
        return course - o.course;
    }
}
