class GraduateStudent extends Student{ //declaring subclass
    private String supervisor;
    private boolean isPHD;
    private String undergraduateSchool;

    GraduateStudent(String subject, String year, double grade, String lastName, String supervisor, boolean isPHD, String undergraduateSchool) throws Exception{
        super(subject, year, grade, lastName); //copies in variables from the superclass 'Student'
        this.supervisor = supervisor;
        this.isPHD = isPHD;
        this.undergraduateSchool = undergraduateSchool;
    }

    //getters
    public String getNameSupervisor() {
        return supervisor;
    }

    public boolean getNamePhD() {
        return isPHD;
    }

    public String getNameUnder() {
        return undergraduateSchool;
    }

    //setters
    public void setNameSupervisor(String supervisor){
        this.supervisor = supervisor;
    }

    public void setNamePhD(boolean isPHD){
        this.isPHD = isPHD;
    }

    public void setNameUnder(String undergraduateSchool){
        this.undergraduateSchool = undergraduateSchool;
    }

    @Override //overrides the superclass 'Student' toString method
    public String toString(){
        String ss = super.toString(); //so we print all fields including those from the superclass 'Student'
        String status = "";

        if (isPHD == true){ //since we need to print PhD or Masters specifically to standard output
            status = "PhD";
        }
        else{
            status = "Masters";
        }

        return ss + "Supervisor: " + supervisor + "\nPhD/Masters: " + status + "\nSchool: " + undergraduateSchool + "\n"; //printing everything on a newline as per pdf instructions
    }
}
