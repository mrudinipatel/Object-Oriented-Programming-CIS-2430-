public class Student{
    protected String subject;
    protected String year;
    protected double grade;
    protected String lastName;

    Student(String subject, String year, double grade, String lastName) throws Exception{
        this.subject = subject;
        this.year = year;

        if((grade <= 100) && (grade >=  0)){
            this.grade = grade;
        }
        else{
            throw new Exception("Error: average grade must be between 0 and 100.\n");
        }

        this.lastName = lastName;
    }

    //Getters
    public String getNameSubject() {
        return subject;
    }

    public String getNameYear() {
        return year;
    }

    public double getNameGrade() {
        return grade;
    }

    public String getNameLast() {
        return lastName;
    }

    //Setters
    public void setNameSubject(String subject){
        this.subject = subject;
    }

    public void setNameYear(String year){
        this.year = year;
    }

    public void setNameGrade(double grade){
        this.grade = grade;
    }

    public void setNameLast(String lastName){
        this.lastName = lastName;
    }

    public String toString(){ //toString method for superclass which returns/prints all 4 fields
        return "\nSubject: " + subject + "\nYear: " + year + "\nAverage Grade: " + grade + "\nLastName: " + lastName + "\n";
    }
}

