import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream json = Main.class.getClassLoader().getResourceAsStream("credentials.json");
        List<Map<String, String>> data = mapper.readValue(json, new TypeReference<List<Map<String, String>>>(){});
        for (Map<String, String> pair : data) {
            System.out.printf("username: %s, password: %s", pair.get("username"),  pair.get("password"));
            System.out.println();
        }

//        ExcelReader objExcelFile = new ExcelReader();
//
//        //Prepare the path of excel file
//
//        String filePath = System.getProperty("user.dir") +"\\src\\main\\java\\";
//
//        //Call read file method of the class to read data
//
//        objExcelFile.readExcel(filePath, "invalidCredentials.xlsx","username");
    }
}
