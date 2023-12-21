package advent.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6 {

    class Race{

        public long time;

        public long high_score;
        
        public Race(long time, long high_score){
            this.time = time;
            this.high_score = high_score;
        }

        public void print(){
            System.out.println("Time: " + this.time);
            System.out.println("High Score: " + this.high_score);
            System.out.println();
        }
    }

    private int distance_per_second = 1;

    public Day6(List<String> data){
        List<Race> races = new ArrayList<>(); 
        List<List<String>> result = data.stream().map(row -> Arrays.asList(row.split(":")[1].split(" ")).stream().filter(o_row -> !o_row.equals("")).collect(Collectors.toList())).collect(Collectors.toList());
       
        String time = "";
        String high_score = "";
        for(int i = 0; i < result.get(0).size(); i ++){
            time += result.get(0).get(i);
            high_score += result.get(1).get(i);
        }

        races.add(new Race(Long.valueOf(time), Long.valueOf(high_score)));

        System.out.println("Races: " + races.size());
        List<Long> winning_runs = new ArrayList<>();
        
        for(Race race: races) {
            Long winnings = 0l;
            for(int i = 1; i <= race.time; i ++){
                if(race.high_score < (distance_per_second * i) * (race.time - i))
                    winnings ++;
            }
            winning_runs.add(winnings);
        }

        int r = 1;
        for(Long winning: winning_runs){
            r *= winning;
        }
        System.out.println(r);
    }
}
