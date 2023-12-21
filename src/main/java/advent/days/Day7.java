
package advent.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;


public class Day7 {

    class Hand{
        public int power;
        public String hand;
        public int bet;
        public int rank;

        public Hand(){
        }
    }

    List<Predicate<String>> createPredicates(){
        List<Predicate<String>> predicates = new ArrayList<>();

        // check five of a kind
        predicates.add(str -> 5 == str.chars().filter(chr -> chr == str.charAt(0)).count());
        // check four of a kind
        predicates.add(str -> 4 == str.chars().filter(chr -> chr == str.charAt(0)).count());

        predicates.add(str ->{
            Set<Long> counts = new HashSet<>();

            for(int i = 0; i < str.length(); i++){
                final int counter = i;
                counts.add(str.chars().filter(chr -> chr == str.charAt(counter)).count());
            }

            return counts.contains(2l) && counts.contains(3l);
        });

        return predicates;
    }

    public Day7(List<String> data){
        List<Predicate<String>> predicates = createPredicates();

        
        for(String row: data){
            System.out.println(row);
        }
    }
}
