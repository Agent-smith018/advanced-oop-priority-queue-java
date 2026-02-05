import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    Department dept = new Department(new ArrayList<>());
    loadCourses("./TextFile/courses_f22.txt", dept);
    PriorityQueue<Professor> profQueue = loadProfs("./TextFile/profs.txt", dept);

        runMatching(profQueue, dept);
    }

    private static void loadCourses(String filename, Department dept) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 6) {
                    Course course = new Course(parts[0].trim(), parts[1].trim(), parts[2].trim(),
                            Integer.parseInt(parts[3].trim()), Integer.parseInt(parts[5].trim()));
                    dept.addCourse(course);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PriorityQueue<Professor> loadProfs(String filename,Department department){
        PriorityQueue<Professor> pq = new PriorityQueue<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = br.readLine())!=null){
                String [] parts = line.split(":");
                if (parts.length>=5){
                    Date hireDate = null;
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy");
                        hireDate = sdf.parse(parts[3].trim());
                    } catch (ParseException pe) {
                        hireDate = new Date();
                    }

                    Professor prof = new Professor(Integer.parseInt(parts[0].trim()),
                            parts[1].trim(), Float.parseFloat(parts[2].trim()), hireDate, parts[4].trim());

                    department.listOfProfessor.add(prof);
                    pq.enqueue(prof);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return pq;
    }
    private static void runMatching(PriorityQueue<Professor> profQueue, Department dept) {
        HashMap<String, Course> courseMap = dept.getCourseMap();

        while (!profQueue.isEmpty()) {
            Professor prof = profQueue.dequeue();
            // use the shared selection file in TextFile folder
            String reqFile = "./TextFile/"+prof.getId()+"_selection.txt"; // professeour id
            double remainingHours = readRequestedHours(reqFile);

            processProfRequests(prof, reqFile, courseMap, remainingHours);
        }
        printAffectations(dept.listOfProfessor);
    }
    private static double readRequestedHours(String reqFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(reqFile))) {
            return Double.parseDouble(br.readLine().trim());
        } catch (Exception e) {
            return 0;
        }
    }
    private static void processProfRequests(Professor prof, String reqFile,
                                            HashMap<String, Course> courseMap, double remainingHours) {
        try (BufferedReader br = new BufferedReader(new FileReader(reqFile))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null && remainingHours > 0) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String courseId = parts[0].trim();
                    int reqGroups = Integer.parseInt(parts[1].trim());

                    Course course = courseMap.get(courseId);
                    if (course != null && course.getNumOfGroups() > 0 &&
                            prof.hasDiscipline(course.getDiscipline())) {

                        int availableGroups = course.getNumOfGroups();
                        int weeklyHours = course.getWeeklyHours();
                        int assignGroups = Math.min(reqGroups, availableGroups);
                        assignGroups = Math.min(assignGroups, (int) (remainingHours / weeklyHours));

                        if (assignGroups > 0) {
                            Course assigned = new Course(course);  // Copy
                            assigned.setNumOfGroups(assignGroups);
                            prof.getListOfAffectedCourses().add(assigned);

                            course.setNumOfGroups(availableGroups - assignGroups);
                            remainingHours -= assignGroups * weeklyHours;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printAffectations(List<Professor> profs) {
        for (Professor p : profs) {
            if (!p.getListOfAffectedCourses().isEmpty()) {
                System.out.println(p + " assigned: ");
                for (Course c : p.getListOfAffectedCourses()) {
                    System.out.println("  " + c);
                }
            }
        }
    }
}