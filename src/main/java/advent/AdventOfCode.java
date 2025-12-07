package advent;

import advent.fileReader.FileReader;
import advent.year3.*;

public class AdventOfCode {

    public static void main(String[] args){
        FileReader reader = new FileReader("inputFiles/year3/day-seven-example.txt");
            
        long startTime = System.currentTimeMillis();
        new DaySeven(reader.fileContents());
        long endTime = System.currentTimeMillis();
        System.out.println("Run time in Millis: " + (endTime - startTime));
    }
}
