package com.library.common;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The type Json helper.
 */
public class JsonHelper {

    private static void recursionGetPairs(Map<String, String> pairMap, ObjectMapper mapper, String json) {
        try {
            JsonNode rootNode = mapper.readTree(json);
            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
            while (fieldsIterator.hasNext()) {
                Map.Entry<String, JsonNode> field = fieldsIterator.next();
                String value = StringHelper.unquote(String.valueOf(field.getValue()));
                pairMap.put(field.getKey(), value);
                if (!value.equals("")) {
                    recursionGetPairs(pairMap, mapper, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> parseJsonToPairs(String json) {
        Map<String, String> pairMap = new HashMap<String, String>();
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        if (!json.equals("")) {
            recursionGetPairs(pairMap, mapper, json);
        }
        return pairMap;
    }

}
