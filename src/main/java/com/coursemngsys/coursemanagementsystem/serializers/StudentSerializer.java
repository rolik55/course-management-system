package com.coursemngsys.coursemanagementsystem.serializers;

import com.coursemngsys.coursemanagementsystem.Model.Student;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class StudentSerializer implements JsonSerializer<Student> {
    @Override
    public JsonElement serialize(Student student, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject studentJson = new JsonObject();
        studentJson.addProperty("id", student.getId());
        studentJson.addProperty("login", student.getLogin());
        studentJson.addProperty("password", student.getPassword());
        //studentJson.addProperty("dateCreated", student.getDateCreated().toString());
        //studentJson.addProperty("dateModified", student.getDateModified().toString());
        studentJson.addProperty("name", student.getName());
        studentJson.addProperty("surname", student.getSurname());
        studentJson.addProperty("email", student.getEmail());
        studentJson.addProperty("studentNumber", student.getStudentNumber());
//        studentJson.addProperty("myFiles", student.getMyFiles());
//        studentJson.addProperty("enrolledCourses", student.getEnrolledCourses());
        return studentJson;
    }
}
