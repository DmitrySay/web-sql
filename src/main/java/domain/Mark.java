package domain;


public class Mark {

    private int id;
    private int studentId;
    private int mark;



    public void setId(int id) {
        this.id = id;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getMark() {
        return mark;
    }

    @Override
    public String toString()
    {
        return "( id " +this.id+ ", studentId " +this.studentId+ ", mark " +this.mark +" )";
    }

}
