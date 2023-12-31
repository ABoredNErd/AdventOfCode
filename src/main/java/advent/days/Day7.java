
package advent.days;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class Day7 {

    public List<Predicate<List<Long>>> predicates; 
    class Hand{
        public int power;
        public String hand;
        public int bet;
        public int rank;

        public Hand(){
        }

        public void print(){
            System.out.println("Hand: " + this.hand);
            System.out.println("Bet: " + this.bet);
            System.out.println("Power: " + this.power);
            System.out.println("Rank: " + this.rank);
        }
    }

    List<Long> find_counts(String data){
        List<Long> counts = new ArrayList<>();
        List<Character> done = new ArrayList<>();
        for(int i = 0; i < data.length(); i ++){
            char checker = data.charAt(i);
            if(!done.contains(checker)){
                long count = data.chars().filter(chr -> chr == checker).count();
                if(count > 1){
                    done.add(checker);
                    counts.add(count);
                }
            }
        }
        return counts;
    }

    List<Predicate<List<Long>>> createPredicates(){
        List<Predicate<List<Long>>> predicates = new ArrayList<>();

        // One Pair = 1 
        predicates.add((List<Long> counts) -> {
            return counts.size() == 1 && counts.contains(2l);
        });
        // Two Pair = 2 
        predicates.add((List<Long> counts) -> {
            return counts.size() == 2 && counts.contains(2l);
        });
        // three of a kind = 3
        predicates.add((List<Long> counts) -> {
            return counts.size() == 1 && counts.contains(3l);
        });
        // full house = 4
        predicates.add((List<Long> counts) -> {
            return counts.size() == 2 && counts.contains(3l);
        });
        // four of a kind = 5
        predicates.add((List<Long> counts) -> {
            return counts.contains(4l);
        });
        // five of a kind = 6
        predicates.add((List<Long> counts) -> {
            return counts.contains(5l);
        });

        return predicates;
    }

    int check_predicates(List<Long> values){
        for(int i = 0; i < predicates.size(); i ++){
            if(predicates.get(i).test(values)){
                return i + 1;
            }
        }
        return 0;
    }

    public char[] card_values = new char[] {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};

    public int card_value(char card){
        for(int i = 0; i < card_values.length; i ++){
            if(card == card_values[i]){
                return i;
            }
        }
        return -1;
    }

    public int check_hands(Hand h1, Hand h2){
        for(int i = 0; i < card_values.length; i ++){
            int result = card_value(h1.hand.charAt(i)) - card_value(h2.hand.charAt(i));
            if(result != 0)
                return result;
        }
        return 0;
    }

    public Day7(List<String> data){
        predicates = createPredicates();

        List<Hand> hands = new ArrayList<>();
        for(String row: data){
            String[] values = row.split(" ");
            Hand new_hand = new Hand();
            new_hand.hand = values[0];
            new_hand.bet = Integer.valueOf(values[1]);
            new_hand.power = this.check_predicates(find_counts(new_hand.hand));
            hands.add(new_hand);
        }

        hands.sort((Hand h1, Hand h2) -> {
            if(h1.power == h2.power){
                return this.check_hands(h1, h2);
            } 
            return h1.power - h2.power;
        });

        hands.forEach(Hand::print);

        int result =  0;
        for(int i = 0; i < hands.size(); i ++){
            result += hands.get(i).bet * (i + 1);
        }
        System.out.println("Result: " + result);
    }
}
