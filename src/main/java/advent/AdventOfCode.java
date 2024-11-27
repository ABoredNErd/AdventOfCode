package advent;

import advent.fileReader.FileReader;
import advent.year2.*;

public class AdventOfCode {

    public static void main(String[] args){
        FileReader reader = new FileReader("inputFiles/Day7.txt");
            
        long startTime = System.currentTimeMillis();
        new day1(reader.fileContents());
        long endTime = System.currentTimeMillis();
        System.out.println("Run time in Millis: " + (endTime - startTime));
    }
}
