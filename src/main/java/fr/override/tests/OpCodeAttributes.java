package fr.override.tests;

import java.util.HashMap;
import java.util.Map;

public class OpCodeAttributes {

    private final HashMap<String, String> attributes;

    public OpCodeAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    public boolean isEmpty() {
        return attributes.isEmpty();
    }

    public void writeAttributes(StringBuilder sb) {
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String name = entry.getKey();
            String value = entry.getKey();
            sb.append('"').append(name).append("\":")
                    .append(value).append(',');
        }
    }

    @Override
    public String toString() {
        return "OpCodeAttributes{" +
                "attributes=" + attributes +
                '}';
    }
}
