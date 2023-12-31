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
        FileReader reader = new FileReader("inputFiles/Day7.txt");
            
            
        long startTime = System.currentTimeMillis();
        new Day7(reader.fileContents());
        long endTime = System.currentTimeMillis();
        System.out.println("Run time in Millis: " + (endTime - startTime));
    }
}
