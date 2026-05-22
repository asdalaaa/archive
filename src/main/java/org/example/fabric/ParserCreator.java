package org.example.fabric;

import org.example.model.Mission;
import org.example.parsers.ParserI;

abstract public class ParserCreator {
    abstract ParserI createParser();

    public Mission parse(String filePath) {
        ParserI parser = createParser();
        return parser.parse(filePath);
    };
}
