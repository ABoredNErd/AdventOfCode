package advent;

import advent.days.Day2;
import advent.fileReader.FileReader;

public class AdventOfCode {

    public static void main(String[] args){
        FileReader reader = new FileReader("inputFiles/Day2.txt");
            
        new Day2(reader.fileContents());
    }
}