package advent.year3;

import java.util.List;

/**
 * DayOne
 */
public class DayOne {

    public DayOne(List<String> list){
        List<String> direction = list.stream().map(str -> {
            return str.substring(0, 1);
        }).toList();
        
        List<Integer> amount = list.stream().map(str -> { 
            return Integer.valueOf(str.substring(1));
        }).toList();
        
        int count = 0;

        int start_value = 50;
        for (int i = 0; i < direction.size(); i++) {
            for(int movement = 0; movement < amount.get(i); movement ++){
                if(direction.get(i).equals("L")){
                    start_value =  (start_value - 1) + 100;
                }
                if(direction.get(i).equals("R")){
                    start_value += 1;
                }
                start_value = Math.floorMod(start_value, 100);
                if(start_value ==  0){
                    count ++;
                }
            }
        }
        System.out.println();
        System.out.println(count);
    }
}
