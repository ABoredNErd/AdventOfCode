package advent.days;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Day2 {


    public Day2(List<String> data){

        int total = 0;
        for(String line: data){
            HashMap<String, Integer> cubes = new HashMap<>();
            cubes.put("red", 0);
            cubes.put("green", 0);
            cubes.put("blue", 0);

            String[] split = line.split(":");
 
            String[] pieces = split[1].replace(';' , ',').split(",");
            for(String cube: pieces){
                String[] cubeInfo = cube.split(" ");
                if(cubes.containsKey(cubeInfo[2])){
                    if(Integer.valueOf(cubeInfo[1]) > cubes.get(cubeInfo[2])){
                        cubes.put(cubeInfo[2], Integer.valueOf(cubeInfo[1]));
                    }
                }
            }
            total += Integer.valueOf(cubes.get("red")) * Integer.valueOf(cubes.get("green")) * Integer.valueOf(cubes.get("blue")); 
        }
        System.out.println(total);

    }
    
}
