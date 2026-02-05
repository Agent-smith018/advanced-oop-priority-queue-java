public class Course {

    private String id;
    private String title;
    private String discipline;
    private int numOfHours;
    private int numOfGroups;


    public Course(String id,String title,String discipline,int numOfHours,int numOfGroups){
        this.id=id;
        this.title=title;
        this.discipline=discipline;
        this.numOfHours=numOfHours;
        this.numOfGroups=numOfGroups;

    }

    public Course(Course course){
        this.id=new String(course.id);
        this.title=new String(course.title);
        this.discipline=new String(course.discipline);
        this.numOfHours= course.numOfHours;
        this.numOfGroups=course.numOfGroups;
    }



    public String getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }


    public String getDiscipline(){
        return discipline;
    }

    public int getNumOfHours(){
        return numOfHours;
    }
    public  int getNumOfGroups(){
        return numOfGroups;
    }

    public void setNumOfGroups(int numOfGroups){
        this.numOfGroups=numOfGroups;
    }

    public int getWeeklyHours() {
        return switch (numOfHours) {
            case 45 -> 3;
            case 60 -> 4;
            case 75 -> 5;
            case 90 -> 6;
            default -> throw new IllegalArgumentException("Invalid hours: " + numOfHours);
        };
    }

    @Override
    public String toString(){
        return "Course Id:"+ id +" Title:"+title+" discipline:"+discipline+"language:"+" Number of Hours:"+numOfHours+
                " Number of Groups"+numOfGroups+" .!" ;
    }


}
