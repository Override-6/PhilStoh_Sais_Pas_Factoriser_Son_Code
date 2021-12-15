package fr.override.tests;

import java.util.HashMap;

public class OpCodeAttributesSimpleBuilder {

    private final String[] attributeTypes;
    private final String pattern;

    public OpCodeAttributesSimpleBuilder(String[] attributeTypes, String pattern) {
        if (pattern.length() != attributeTypes.length)
            throw new IllegalArgumentException("XXX");
        this.attributeTypes = attributeTypes;
        this.pattern = pattern;
    }

    public OpCodeAttributes build(ByteSequence sequence) {
        HashMap<String, String> map = new HashMap<>();
        char[] patternBytes = pattern.toCharArray();
        for (int i = 0; i < attributeTypes.length; i++) {
            char c = patternBytes[i];
            String name = attributeTypes[i];
            String result = switch (c) {
                case 'I' -> Integer.toUnsignedString(sequence.retrieveInt());
                case 'B' -> String.valueOf(Byte.toUnsignedInt(sequence.retrieveByte()));
                case 'L' -> Long.toUnsignedString(sequence.retrieveLong());
                case 'S' -> Long.toUnsignedString(sequence.retrieveShort());
                default -> throw new UnsupportedOperationException("Received unexpected opcode parameter type : " + c);
            };
            map.put(name, result);
        }
        return new OpCodeAttributes(map);
    }

}
