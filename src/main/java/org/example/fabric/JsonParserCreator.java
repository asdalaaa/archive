package org.example.fabric;

import org.example.parsers.JsonParser;
import org.example.parsers.ParserI;

public class JsonParserCreator extends ParserCreator {
    @Override
    ParserI createParser() {
        return new JsonParser();
    }
}
