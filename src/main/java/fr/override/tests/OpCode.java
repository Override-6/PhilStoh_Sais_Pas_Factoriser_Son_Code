package fr.override.tests;

public class OpCode {

    private final OpCodeAttributes attributes;
    private final OpCodeType type;
    private final int start;

    public OpCode(OpCodeType type, int start, OpCodeAttributes attributes) {
        this.attributes = attributes;
        this.type = type;
        this.start = start;
    }

    public static OpCode from(ByteSequence sequence) {
        byte opcode = sequence.peekByte();
        OpCodeType type = OpCodeType.values()[opcode];
        return type.getFactory().apply(sequence);
    }

    public OpCodeAttributes getAttributes() {
        return attributes;
    }

    private String toStringBase() {
        return "{" +
                "\"start_pc\":" + Integer.toUnsignedString(start) + "," +
                "\"opcode\":" + Integer.toHexString(type.getOpCode()) + "," +
                "\"type\":" + type.getName();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(toStringBase());
        if (!attributes.isEmpty())
            sb.append(',');
        attributes.writeAttributes(sb);
        sb.append('}');
        return sb.toString();
    }
}
