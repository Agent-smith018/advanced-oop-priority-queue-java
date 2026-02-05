import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Department {

    private HashMap<String,Course>courseHashMap;
    protected List<Professor>listOfProfessor;

    public Department(List<Professor>professor){
        this.listOfProfessor=new ArrayList<>(professor);
        this.courseHashMap=new HashMap<>();
    }

    public void addCourse(Course course){
        courseHashMap.put(course.getId(),course);
    }
    public Course getCourse(String id){
        return courseHashMap.get(id);
    }

    public HashMap<String, Course> getCourseMap() {
        return courseHashMap;
    }
    public PriorityQueue<Professor> getProfPriorityQueue() {
        PriorityQueue<Professor> pq = new PriorityQueue<>();
        for (Professor p : listOfProfessor) {
            pq.enqueue(p);
        }
        return pq;
    }
}

