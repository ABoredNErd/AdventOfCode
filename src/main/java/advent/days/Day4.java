package advent.days;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Day4 {

    public Day4(List<String> data){ 
        int total = 0;
        for(String row: data){
            String game_id = row.split(":")[0];
            List<String> winning_cards = Arrays.asList(row.split(":")[1].split(" \\| ")[0].split(" "));
            List<String> cards = Arrays.asList(row.split(":")[1].split(" \\| ")[1].split(" "));

            List<String> matchingCards = cards.stream().filter(st -> winning_cards.contains(st) && st != "").collect(Collectors.toList());

            int score = 0;
            for (String matchingCards2 : matchingCards) {
                if(score == 0)
                    score = 1;
                else
                    score *= 2;
            }
            total += score;
        }
        System.out.println("Total: " + total);
    }
}
