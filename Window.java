import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class Window extends JFrame implements ActionListener{
    JButton op1 = new JButton("1. Enter information for a new student.");
    JButton op2 = new JButton("2. Enter information for a new grad Student.");
    JButton op3 = new JButton("3. Show all student information on separate lines.");
    JButton op4 = new JButton("4. Print the average and total number of students.");
    JButton op5 = new JButton("5. Search by program for specific student information.");
    JButton op6 = new JButton("6. Load student information from an input file.");
    JButton op7 = new JButton("7. Save all student information to an output file.");
    JButton op8 = new JButton("8. End program.");
    JTextArea output = new JTextArea(830,500);

    HashMap <String, Student> map = new HashMap<String, Student>();
    ArrayList<Student> info = new ArrayList<Student>();

    public Window(){
        super();
        prepareGUI();
    }

	private void prepareGUI(){
		setSize(1460,1000);
		setTitle("Student Class Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
		op1.setSize(360,50);
		op1.setFocusable(false);
		op1.setLocation(4,0);
		op1.addActionListener(new option1());
		add(op1);

		op2.setSize(360,50);
		op2.setFocusable(false);
		op2.setLocation(370,0);
		op2.addActionListener(new option2());
		add(op2);

		op3.setSize(360,50);
		op3.setFocusable(false);
		op3.setLocation(740,0);
		op3.addActionListener(new option3());
		add(op3);

		op4.setSize(360,50);
		op4.setFocusable(false);
		op4.setLocation(1100,0);
		op4.addActionListener(new option4());
		add(op4);

		op5.setSize(360,50);
		op5.setFocusable(false);
		op5.setLocation(4,60);
		op5.addActionListener(new option5());
		add(op5);

		op6.setSize(360,50);
		op6.setFocusable(false);
		op6.setLocation(370,60);
		op6.addActionListener(new option6());
		add(op6);

		op7.setSize(360,50);
		op7.setFocusable(false);
		op7.setLocation(740,60);
		op7.addActionListener(new option7());
		add(op7);

		op8.setSize(360,50);
		op8.setFocusable(false);
		op8.setLocation(1100,60);
		op8.addActionListener(new option8());
        add(op8);

		output.setSize(830,750);
		output.setLocation(300,180);
		output.setEnabled(true);
        add(output);
    }

    private class option1 implements ActionListener{
        public void actionPerformed (ActionEvent e){
            String sentence = "", grade = "", lastName = "", program = "", year = "", first = "", result = "";
            double aveGrade = 0;
            int length = 0;
            boolean valid;

            output.setText("");

            do{
                valid = true;
                
                do{
                    sentence = JOptionPane.showInputDialog("Enter student program and year: ");
                }while (sentence.isEmpty()); //keeps reprompting user until they enter a valid input

                String [] words = sentence.split("\\s+"); //splits the input by any whitespace to differentiate the subject from the year of study

                if(words.length == 1){ //error checks if only 1 parameter is entered
                    output.append("Error, please enter both fields. Try again.\n");
                    return;
                }
                
                grade = JOptionPane.showInputDialog("Enter average grade, or leave blank: ");

                length = grade.length();

                if (length == 0){ //if the input is null/empty, then the grade will be by default 0.0%
                    aveGrade = 0;
                }
                else{
                    aveGrade = Double.parseDouble(grade); //otherwise, the input is parsed into a double
                }

                do{
                    lastName = JOptionPane.showInputDialog("Enter last name of student: ");
                }while (lastName.isEmpty());

                try{
                    Student std = new Student(words[0], words[1], aveGrade, lastName); //storing the attributes inside Student class 
                    info.add(std); //adding inputs into the arrayList

                    program = words[0].toLowerCase();
                    year = words[1].toLowerCase();
                    lastName = lastName.toLowerCase();

                    first = program.concat(year);
                    result = first.concat(lastName);

                    map.put(result, std);

                    output.append("\n");
                }
                catch (Exception x){
                    output.append(x.getMessage()); //print exception message to user
                    valid = false;
                }
            }while(!valid);
        }
    }

    private class option2 implements ActionListener{
        public void actionPerformed (ActionEvent e){
            String sentence = "", med = "", other = "", grade = "", lastName = "", program = "", year = "", first = "", result = "";;
            double aveGrade = 0;
            int length = 0;
            boolean flag = true, valid;

            output.setText("");

            do{
                valid = true;
                do{
                    sentence = JOptionPane.showInputDialog("Enter student program and year: ");
                }while (sentence.isEmpty());

                String [] words = sentence.split("\\s+");

                if(words.length == 1){
                    output.append("Error, please enter both fields. Try again.\n");
                    return;
                }

                grade = JOptionPane.showInputDialog("Enter average grade, or leave blank: ");

                length = grade.length();

                if (length == 0){
                    aveGrade = 0;
                }
                else{
                    aveGrade = Double.parseDouble(grade);
                }

                do{
                    lastName = JOptionPane.showInputDialog("Enter last name of student: ");
                }while (lastName.isEmpty());

                do{
                    other = JOptionPane.showInputDialog("Enter supervisor name and undergraduate school: ");
                }while (other.isEmpty());

                String [] extra = other.split("\\s+");

                if(extra.length == 1){ //error checks if only 1 parameter is entered
                    output.append("Error, please enter both fields. Try again.\n");
                    return;
                }

                med = JOptionPane.showInputDialog("Enter 1 (PhD) or 0 (Masters) for graduate certification: ");

                if (med.equals("1")){
                    flag = true;
                }
                else if (med.equals("0")){
                    flag = false;
                }
                else{ //error checks if input was not 1 or 0
                    output.append("Error, invalid entry. Please try again.\n");
                    return;
                }
        
                try{
                    GraduateStudent std2 = new GraduateStudent(words[0], words[1], aveGrade, lastName, extra[0], flag, extra[1]);
                    info.add(std2);

                    program = words[0].toLowerCase();
                    year = words[1].toLowerCase();
                    lastName = lastName.toLowerCase();

                    first = program.concat(year);
                    result = first.concat(lastName);

                    map.put(result, std2);

                    output.append("\n");
                }
                catch (Exception x){
                    output.append(x.getMessage());
                    valid = false;
                }
            }while(!valid);
        }
    }

    private class option3 implements ActionListener{
        public void actionPerformed (ActionEvent e){
            String display = info.toString();
        
            display = display.substring(1, display.length() - 1); //to remove the '[' and ']' added by default by the toString function
            display = display.replace(",", ""); //to remove the ',' added by default by the toString function

            output.setText(display);
        }
    }

    private class option4 implements ActionListener{
        public void actionPerformed (ActionEvent e){
            int total = 0;
            double sum = 0;
            double average = 0;
            String format = "###.#"; //formatting to 1 decimal place as per lab2 pdf
            DecimalFormat decimalFormat = new DecimalFormat(format);

            for (Student obj: info){
            total++; //to count total number of students in arrayList 
            sum += obj.getNameGrade(); //to calculate the sum of all grades in arrayList
            average = sum/total; //to find the grand average of the average grades in arrayList
            }

            output.setText("Total number of students: " + Integer.toString(total) + "\n");
            output.append("Average grade of all students: " + decimalFormat.format(average) + "%");
        }
    }

    private class option5 implements ActionListener{
        public void actionPerformed (ActionEvent e){
            String search = "", first = "", result = "";

            do{
                search = JOptionPane.showInputDialog("Enter the program, year, and lastname (separated by spaces): ");
            }while(search.isEmpty());

            String [] words = search.split("\\s+");

            output.setText("");

            if((words.length == 1) || (words.length == 2)){ //error checks if only 1 parameter is entered
                output.append("Error, please enter all fields. Try again.\n");
                return;
            }

            first = words[0].concat(words[1]);
            result = first.concat(words[2]);
            result = result.toLowerCase();

            if(map.containsKey(result)){
                output.append("" + map.get(result));
            }
            else{
                output.append("Sorry, this student is not in database.\n");
            }

            output.append("\n");
        }
    }

    private class option6 implements ActionListener{
        public void actionPerformed (ActionEvent e){
            String filename = "", contents = "", program = "", year = "", lastName = "", first = "", result = "";
            double aveGrade = 0;
            boolean flag = true;
            
            filename = JOptionPane.showInputDialog("Please enter the name of the input file: ");

            output.setText("");

            try{
                File file = new File(filename);

                if (file.exists() && !file.isDirectory()){ //file of choice needs to exist and be in the working directory
                    Scanner scan = new Scanner(file); //scans all of the files contents

                    while (scan.hasNextLine()){ //scans each line in the file until it reaches the end
                        contents = scan.nextLine();
                        String [] words = contents.split("\\s+");

                        program = words[0];
                        year = words[1];
                        aveGrade = Double.parseDouble(words[2]);

                        if (words.length == 4){
                            lastName = words[3];
                            
                            first = program.concat(year);
                            result = first.concat(lastName);
                            result = result.toLowerCase();

                            if(map.containsKey(result)){
                                output.append("Error: this student entry already exists.\n");
                            }
                            else{
                                Student std = new Student(words[0], words[1], aveGrade, lastName); //storing the attributes inside Student class 
                                info.add(std); //adding info to arraylist

                                map.put(result, std);
                            }
                        }
                        else if (words.length == 7){
                            lastName = words[6];

                            first = program.concat(year);
                            result = first.concat(lastName);
                            result = result.toLowerCase();

                            if(map.containsKey(result)){
                                output.append("Error: this graduate student entry already exists.\n");
                            }
                            else{
                                if (words[4].equals("1")){ //translating string values to boolean representations
                                    flag = true;
                                }
                                else if (words[4].equals("0")){
                                    flag = false;
                                }

                                GraduateStudent std2 = new GraduateStudent(words[0], words[1], aveGrade, lastName, words[3], flag, words[5]); //storing the attributes inside graduateStudent subclass
                                info.add(std2); //adding info to arrayList

                                map.put(result, std2);
                            }
                        }
                    }

                    scan.close();
                }
                else{
                    output.append("Error, the file does not exist or isn't in directory. Please try again.\n");
                    return;
                }
            }
            catch (Exception x){
                output.setText("Error, could not load: " + x.getMessage());
            }
        }
    }

    private class option7 implements ActionListener{
        public void actionPerformed (ActionEvent e){
            String filename = "", program = "", year = "", supervisor = "", school = "", lastName = "", display = "";
            double grade = 0;
            int status = 0;

            output.setText("");

            filename = JOptionPane.showInputDialog("Please enter the name of the output file: ");

            try{
                //Writing to file of choice
                File output = new File(filename);
                FileWriter fw = new FileWriter(output);
                PrintWriter pw = new PrintWriter(fw);

                for (Student obj: info){
                    if (obj.getNameSubject() != null){
                        if (obj instanceof GraduateStudent){
                            program = obj.getNameSubject();
                            year = obj.getNameYear();
                            lastName = obj.getNameLast();
                            grade = obj.getNameGrade();
                            supervisor = ((GraduateStudent) obj).getNameSupervisor();
                            school = ((GraduateStudent) obj).getNameUnder();

                            if(((GraduateStudent) obj).getNamePhD() == true){
                                status = 1;
                            }
                            else if (((GraduateStudent) obj).getNamePhD() == false){
                                status = 0;
                            }

                            display = program + " " + year + " " + grade + " " + supervisor + " " + status + " " + school + " " + lastName;
                        }
                        else if (obj instanceof Student){
                            program = obj.getNameSubject();
                            year = obj.getNameYear();
                            lastName = obj.getNameLast();
                            grade = obj.getNameGrade();

                            display = program + " " + year + " " + grade + " " + lastName;
                        }

                        pw.println(display); //printing to the file of choice all student/gradStudent entries
                        
                    }
                }

                fw.close(); //closing Filewriter and Printwriter to avoid leaks
                pw.close();
            } 
            catch (Exception x){
                output.setText("Error, could not write: " + x.getMessage());
            }
        }
    }

    private class option8 implements ActionListener{
      public void actionPerformed (ActionEvent e){
        System.exit(0);
      }
    }
    
    public static void main(String[] args) {
        Window layout = new Window();
        layout.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}