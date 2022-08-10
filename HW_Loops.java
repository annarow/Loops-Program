
/**
 * HW_Loops Program to use DNA sequences and calculate relative information.
 *
 * Anna Waldron
 * 4/29/2020
 */
import java.util.Scanner;
public class HW_Loops
{
    //MAIN FUNCTION that asks users for 2 dna sequences and checks for valid
    //input. Calls multiple functions to run.
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a DNA Sequence.");
        String input = keyboard.next();
        boolean check = checkValidInput(input);
        System.out.println("Enter another DNA Sequence.");
        String input2 = keyboard.next();
        boolean check2 = checkValidInput(input2);
        if(check == true && check2 == true){
            basicStatistics(input, 1);
            basicStatistics(input2, 2);
            pairwise(input, input2);
        }
    }
    //function which returns truue or false if the input is a valid dna 
    //sequence.Prints out statements if invalid.
    public static boolean checkValidInput(String input)
    {
        boolean check = false;
        if(input.length() > 12){
            check = false;
            System.out.println("Too long of a sequence.");
            return check;
        }
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) != 'A' || input.charAt(i) != 'T'
            || input.charAt(i) != 'C' || input.charAt(i) != 'G'){
                check = true;
                
                return check;
            }
            System.out.println("Invalid DNA.");
        }  
        return check;
    }
    //Function which takes 2 dna sequences as arguments and prints out
    //the alignment with the highest similarity and the number of 
    //similarities.Uses a nested for loop to compare the 2 sequences
    //and increments the the similarity variable and the index variable.
    public static void pairwise(String one, String two)
    {
        String longer = "";
        String shorter = "";

        if(one.length() > two.length()) {
            longer = one;
            shorter = two;
        }else {
            longer = two;
            shorter = one;
        }

        int difference = longer.length() - shorter.length();

        int bestMatch = 0;
        int currentMatch = 0;
        int indexToUse = 0;

        for (int i = 0; i < difference; i++) {
            for (int k = 0; k < shorter.length(); k++) {
                if(shorter.charAt(k) == longer.charAt(k+i)) {
                    currentMatch++;
                }
            }
            if(currentMatch > bestMatch) {
                bestMatch = currentMatch;
                currentMatch = 0;
                indexToUse = i;
            }else {
                currentMatch = 0;
            }
        }
        System.out.println("Best alignment score: " + bestMatch);
        System.out.println("\t" + longer);
        System.out.print("\t");
        for(int l = 0; l < indexToUse; l++) {
            System.out.print(" ");
        }
        System.out.println(shorter);
    }
    //function which takes a dna sequence as a string and an integer for 
    //which sequence it is working on. Calls multiple function to compute
    //the basic statistics for the sequence and prints out the results.
    public static void basicStatistics(String dna, int number)
    {
        int count_c = determinC(dna);
        double ratio = determinCG(dna, count_c);
        String compliment = determinComp(dna);
        System.out.println("Sequence " + number + ": " + dna);
        System.out.println("\tC-count: " + count_c);
        System.out.println("\tCG-ratio: " + ratio);
        System.out.println("\tComplement: " + compliment);
    }
    //Integer function that takes a string of the dna sequence and calculates
    //the number of times C is in the sequence and returns it.
    public static int determinC(String dna)
    {
        int count_c = 0;
        for(int i = 0; i < dna.length(); i++){
            if(dna.charAt(i) == 'C'){
                count_c += 1;
            }
        }
        return count_c;
    }
    //Double function which takes dna string sequence and integer of the 
    //number of C's in the sequence. Function determines the number of G's
    //in the sequence and determines the ratio for G's and C's combined
    //compared to the total in the sequence and  returns that double value.
    public static double determinCG(String dna, int count_c)
    {
        double ratio_cg;
        double count_g = 0.0;
        for(int i = 0; i < dna.length(); i++){
            if(dna.charAt(i) == 'G'){
                count_g += 1.0;
            }
        }
        double total = count_g + count_c;
        ratio_cg = total / dna.length();
        String holder = String.format("%.3f", ratio_cg);
        ratio_cg = Double.parseDouble(holder);
        return ratio_cg;
    }
    //String function that determines the complementary sequence to the 
    //sequence passed in as a string. Uses a for loop to go through each
    //letter and adds to the new sequence it's pair. Returns that string.
    public static String determinComp(String dna)
    {
        String compliment = "";
        for(int i = 0; i < dna.length(); i++){
            if(dna.charAt(i) == 'A'){
                compliment += 'T';
            }
            else if(dna.charAt(i) == 'T'){
                compliment += 'A';
            }
            else if(dna.charAt(i) == 'C'){
                compliment += 'G';
            }
            else if(dna.charAt(i) == 'G'){
                compliment += 'C';
            }
        }
        return compliment;
    }
    
}
