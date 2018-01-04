package com.jraw.android.jonsapp.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by JonGaming on 03/01/2018.
 * This is because I ran into problems with gson/retrofit handling doubles from the server.
 * Basically it tells gson how to handle nulls
 */

public class DoubleTypeAdapter extends TypeAdapter<Double> {
    @Override
    public void write(JsonWriter aWriter, Double value) throws IOException {
        if (value == null) {
            aWriter.nullValue();
            return;
        }
        aWriter.value(value);
    }

    @Override
    public Double read(JsonReader aReader) throws IOException {
        if(aReader.peek() == JsonToken.NULL){
            aReader.nextNull();
            return null;
        }
        String stringValue = aReader.nextString();
        try{
            Double value = Double.valueOf(stringValue);
            return value;
        } catch(NumberFormatException e){
            return null;
        }
    }
}
