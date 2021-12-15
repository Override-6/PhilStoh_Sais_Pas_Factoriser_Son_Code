package fr.override.tests;

import java.util.function.Function;

/*
 *
 *Ici tu mets les trucs compliqués
 */
public class ComplexOpCodeFactories {

    public static Function<ByteSequence, OpCode> lookupSwitchFactory() {
        return sequence -> {
            var start = sequence.currentPos();
            var opCode = sequence.retrieveByte();
            sequence.skipBytes(4 - (sequence.getIndex() % 4));
            var defaultOffset = sequence.retrieveInt();
            var numberOfPairs = sequence.retrieveInt();
            var matchValues = new int[opCode.numberOfPairs];
            var offsets = new int[opCode.numberOfPairs];
            for (int i = 0; i < opCode.numberOfPairs; i++) {
                matchValues[i] = sequence.retrieveInt();
                offsets[i] = sequence.retrieveInt();
            }

            var attributes = new OpCodeAttributes(); //bref tu fou s'qu'il faut ici le sang
            return new OpCode(OpCodeType.LOOKUP_SWITCH, start, attributes);
        };
    }

}
