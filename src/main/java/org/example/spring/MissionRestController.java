package org.example.spring;

import org.example.model.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/missions")
public class MissionRestController {

    @Autowired
    private MissionService missionService;

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("txt", "xml", "json", "yaml", "dat");

    @GetMapping
    public List<Mission> getAllMissions() {
        return missionService.getAllMissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mission> getMission(@PathVariable String id) {
        Mission m = missionService.getMissionById(id);
        return m != null ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadMission(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            return ResponseEntity.badRequest().body("Файл не выбран");
        }

        String extension = "";
        int lastDot = originalFilename.lastIndexOf('.');
        if (lastDot > 0) {
            extension = originalFilename.substring(lastDot + 1).toLowerCase();
        } else {
            return ResponseEntity.badRequest().body("Файл должен иметь расширение .txt, .xml, .json, .yaml или .dat");
        }

        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            return ResponseEntity.badRequest().body("Недопустимый формат файла. Разрешены txt, xml, json, yaml, dat");
        }

        try {
            Mission saved = missionService.saveMissionFromFile(file);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка загрузки: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/report")
    public ResponseEntity<String> getReport(@PathVariable String id,
                                            @RequestParam(defaultValue = "false") boolean full) {
        String report = missionService.generateReport(id, full);
        if (report.startsWith("Миссия не найдена")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }
}