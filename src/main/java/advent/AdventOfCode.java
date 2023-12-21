package advent;

import advent.days.*;
import advent.fileReader.FileReader;

public class AdventOfCode {

    class Hand{
        public int power;
        public String hand;
        public int bet;
        public int rank;

        public Hand(){
        }
    }

    public static void main(String[] args){
        FileReader reader = new FileReader("inputFiles/Day6.txt");
            
        new Day6(reader.fileContents());
    }
}
