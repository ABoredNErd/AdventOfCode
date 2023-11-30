package advent;

import advent.days.Day1;
import advent.fileReader.FileReader;

public class AdventOfCode {

    public static void main(String[] args){
        FileReader reader = new FileReader("inputFiles/Day1.txt");

        new Day1(reader.fileContents());
    }
}
