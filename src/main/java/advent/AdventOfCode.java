package advent;

import advent.fileReader.FileReader;
import advent.year2.*;

public class AdventOfCode {

    public static void main(String[] args){
        FileReader reader = new FileReader("inputFiles/year2_day1.txt");
            
        long startTime = System.currentTimeMillis();
        new Day1(reader.fileContents());
        long endTime = System.currentTimeMillis();
        System.out.println("Run time in Millis: " + (endTime - startTime));
    }
}
