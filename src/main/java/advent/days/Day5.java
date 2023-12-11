package advent.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

    class Cat{

        public Long lower;
        public Long higher;

        public Long dest;

        public Cat(Long lower, Long higher, Long dest){
            this.lower = lower;
            this.higher = higher;
            this.dest = dest;
        }

        public boolean inRange(Long value){
            return value <= higher && value >= lower;
        }
        
        public Long convert(Long value){
            return this.dest + (value - this.lower);
        }
    }

    public Day5(List<String> data){ 
        List<Long> seeds = Arrays.asList(data.get(0).split(":")[1].split(" ")).stream().filter(seed -> seed != "").map(Long::valueOf).collect(Collectors.toList());

        List<List<Cat>> cats = new ArrayList<>();
        for(int i = 1; i < data.size(); i++){
            if(data.get(i).equals(""))
                continue;
            if(!Character.isDigit(data.get(i).charAt(0))){
                cats.add(new ArrayList<>());
                continue;
            }
            List<Long> numbs = Arrays.asList(data.get(i).split(" ")).stream().map(inpt -> Long.valueOf(inpt)).collect(Collectors.toList());

            cats.get(cats.size() - 1).add(new Cat(numbs.get(1), numbs.get(1) + (numbs.get(2) - 1), numbs.get(0)));
        }


        for(List<Cat> cat_i: cats){
            for(int i = 0; i < seeds.size(); i ++){
                for(Cat c: cat_i){
                    if(c.inRange(seeds.get(i))){
                        seeds.set(i, c.convert(seeds.get(i)));
                        break;
                    }
                }
            }
        }

        Collections.sort(seeds);
        System.out.println("Answer: " + seeds.get(0));
    }
}
