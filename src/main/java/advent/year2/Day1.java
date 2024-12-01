package advent.year2;

import java.util.*;


/**
 * Day1
 */
public class Day1 {

    
    public Day1(List<String> rows){
        List<Integer> first = new ArrayList<Integer>();
        List<Integer> second = new ArrayList<Integer>();
        for(String row: rows){
            String[] outcome = row.split("   ");
            first.add(Integer.parseInt(outcome[0]));
            second.add(Integer.parseInt(outcome[1]));
        }

        Collections.sort(first);
        Collections.sort(second);

        int diff = 0;
        for(int i = 0; i < first.size(); i ++){
            int count = Collections.frequency(second, first.get(i));

            diff += first.get(i) * count;
        }
        System.out.println(diff);
        
    }
}
