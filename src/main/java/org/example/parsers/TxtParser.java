package org.example.parsers;

import org.example.builder.Director;
import org.example.builder.MissionBuilder;
import org.example.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TxtParser implements ParserI {

    @Override
    public Mission parse(String filePath) {
        Mission mission = new Mission();
        List<Sorcerer> sorcerers = new ArrayList<>();
        List<Technique> techniques = new ArrayList<>();
        Curse curse = new Curse();
        EnvironmentConditions environment = new EnvironmentConditions();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String currentSection = null;
            Map<String, String> currentBlock = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    if (currentSection != null && !currentBlock.isEmpty()) {
                        processBlock(currentSection, currentBlock, mission, sorcerers, techniques, curse, environment);
                        currentBlock.clear();
                        currentSection = null;
                    }
                    continue;
                }

                if (line.startsWith("[") && line.endsWith("]")) {
                    if (currentSection != null && !currentBlock.isEmpty()) {
                        processBlock(currentSection, currentBlock, mission, sorcerers, techniques, curse, environment);
                        currentBlock.clear();
                    }
                    currentSection = line.substring(1, line.length() - 1);
                    continue;
                }

                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    currentBlock.put(key, value);
                }
            }

            if (currentSection != null && !currentBlock.isEmpty()) {
                processBlock(currentSection, currentBlock, mission, sorcerers, techniques, curse, environment);
            }

            mission.setCurse(curse);
            mission.setSorcerers(sorcerers);
            mission.setTechniques(techniques);
            mission.setEnvironmentConditions(environment);

            Director director = new Director();
            return director.createMission(new MissionBuilder(), mission);

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return null;
        }
    }

    private void processBlock(String section, Map<String, String> block, Mission mission,
                              List<Sorcerer> sorcerers, List<Technique> techniques,
                              Curse curse, EnvironmentConditions environment) {
        switch (section) {
            case "MISSION":
                if (block.containsKey("missionId")) mission.setMissionId(block.get("missionId"));
                if (block.containsKey("date")) mission.setDate(block.get("date"));
                if (block.containsKey("location")) mission.setLocation(block.get("location"));
                if (block.containsKey("outcome")) mission.setOutcome(block.get("outcome"));
                if (block.containsKey("damageCost")) mission.setDamageCost(Integer.parseInt(block.get("damageCost")));
                break;

            case "CURSE":
                if (block.containsKey("name")) curse.setName(block.get("name"));
                if (block.containsKey("threatLevel")) curse.setThreatLevel(block.get("threatLevel"));
                break;

            case "SORCERER":
                Sorcerer sorcerer = new Sorcerer();
                if (block.containsKey("name")) sorcerer.setName(block.get("name"));
                if (block.containsKey("rank")) sorcerer.setRank(block.get("rank"));
                sorcerers.add(sorcerer);
                break;

            case "TECHNIQUE":
                Technique technique = new Technique();
                if (block.containsKey("name")) technique.setName(block.get("name"));
                if (block.containsKey("type")) technique.setType(block.get("type"));
                if (block.containsKey("owner")) technique.setOwner(block.get("owner"));
                if (block.containsKey("damage")) technique.setDamage(Integer.parseInt(block.get("damage")));
                techniques.add(technique);
                break;

            case "ENVIRONMENT":
                if (block.containsKey("weather")) environment.setWeather(block.get("weather"));
                if (block.containsKey("timeOfDay")) environment.setTimeOfDay(block.get("timeOfDay"));
                if (block.containsKey("visibility")) environment.setVisibility(block.get("visibility"));
                if (block.containsKey("cursedEnergyDensity")) {
                    environment.setCursedEnergyDensity(Integer.parseInt(block.get("cursedEnergyDensity")));
                }
                break;
        }
    }
}