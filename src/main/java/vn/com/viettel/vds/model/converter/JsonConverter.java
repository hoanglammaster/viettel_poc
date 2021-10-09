package vn.com.viettel.vds.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter<E> {

    private E e;

    public String objectToJson(E e){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(e);
        } catch (JsonProcessingException ex) {
           throw new IllegalArgumentException(String.format("Class not support convert to json: %s", e.getClass().getName()));
        }
    }

    public E jsonToObject(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return (E) objectMapper.readValue(json,e.getClass());
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException(String.format("Class not support convert to json: %s", e.getClass().getName()));
        }
    }
}
