package fr.override.tests;

import java.util.function.Function;

public enum OpCodeType {

    //range tes opcodes par ordre croissant
    PUSH_NULL("aconst_null", 0x00, noParameterOpCode("PUSH_NULL")),
    LOAD_REF_FROM_ARRAY("aconst_null", 0x01, noParameterOpCode("LOAD_REF_FROM_ARRAY")),
    PUSH_BYTE("dsdqs", 0x02, simpleCreator("LOAD_REF_FROM_ARRAY", "B", "index")),
    GOTO_WIDE("dsqd", 0x03, simpleCreator("GOTO_WIDE", "BI", "offset", "address")),
    INTEGER_TO_FLOAT("sdsq", 0x04, noParameterOpCode("INTEGER_TO_FLOAT")),
    CREATE_NEW_MULTIDIMENSIONAL_ARRAY("sss", 0x05, simpleCreator("CREATE_NEW_MULTIDIMENSIONAL_ARRAY", "SB", "offset", "address")),
    LOOKUP_SWITCH("lookup_souitshes", 0x06, ComplexOpCodeFactories.lookupSwitchFactory());

    private final String name;
    private final byte opCode;
    private final Function<ByteSequence, OpCode> creator;

    OpCodeType(String name, int opCode, Function<ByteSequence, OpCode> creator) {
        this.name = name;
        this.opCode = (byte) opCode;
        this.creator = creator;
    }

    public byte getOpCode() {
        return opCode;
    }

    public String getName() {
        return name;
    }

    public Function<ByteSequence, OpCode> getFactory() {
        return creator;
    }

    private static Function<ByteSequence, OpCode> noParameterOpCode(String that) {
        return simpleCreator(that, "");
    }

    private static Function<ByteSequence, OpCode> simpleCreator(String that, String pattern, String... attributeTypes) {
        var builder = new OpCodeAttributesSimpleBuilder(attributeTypes, pattern);
        return seq -> {
            OpCodeType thatTpe = OpCodeType.valueOf(that); //petit bypass du self reference des familles
            return new OpCode(thatTpe, seq.currentPos(), builder.build(seq));
        };
    }
}
