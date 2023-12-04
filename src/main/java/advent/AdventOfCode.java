package advent;

import advent.days.Day4;
import advent.fileReader.FileReader;

public class AdventOfCode {

    public static void main(String[] args){
        FileReader reader = new FileReader("inputFiles/Day4.txt");
            
        new Day4(reader.fileContents());
    }
}
