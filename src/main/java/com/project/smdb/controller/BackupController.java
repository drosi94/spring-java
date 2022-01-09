package com.project.smdb.controller;

import com.project.smdb.service.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackupController {
    private final BackupService backupService;

    @Autowired
    public BackupController(BackupService backupService) {
        this.backupService = backupService;
    }

    @GetMapping("/backup")
    public ResponseEntity<?> backup() {
        backupService.generateBackup();
        return ResponseEntity.ok("Backup");
    }
}
