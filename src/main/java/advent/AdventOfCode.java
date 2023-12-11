package advent;

import advent.days.*;
import advent.fileReader.FileReader;

public class AdventOfCode {

    public static void main(String[] args){
        FileReader reader = new FileReader("inputFiles/Day5.txt");
            
        long startTime = System.currentTimeMillis();
        new Day5(reader.fileContents());
        long endTime = System.currentTimeMillis();
        System.out.println("Run time in Millis: " + (endTime - startTime));
    }
}
