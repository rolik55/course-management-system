package com.coursemngsys.coursemanagementsystem.serializers;

import com.coursemngsys.coursemanagementsystem.Model.Course;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class CourseSerializer implements JsonSerializer<Course> {
    @Override
    public JsonElement serialize(Course course, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject courseJson = new JsonObject();
        courseJson.addProperty("id", course.getId());
        courseJson.addProperty("title", course.getTitle());
        courseJson.addProperty("description", course.getDescription());
//        courseJson.addProperty("dateCreated", course.getDateCreated().toString());
//        courseJson.addProperty("startDate", course.getStartDate().toString());
//        courseJson.addProperty("endDate", course.getEndDate());
//        courseJson.addProperty("folders", course.getFolders().toString());
//        courseJson.addProperty("moderators", course.getModerators().toString());
//        courseJson.addProperty("students", course.getStudents().toString());
        return courseJson;
    }
}
