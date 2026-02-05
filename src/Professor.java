import java.util.*;

public class Professor implements Comparable<Professor> {

    private int id;
    private String name;
    private Date dateOfHire;
    private Float seniorityLevel;
    private Set<String> setOfDisciplines;
    private List<Course> listOfAffectedCourses;

    public Professor(int id, String name, Float seniorityLevel, Date dateOfHire, String setOfDisciplines) {
        this.id = id;
        this.name = name;
        this.dateOfHire = dateOfHire;
        this.seniorityLevel = seniorityLevel;
        this.setOfDisciplines = new HashSet<>(Arrays.asList(setOfDisciplines.split(",")));
        this.listOfAffectedCourses = new ArrayList<>();;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfHire() {
        return dateOfHire;
    }

    public float getSeniorityLevel() {
        return seniorityLevel;
    }

    public Set<String> getSetOfDisciplines() {
        return setOfDisciplines;
    }

    public List<Course> getListOfAffectedCourses() {
        return listOfAffectedCourses;
    }

    public void setSeniorityLevel(float seniorityLevel) {
        if (seniorityLevel < 0 || seniorityLevel > 60) {
            throw new IllegalArgumentException("Seniority cannot be negative and between 0 and 60");
        }
        this.seniorityLevel = seniorityLevel;
    }

    public void setListOfAffectedCourses(List<Course> listOfAffectedCourses) {
        this.listOfAffectedCourses = new ArrayList<>(listOfAffectedCourses);;
    }
    public boolean hasDiscipline(String discipline) {
        return setOfDisciplines.contains(discipline.trim().replace("-", ""));
    }

    @Override
    public String toString() {
        return "Professor{id=" + id + ", name=" + name + ", Date of hire=" + dateOfHire + ", seniority=" + seniorityLevel +
                ", disciplines=" + setOfDisciplines + ", courses=" +
                listOfAffectedCourses + "}";
    }

    @Override
    public int compareTo(Professor o) {
        if (this.seniorityLevel != o.seniorityLevel)
            return this.seniorityLevel.compareTo(o.seniorityLevel);

        return this.dateOfHire.compareTo(o.dateOfHire);
    }
}


