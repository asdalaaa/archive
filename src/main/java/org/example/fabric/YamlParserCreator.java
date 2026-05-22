package org.example.fabric;

import org.example.parsers.ParserI;
import org.example.parsers.YamlParser;

public class YamlParserCreator extends ParserCreator {
    @Override
    ParserI createParser() {
        return new YamlParser();
    }
}
