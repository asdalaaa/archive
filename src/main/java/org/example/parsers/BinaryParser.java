package org.example.parsers;

import org.example.builder.Director;
import org.example.builder.MissionBuilder;
import org.example.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BinaryParser implements ParserI {

    @Override
    public Mission parse(String filePath) {
        Mission mission = new Mission();
        List<Sorcerer> sorcerers = new ArrayList<>();
        List<Technique> techniques = new ArrayList<>();
        List<OperationTimeline> operationTimeline = new ArrayList<>();
        EnemyActivity enemyActivity = new EnemyActivity();
        List<String> attackPatterns = new ArrayList<>();
        CivilianImpact civilianImpact = new CivilianImpact();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\|");
                String recordType = parts[0];

                switch (recordType) {
                    case "MISSION_CREATED":
                        mission.setMissionId(parts[1]);
                        mission.setDate(parts[2]);
                        mission.setLocation(parts[3]);
                        break;

                    case "CURSE_DETECTED":
                        Curse curse = new Curse();
                        curse.setName(parts[1]);
                        curse.setThreatLevel(parts[2]);
                        mission.setCurse(curse);
                        break;

                    case "SORCERER_ASSIGNED":
                        Sorcerer sorcerer = new Sorcerer();
                        sorcerer.setName(parts[1]);
                        sorcerer.setRank(parts[2]);
                        sorcerers.add(sorcerer);
                        break;

                    case "TECHNIQUE_USED":
                        Technique technique = new Technique();
                        technique.setName(parts[1]);
                        technique.setType(parts[2]);
                        technique.setOwner(parts[3]);
                        technique.setDamage(Integer.parseInt(parts[4]));
                        techniques.add(technique);
                        break;

                    case "TIMELINE_EVENT":
                        OperationTimeline event = new OperationTimeline();
                        event.setTimestamp(parts[1]);
                        event.setType(parts[2]);
                        event.setDescription(parts[3]);
                        operationTimeline.add(event);
                        break;

                    case "ENEMY_ACTION":
                        attackPatterns.add(parts[1] + ": " + parts[2]);
                        break;

                    case "CIVILIAN_IMPACT":
                        for (int i = 1; i < parts.length; i++) {
                            String[] kv = parts[i].split("=");
                            if (kv.length == 2) {
                                switch (kv[0]) {
                                    case "evacuated":
                                        civilianImpact.setEvacuated(Integer.parseInt(kv[1]));
                                        break;
                                    case "injured":
                                        civilianImpact.setInjured(Integer.parseInt(kv[1]));
                                        break;
                                    case "missing":
                                        civilianImpact.setMissing(Integer.parseInt(kv[1]));
                                        break;
                                }
                            }
                        }
                        break;

                    case "MISSION_RESULT":
                        mission.setOutcome(parts[1]);
                        if (parts.length > 2) {
                            String[] kv = parts[2].split("=");
                            if (kv.length == 2 && kv[0].equals("damageCost")) {
                                mission.setDamageCost(Integer.parseInt(kv[1]));
                            }
                        }
                        break;
                }
            }

            mission.setSorcerers(sorcerers);
            mission.setTechniques(techniques);
            mission.setOperationTimeline(operationTimeline);
            enemyActivity.setAttackPatterns(attackPatterns);
            mission.setEnemyActivity(enemyActivity);
            mission.setCivilianImpact(civilianImpact);

            Director director = new Director();
            return director.createMission(new MissionBuilder(), mission);

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return null;
        }
    }
}