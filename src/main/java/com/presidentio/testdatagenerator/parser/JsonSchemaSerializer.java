package com.presidentio.testdatagenerator.parser;

import com.presidentio.testdatagenerator.model.Schema;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Vitaliy on 23.01.2015.
 */
public class JsonSchemaSerializer implements SchemaSerializer {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Schema deserialize(InputStream inputStream) throws IOException {
        return objectMapper.readValue(inputStream, Schema.class);
    }

    @Override
    public Schema deserialize(String string) throws IOException {
        return objectMapper.readValue(string, Schema.class);
    }

    @Override
    public void serialize(Schema schema, OutputStream outputStream) throws IOException {
        objectMapper.writeValue(outputStream, schema);
    }

    @Override
    public String serialize(Schema schema) throws IOException {
        return objectMapper.writeValueAsString(schema);
    }

}
