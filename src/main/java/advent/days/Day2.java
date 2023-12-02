package advent.days;

import java.util.HashMap;
import java.util.List;

public class Day2 {


    public Day2(List<String> data){
        HashMap<String, Integer> cubes = new HashMap<>();
        cubes.put("red", 12);
        cubes.put("green", 13);
        cubes.put("blue", 14);

        int total = 0;
        for(String line: data){

            String[] split = line.split(":");
            int gameID = Integer.valueOf(split[0].split(" ")[1]);
 
            String[] pieces = split[1].replace(';' , ',').split(",");
            boolean valid = true;
            for(String cube: pieces){
                String[] cubeInfo = cube.split(" ");
                if(cubes.containsKey(cubeInfo[2])){
                    if(Integer.valueOf(cubeInfo[1]) > cubes.get(cubeInfo[2])){
                        valid = false;
                    }
                }

            }
            if(valid){
                total += Integer.valueOf(gameID);
            }
        }
        System.out.println(total);

    }
    
}
