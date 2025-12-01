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
            System.out.println();
            System.out.println("Start Value: " + start_value);
            boolean start_zero = start_value == 0;
            System.out.println("start_zero: " + start_zero);
            if(direction.get(i).equals("L")){
                start_value -= amount.get(i);
            }
            if(direction.get(i).equals("R")){
                start_value += amount.get(i);
            }

            System.out.println(start_value);
            System.out.println("Movement: " + amount.get(i));
            int total = 0; 
            if(start_value < 0){
                total = (start_value / -100);
                if(!start_zero){
                    total += 1;
                }
            }
            System.out.println("Total backwards: " + total);
            count += total;
            total = 0;


            // How many times it hits 0
            if(start_value != 100){
                total += (int) Math.floor(start_value / 100);
                count += Math.abs(total);
            }
            System.out.println("Total forward: " + total);
            


            start_value = Math.floorMod(start_value, 100);
            if(start_value ==  0){
                count ++;
            }
        }
        System.out.println();
        System.out.println(count);
    }
}
