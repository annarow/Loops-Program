
/**
 *This program will read input data from a file describing
 *specific classes and students, and will calculate weighted averages
 *for each student.
 * 
 * Anna Waldron
 * 5/4/2020
 */
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class HW_Loops_Files
{
    //Main function that creates a Scanner object to read a file
    //and uses a try-catch to see if the file can be found and opened.
    //calls another method to then parse the file.
    public static final void main(String args[]){
        Scanner fileIn = null;

        try
        {
            fileIn = new Scanner(new FileInputStream("CourseData.txt"));
        }
        catch (FileNotFoundException e){
            System.out.println("File not found.");
            System.exit(0); 
        }
        readFile(fileIn);
    }
    //Void function that takes a Scanner object that is a text file
    //that parses through the file to then print out a summary of the
    //information. Creates vriables to hold weightss of scoring and
    //creates arrays to store information on each student.
    public static void readFile(Scanner fileIn)
    {
        double program_weight = fileIn.nextDouble();
        double midterm_weight = fileIn.nextDouble();
        double final_exam_weight = fileIn.nextDouble();
        fileIn.nextLine();
        String holder = "";
        String[] temp = new String[4]; //used to convert string into ints
        int[] data = new int[4];
        double average = 0.0;
        double total_av = 0.0;
        int num_students = 0;
        //Nested while loop goes through each line of the file to extract 
        //data for each student and and prints out the layout as it goes.
        while(fileIn.hasNextLine()){
            holder = fileIn.nextLine();
            System.out.println("Grade Data For Class " + holder + "\n"); 
            holder = fileIn.nextLine();
            System.out.println(" ID   Programs  Midterm  Final  " +
            "Weighted Average  Programs Grade");
            System.out.println(" --   --------  -------  -----  " +
            "----------------  --------------");
            while(!holder.equals("0")){
                temp = holder.split(" ");
                for(int i = 0; i < 4; i++){
                    data[i] = Integer.parseInt(temp[i]);
                    
                }
                System.out.print(data[0] + "     " + data[1] +
                "\t  " + data[2] + "\t  " + data[3] + "\t ");
                average = findWeightedAverage(program_weight,
                midterm_weight, final_exam_weight, data[1], data[2],
                data[3]);
                System.out.printf("    %.2f        ", average);
                //If/else used to determine pass fail depending on 
                //program score only.
                if(data[1] < 70){
                    System.out.println("Fail");
                }
                else{
                    System.out.println("Pass");
                }
                total_av += average;
                num_students++;
                holder = fileIn.nextLine();
            }
            total_av = total_av / num_students; //find average for class
            System.out.printf("Class Average: %.2f\n\n", total_av);
            total_av = 0;
            num_students = 0;
            
        }
    }
    //Double function which takes many parameters, first 3 being the 
    //percent weight for programs, midterm, and final for calucating the
    //weighted average for the student. Takes in 3 parameters for the 
    //student on individual scores for programs, midterm and final.
    public static double findWeightedAverage(double program_w, double 
    midterm_w, double final_exam_w, int program_s, int midterm_s,
    int final_s)
    {
        double returnDouble = 0;
        returnDouble += program_w * program_s;
        returnDouble += midterm_w * midterm_s;
        returnDouble += final_exam_w * final_s;
        return returnDouble;
    }
}
