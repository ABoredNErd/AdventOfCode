package advent.days;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Day4 {

    public Day4(List<String> data){ 
        int[] scratch_cards = new int[data.size()];
        for(int i = 0; i < data.size(); i ++){
            scratch_cards[i] = 1;
        }

        int row_count = 0;
        for(String row: data){
            String game_id = row.split(":")[0];
            List<String> winning_cards = Arrays.asList(row.split(":")[1].split(" \\| ")[0].split(" "));
            List<String> cards = Arrays.asList(row.split(":")[1].split(" \\| ")[1].split(" "));

            List<String> matchingCards = cards.stream().filter(st -> winning_cards.contains(st) && st != "").collect(Collectors.toList());

            for(int i = row_count + 1; i < row_count + 1 + matchingCards.size(); i++){
                scratch_cards[i] += 1 * scratch_cards[row_count];
            }
            row_count ++;
        }

        int total = 0;
        for(int i = 0; i < data.size(); i ++){
            System.out.print(scratch_cards[i] + " ,");
            total += scratch_cards[i];
        }
        System.out.println();
        System.out.println("Total: " + total);
    }
}
