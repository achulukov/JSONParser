import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String readString(String pathFile) {

        StringBuilder strJSON = new StringBuilder();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile))) {
            String s = "";

            while ((s = bufferedReader.readLine()) != null) {
                strJSON.append(s);

            }


        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        return strJSON.toString();
    }

    private static List<Employee> jsonToList(String json) {
        JSONParser jsonParser = new JSONParser();
        List<Employee> employeeList = new ArrayList<>();



        try {
            Object obj = jsonParser.parse(json);
            JSONArray jsonArray = (JSONArray) obj;

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            for (Object jsonObject : jsonArray) {
                JSONObject jsonObjectCast = (JSONObject) jsonObject;
                employeeList.add(gson.fromJson(jsonObjectCast.toString(), Employee.class));
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return employeeList;
    }



    public static void main(String[] args) {
        List<Employee> employees = jsonToList(readString("new_data.json"));
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

    }
}
