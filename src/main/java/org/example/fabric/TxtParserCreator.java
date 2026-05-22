package org.example.fabric;

import org.example.parsers.ParserI;
import org.example.parsers.TxtParser;

public class TxtParserCreator extends ParserCreator{
    @Override
    ParserI createParser() {
        return new TxtParser();
    }
}
