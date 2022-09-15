package com.coursemngsys.coursemanagementsystem.serializers;

import com.coursemngsys.coursemanagementsystem.Model.Folder;
import com.coursemngsys.coursemanagementsystem.Model.Moderator;
import com.google.gson.*;
import org.hibernate.secure.internal.JaccSecurityListener;

import java.lang.reflect.Type;
import java.util.List;

public class ModeratorsSerializer implements JsonSerializer<List<Moderator>> {
    @Override
    public JsonElement serialize(List<Moderator> moderators, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Moderator.class, new ModeratorsSerializer());
        Gson parser = gsonBuilder.create();
        for(Moderator m: moderators){
            jsonArray.add(parser.toJson(m));
        }
        return jsonArray;
    }
}
