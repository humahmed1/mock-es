package com.example.es;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.intuit.karate.Runner;
import net.masterthought.cucumber.Configuration;
import org.apache.commons.io.FileUtils;

import com.intuit.karate.Results;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.Test;

public class EsKarateRunner {

    @Test
    public void testParallel() {
        Results results = Runner.path("classpath:features/retailCustomer.feature").outputCucumberJson(true).parallel(1);
        generateReport(results.getReportDir());
        assertTrue(results.getErrorMessages(), results.getFailCount() == 0);

    }

    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[]{"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "cmp");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}