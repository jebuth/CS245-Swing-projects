import java.util.Scanner;
/**
 *
 * @author Justin
 */

public class CommandLine {
    public static void main(String[] args){
        
        double f;
        double c;
        
        System.out.println("Enter a degree in Fahrenheit.");
        Scanner input = new Scanner(System.in);
        f = input.nextFloat();
        
        c = f-32;
        c = c *(5/9.);
        
        System.out.println(c);
        
    }
}