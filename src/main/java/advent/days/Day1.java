package advent.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class Day1 {

    private List<String> numbers = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

    private HashMap<String, Character> numbers_to_chars;

    public Day1(List<String> data){ 
        numbers_to_chars = new HashMap<>();
        numbers_to_chars.put("one", '1');
        numbers_to_chars.put("two", '2');
        numbers_to_chars.put("three", '3');
        numbers_to_chars.put("four", '4');
        numbers_to_chars.put("five", '5');
        numbers_to_chars.put("six", '6');
        numbers_to_chars.put("seven", '7');
        numbers_to_chars.put("eight", '8');
        numbers_to_chars.put("nine", '9');


        List<List<Pair<Character, Integer>>> values = new ArrayList<>();
        for(String value: data){
            value = value.toLowerCase();
            values.add(new ArrayList<>());

            for(Entry<String, Character> number: numbers_to_chars.entrySet()){
                if(value.contains(number.getKey())){
                    int position = value.length();
                    while(position != -1){
                        Integer index = value.indexOf(number.getKey(), position);
                        if(index != -1){
                            values.get(values.size() - 1).add(new Pair(number.getValue(), index));
                        }
                        position -= 1;
                    }
                }
            }

            for(int i = 0; i < value.length(); i ++) {
                if(Character.isDigit(value.charAt(i))){
                    values.get(values.size() - 1).add(new Pair(value.charAt(i), i));
                }
            }
        }
        values.forEach(row -> row.sort((p1, p2) -> {
                return p1.second - p2.second;
            }));

        // values.forEach(p -> {
        //     p.forEach(p1 -> {
        //         System.out.println(p1.second);
        //     });
        //     System.out.println();
        // });

        int total = 0;
        for(List<Pair<Character, Integer>> row: values){
            if(row.size() == 1){
                String result = String.valueOf(row.get(0).first) + String.valueOf(row.get(0).first);
                total += Integer.valueOf(result);
            } else {
                String result = String.valueOf(row.get(0).first) + String.valueOf(row.get(row.size() - 1).first);
                total += Integer.valueOf(result);
            }
        }
        System.out.println("Result: " + total);
    }
}
