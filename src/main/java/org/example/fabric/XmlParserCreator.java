package org.example.fabric;

import org.example.parsers.ParserI;
import org.example.parsers.XmlParser;

public class XmlParserCreator extends ParserCreator{
    @Override
    ParserI createParser() {
        return new XmlParser();
    }
}
