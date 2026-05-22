package org.example.parsers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.builder.Director;
import org.example.builder.MissionBuilder;
import org.example.model.Mission;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XmlParser implements ParserI {
    XmlMapper xmlMapper = new XmlMapper();

    @Override
    public Mission parse(String filePath) {
        Mission mission;
        Director director = new Director();
        try {
            mission = xmlMapper.readValue(new File(filePath), Mission.class);
            return director.createMission(new MissionBuilder(), mission);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filePath);
            System.out.println("Проверьте путь: " + new File(filePath).getAbsolutePath());

        } catch (JsonParseException e) {
            System.out.println("Ошибка синтаксиса XML: " + e.getOriginalMessage());

        } catch (JsonMappingException e) {
            System.out.println("Ошибка структуры XML (не совпадает с классом Mission)");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
