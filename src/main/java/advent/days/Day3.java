package advent.days;

import java.util.ArrayList;
import java.util.List;

public class Day3 {

    private boolean validate_char(Character chr){
        return chr != '.' && !Character.isDigit(chr);
    }

    private void attempt1(List<String> data) {
        System.out.println("Started!");
        List<Integer> enginePart = new ArrayList<>();
        String newnumber = "";
        boolean valid = false;
        for(int i = 0; i < data.size(); i ++) {
            String row = data.get(i);
            for(int j = 0; j < row.length(); j++){
                if(Character.isDigit(row.charAt(j))){
                    newnumber += row.charAt(j);
                    // only check if not already confirmed as valid
                    if(!valid){
                        if(i != 0){
                            // check the line above
                            Character chr = data.get(i - 1).charAt(j);
                            valid = valid || validate_char(chr);
                        }
                        if(i != data.size() - 1){
                            // check the line below
                            Character chr = data.get(i + 1).charAt(j);
                            valid = valid || validate_char(chr);
                        }
                    }
                }
                if(row.charAt(j) == '.'){
                    if(valid && !newnumber.equals("")){
                        enginePart.add(Integer.valueOf(newnumber));
                    }
                    newnumber = "";
                    valid = false;
                }
            }
        }
        enginePart.forEach(System.out::println);

    }

    private class NumbValue{

        public int row;
        public int start;
        public int finish;
        public String value;


        public NumbValue(){
            this.start = Integer.MIN_VALUE;
            this.finish = -1;
            this.row = Integer.MIN_VALUE;
            this.value = "";
        }
    }

    public Day3(List<String> data){
        List<NumbValue> numbers = new ArrayList<>();
        NumbValue value = new NumbValue();
        for(int row = 0; row < data.size(); row ++){
            String row_data = data.get(row);
            for(int col = 0; col < data.get(row).length(); col ++){
                value.row = row;
                char col_data = row_data.charAt(col);
                if(Character.isDigit(col_data)){
                    value.value += col_data;
                    if(value.start == Integer.MIN_VALUE)
                        value.start = col - 1;
                } else if(value.value != "") {
                    value.finish = col;
                    numbers.add(value);
                    value = new NumbValue();
                }
            }
            if(value.value != ""){
                value.finish = row_data.length() - 1;
                numbers.add(value);
                value = new NumbValue();
            }
        }

        List<String> engineParts = new ArrayList<>();
        for(NumbValue v: numbers){

            // System.out.println("Value: " + v.value);
            // System.out.println("Start: " + v.start);
            // System.out.println("Finish: " + v.finish);
            // System.out.println("Row: " + v.row);

            String row_data = data.get(v.row);
            String row_above_data = null;
            String row_under_data = null;
            if(v.row > 0)
                row_above_data = data.get(v.row - 1);
            if(v.row < data.size() - 1) {
                row_under_data = data.get(v.row + 1);
           //     System.out.println(row_under_data);
            }
            boolean valid = false;
            for(int col = v.start; col <= v.finish; col ++){
                if(col >= 0 && !valid){
                    valid = valid || validate_char(row_data.charAt(col));
                    if(row_above_data != null)
                        valid = valid || validate_char(row_above_data.charAt(col));
                    if(row_under_data != null)
                        valid = valid || validate_char(row_under_data.charAt(col));
                }
            }
            if(valid)
                engineParts.add(v.value);
        }

        int max = 0;
        for(String enginePart: engineParts) {
            max += Integer.valueOf(enginePart);
        }
        System.out.println("Total: " + max);
    }
}
