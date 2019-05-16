package com.realtime.nyc.subway.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;

public final class FileUtils {

    public static Iterable<CSVRecord> readFileAsCsv(String path) throws Exception {
        InputStream inputStream = new ClassPathResource(path).getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
    }
}
