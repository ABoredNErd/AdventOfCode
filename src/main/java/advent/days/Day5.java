package advent.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

        public boolean inRangeLower(SeedRange range){
            return range.lower >= this.lower && range.lower <= this.higher;
        }
        
        public boolean inRangeHigher(SeedRange range){
            return range.higher >= this.lower && range.higher <= this.higher;
        }
        
        public Long convert(Long value){
            return this.dest + (value - this.lower);
        }

        public void convert(SeedRange value){
            value.lower = this.convert(value.lower);
            value.higher = this.convert(value.higher);
        }
    }

    class SeedRange{
        public Long lower;
        public Long higher;

        public SeedRange(Long lower, Long higher){
            this.lower = lower;
            this.higher = higher;
        }
        
        public Optional<SeedRange> splitRanges(Long split){
            if(split >= this.higher || split <= this.lower)
                return Optional.empty();
            SeedRange newRange = new SeedRange(this.lower, split);
            this.lower = split + 1;
            return Optional.of(newRange);
        }

        public Optional<SeedRange> splitRangesLower(Long split){
            if(split > this.higher || split < this.lower)
                return Optional.empty();
            SeedRange newRange = new SeedRange(this.lower, split - 1);
            this.lower = split;
            return Optional.of(newRange);
        }
    }

    public Day5(List<String> data){ 
        List<Long> seeds = Arrays.asList(data.get(0).split(":")[1].split(" ")).stream().filter(seed -> seed != "").map(Long::valueOf).collect(Collectors.toList());
        
        List<SeedRange> seedRanges = new ArrayList<>();
        for(int i = 0; i < seeds.size(); i += 2){
            seedRanges.add(new SeedRange(seeds.get(i), seeds.get(i) + seeds.get(i + 1)));
        }

        List<List<Cat>> cats = new ArrayList<>();
        for(int i = 1; i < data.size(); i++){
            if(data.get(i).equals(""))
                continue;
            if(!Character.isDigit(data.get(i).charAt(0))){
                cats.add(new ArrayList<>());
                continue;
            }
            List<Long> numbs = Arrays.asList(data.get(i).split(" ")).stream().map(Long::valueOf).collect(Collectors.toList());
            cats.get(cats.size() - 1).add(new Cat(numbs.get(1), numbs.get(1) + (numbs.get(2) - 1), numbs.get(0)));
        }


        for(List<Cat> cat_i: cats){
            for(int i = 0; i < seedRanges.size(); i ++){
                for(Cat c: cat_i){
                    if(c.inRangeLower(seedRanges.get(i))){
                        Optional<SeedRange> split = seedRanges.get(i).splitRanges(c.higher);
                        if(split.isPresent()){
                            c.convert(split.get());
                            seedRanges.add(i, split.get());
                        } else {
                            c.convert(seedRanges.get(i));
                        }
                        break;
                    } else if(c.inRangeHigher(seedRanges.get(i))){
                        Optional<SeedRange> split = seedRanges.get(i).splitRangesLower(c.lower);
                        if(split.isPresent())
                            seedRanges.add(split.get());
                        c.convert(seedRanges.get(i));
                        break;
                    }
                }
            }
        }

        seedRanges.sort((rl, rr) -> Long.compare(rl.lower, rr.lower));
        System.out.println("Answer: " + seedRanges.get(0).lower);
    }
}
