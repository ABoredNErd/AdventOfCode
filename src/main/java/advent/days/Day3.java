package advent.days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public boolean validate_numb(NumbValue nmb, NumbValue v){
        return v.finish >= nmb.start && v.finish <= nmb.finish || v.start >= nmb.start && v.start <= nmb.finish || nmb.finish >= v.start && nmb.finish <= v.finish || nmb.start >= v.start && nmb.start <= v.finish;
    }

    public Day3(List<String> data){
        List<NumbValue> numbers = new ArrayList<>();
        List<NumbValue> gears = new ArrayList<>();


        NumbValue value = new NumbValue();
        for(int row = 0; row < data.size(); row ++){
            String row_data = data.get(row);
            for(int col = 0; col < data.get(row).length(); col ++){
                value.row = row;
                char col_data = row_data.charAt(col);
                if(Character.isDigit(col_data)){
                    value.value += col_data;
                    if(value.start == Integer.MIN_VALUE)
                        value.start = col;
                } else if(value.value != "") {
                    value.finish = col - 1;
                    numbers.add(value);
                    value = new NumbValue();
                }
                if(row_data.charAt(col) == '*'){
                    NumbValue g = new NumbValue();
                    g.start = col - 1;
                    g.finish = col + 1;
                    g.row = row;
                    g.value = "*";
                    gears.add(g);
                }
            }
            if(value.value != ""){
                value.finish = row_data.length() - 1;
                numbers.add(value);
                value = new NumbValue();
            }
        }

        
        numbers.stream().filter(nmb -> nmb.value.equals("1")).forEach(v -> {
            System.out.println("Value: " + v.value);
            System.out.println("Start: " + v.start);
            System.out.println("Finish: " + v.finish);
            System.out.println("Row: " + v.row);
        });
        // TODO change this to be hashmap and no filters
        int max = 0;
        List<String> gearBox = new ArrayList<>();
        for(NumbValue v: gears){

            System.out.println("Value: " + v.value);
            System.out.println("Start: " + v.start);
            System.out.println("Finish: " + v.finish);
            System.out.println("Row: " + v.row);

            List<NumbValue> row_data = numbers.stream().filter(nmb -> nmb.row == v.row)
                .filter(nmb -> this.validate_numb(nmb, v)).collect(Collectors.toList());
            List<NumbValue> row_above_data = null;
            List<NumbValue> row_under_data = null;
            if(v.row > 0)
                row_above_data = numbers.stream().filter(nmb -> nmb.row == v.row - 1)
                    .filter(nmb -> this.validate_numb(nmb, v)).collect(Collectors.toList());
            if(v.row <= data.size() - 1) {
                row_under_data = numbers.stream().filter(nmb -> nmb.row == v.row + 1).filter(nmb -> this.validate_numb(nmb, v)).
                    collect(Collectors.toList());
            }


            if(row_above_data != null)
                row_data.addAll(row_above_data);
            if(row_under_data != null)
                row_data.addAll(row_under_data);

            System.out.println();
            System.out.println(row_data.size());
            row_data.forEach(g -> {
                System.out.println("Value: " + g.value);
                System.out.println("Start: " + g.start);
                System.out.println("Finish: " + g.finish);
                System.out.println("Row: " + g.row);
            });

            if(row_data.size() == 2){
                max += Integer.valueOf(row_data.get(0).value) * Integer.valueOf(row_data.get(1).value);
            }
        }
        System.out.println("Total: " + max);
    }
}
