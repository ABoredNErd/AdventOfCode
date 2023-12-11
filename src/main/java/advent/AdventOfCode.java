package advent;

import advent.days.*;
import advent.fileReader.FileReader;

public class AdventOfCode {

    public static void main(String[] args){
        FileReader reader = new FileReader("inputFiles/Day5.txt");
            
        new Day5(reader.fileContents());
    }
}
