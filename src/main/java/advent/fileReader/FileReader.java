package advent.fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader{

    private String filePath;
    public FileReader(String filePath){
        this.filePath = filePath;
    }

    public List<String> fileContents(){
        List<String> data = new ArrayList<>();
        File myFile = new File(filePath);
        try (Scanner myReader = new Scanner(myFile)) {
            while(myReader.hasNextLine()) {
                data.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }
}
