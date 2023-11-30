package advent.days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Example2022 {
    
    public Example2022(List<String> file_contents){
        List<Integer> max_values = new ArrayList<>();
        int new_total = 0;
        for(String line: file_contents){
            if(line.length() > 0) {
                new_total += Integer.valueOf(line);
            } else {
                max_values.add(new_total);
                new_total = 0;
            }
        }

        int max = 0;

        for(Integer value: max_values){ 
            if(value > max){
                max = value;
            }
        }

        System.out.println("Values: " + max);

        Collections.sort(max_values, Collections.reverseOrder());
        max = max_values.get(0) + max_values.get(1) + max_values.get(2);
        System.out.println("Answer: "  + max);

    }
}
