package com.micasita.order_management_app.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Response {

    public String Message(String response)
    {
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        JsonObject message = jsonObject.get("Message").getAsJsonObject();
        return message.get("Body").getAsString();
    }
}
