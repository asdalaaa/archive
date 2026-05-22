package org.example.fabric;

import org.example.parsers.BinaryParser;
import org.example.parsers.ParserI;

public class BinaryParserCreator extends ParserCreator {
    @Override
    ParserI createParser() {
        return new BinaryParser();
    }
}