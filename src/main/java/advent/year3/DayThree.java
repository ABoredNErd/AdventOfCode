package advent.year3;

import java.util.Arrays;
import java.util.List;

/**
 * DayOne
 */
public class DayThree {


    public DayThree(List<String> list){
        long result = 0;
        for (String str : list) {
            System.out.println("");
            List<String> array = Arrays.asList(str.split(""));
            String result_str = "";
            for (int i = 0; i < array.size(); i++) {
                String current_str = array.get(i);
                int current_int = Integer.valueOf(current_str);
                boolean biggest = true;
                for(int j = i + 1; j < array.size(); j++){
                    if(current_int < Integer.valueOf(array.get(j)) && j <= array.size() - (12 - result_str.length())){
                        biggest = false;
                    }
                }
                if(biggest){
                    result_str += current_str;
                }
                if(result_str.length() == 12){
                    break;
                }
            }
            System.out.println("Result: " + result_str);
            result += Long.valueOf(result_str);
        }
        System.out.println(result);
    }
}
