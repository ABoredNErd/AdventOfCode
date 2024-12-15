package advent;

import advent.fileReader.FileReader;
import advent.days.year2.*;;

public class AdventOfCode {

    public static void main(String[] args){
        FileReader reader = new FileReader("inputFiles/year2/Day15.txt");
            
        long startTime = System.currentTimeMillis();
        new Day15(reader.fileContents());
        long endTime = System.currentTimeMillis();
        System.out.println("Run time in Millis: " + (endTime - startTime));
    }
}
