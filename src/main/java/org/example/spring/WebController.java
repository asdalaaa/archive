package org.example.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private MissionService missionService;

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("txt", "xml", "json", "yaml", "dat");

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/upload")
    public String uploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadMission(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Ошибка: выберите файл");
            return "upload";
        }

        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();

        String extension = "";
        int lastDot = originalFilename.lastIndexOf('.');
        if (lastDot > 0) {
            extension = originalFilename.substring(lastDot + 1).toLowerCase();
        }

        boolean allowedExtension = Arrays.asList("txt", "xml", "json", "yaml", "dat").contains(extension);
        boolean allowedMime = contentType != null && (
                contentType.equals("text/plain") ||
                        contentType.equals("application/xml") ||
                        contentType.equals("text/xml") ||
                        contentType.equals("application/json") ||
                        contentType.equals("application/x-yaml") ||
                        contentType.equals("text/yaml") ||
                        contentType.equals("application/octet-stream") // для .dat
        );

        if (!allowedExtension || !allowedMime) {
            model.addAttribute("message", "Недопустимый формат файла. Разрешены: txt, xml, json, yaml, dat");
            return "upload";
        }

        try {
            missionService.saveMissionFromFile(file);
            model.addAttribute("message", "Миссия успешно загружена");
        } catch (Exception e) {
            model.addAttribute("message", "Ошибка при обработке файла: " + e.getMessage());
            e.printStackTrace(); // для отладки
        }
        return "upload";
    }

    @GetMapping("/archive")
    public String archive(Model model) {
        model.addAttribute("missions", missionService.getAllMissions());
        return "archive";
    }

    @GetMapping("/report/{id}")
    public String reportForm(@PathVariable String id, Model model) {
        model.addAttribute("missionId", id);
        return "report";
    }

    @PostMapping("/report")
    public String generateReport(@RequestParam String missionId,
                                 @RequestParam(defaultValue = "false") boolean fullReport,
                                 Model model) {
        String reportText = missionService.generateReport(missionId, fullReport);
        model.addAttribute("report", reportText);
        model.addAttribute("missionId", missionId);
        return "report";
    }
}