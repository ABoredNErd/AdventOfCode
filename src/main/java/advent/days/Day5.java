package advent.days;

import java.util.ArrayList;
import java.util.List;

public class Day5 {

    class Range{

        public int start;
        public int end;

        public Range(int start, int end){
            this.start = start;
            this.end = end;
        }

        public boolean inRange(int value){
            return value >= this.start && value <= this.end;
        }
    }

    class Cat{
        public Range dest;
        public Range source;

        public Cat(Range source, Range dest){
            this.source = source;
            this.dest = dest;
        };

        public Cat(){
            // default constr
        };
    }

    public Day5(List<String> data){
        List<Cat> cats = new ArrayList<>();
        for(String row: data){
            if(Character.isDigit(row.charAt(0))){
            }
            if(row.equals(""))
                cats.add(new Cat());
        }
    }
}
