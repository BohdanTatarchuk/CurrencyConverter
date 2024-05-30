package com.example.converter.util;

import com.example.converter.model.ConvertsInto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Utils {

    public List<ConvertsInto> getConvertsIntoList(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        List<ConvertsInto> result = new ArrayList<>();

        StringBuilder dataString = new StringBuilder();
        while (scanner.hasNext()){
            dataString.append(scanner.nextLine());
        }

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        JsonObject jsonObject = gson.fromJson(String.valueOf(dataString), JsonObject.class);

        JsonObject data = jsonObject.getAsJsonObject("data");

        for (Map.Entry<String, JsonElement> entry : data.entrySet()) {
            result.add(new ConvertsInto(
                            "USD",
                            entry.getKey(),
                            entry.getValue().getAsDouble()
                    )
            );
        }
        return result;
    }

}
