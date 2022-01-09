package com.project.smdb.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface BackupService {
    Map<String, List<String>> generateBackup();
    File getFile(String fileName) throws FileNotFoundException;
}
