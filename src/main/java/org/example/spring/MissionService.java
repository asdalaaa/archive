package org.example.spring;

import org.example.builder.Director;
import org.example.builder.MissionBuilder;
import org.example.model.*;
import org.example.reports.AdditionalInfoReportDecorator;
import org.example.reports.MissionReport;
import org.example.reports.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    public Mission saveMissionFromFile(MultipartFile file) throws IOException {
        Path tempFile = Files.createTempFile("upload_", "_" + file.getOriginalFilename());
        File convFile = tempFile.toFile();
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }

        String fileName = convFile.getAbsolutePath();
        String extension = "";
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot > 0) extension = fileName.substring(lastDot + 1).toLowerCase();

        org.example.fabric.ParserCreator fabric;
        switch (extension) {
            case "txt": fabric = new org.example.fabric.TxtParserCreator(); break;
            case "xml": fabric = new org.example.fabric.XmlParserCreator(); break;
            case "json": fabric = new org.example.fabric.JsonParserCreator(); break;
            case "yaml": fabric = new org.example.fabric.YamlParserCreator(); break;
            case "": fabric = new org.example.fabric.BinaryParserCreator(); break;
            default: throw new RuntimeException("Не удалось распознать файл");
        }

        Mission mission = fabric.parse(convFile.getAbsolutePath());
        convFile.delete();

        if (mission == null) {
            throw new RuntimeException("Не удалось распарсить файл. Проверьте формат и содержимое.");
        }

        if (mission.getSorcerers() != null) {
            for (Sorcerer s : mission.getSorcerers()) s.setMission(mission);
        }
        if (mission.getTechniques() != null) {
            for (Technique t : mission.getTechniques()) t.setMission(mission);
        }
        if (mission.getOperationTimeline() != null) {
            for (OperationTimeline ot : mission.getOperationTimeline()) ot.setMission(mission);
        }

        return missionRepository.save(mission);
    }

    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    public Mission getMissionById(String id) {
        return missionRepository.findById(id).orElse(null);
    }

    public String generateReport(String missionId, boolean includeAdditionalInfo) {
        Mission mission = getMissionById(missionId);
        if (mission == null) return "Миссия не найдена";

        Report baseReport = new MissionReport();
        if (includeAdditionalInfo) {
            AdditionalInfoReportDecorator decorator = new AdditionalInfoReportDecorator(baseReport);
            return decorator.getReport(mission);
        } else {
            return baseReport.getReport(mission);
        }
    }
}
