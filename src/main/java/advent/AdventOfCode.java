package advent;

import advent.days.Day3;
import advent.fileReader.FileReader;

public class AdventOfCode {

    public static void main(String[] args){
        FileReader reader = new FileReader("inputFiles/Day3.txt");
            
        new Day3(reader.fileContents());
    }
}
