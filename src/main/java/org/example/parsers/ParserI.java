package org.example.parsers;

import org.example.model.Mission;

public interface ParserI {
    Mission parse(String filePath);
}

