import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable


import random

public class fump {
	
    static List<String> maleNames = []
    static List<String> femaleNames = []
    static boolean isLoaded = false

    private void loadNames() {
        if (!isLoaded) {
            String filePath = "Data Files/Vornamen_1000_je_Geschlecht.csv"
            List<String> lines = Files.readAllLines(Paths.get(filePath))
            lines.remove(0) // Entferne Header
            lines.each { line ->
                String[] parts = line.split(",")
                if (parts.length >= 2) {
                    maleNames.add(parts[0].trim())
                    femaleNames.add(parts[1].trim())
                }
            }
            isLoaded = true
        }
    }

    @Keyword
    def String getRandomName(String gender) {
        loadNames()
        Random rand = new Random()
        if (gender.equalsIgnoreCase("m")) {
            return maleNames.get(rand.nextInt(maleNames.size()))
        } else if (gender.equalsIgnoreCase("w")) {
            return femaleNames.get(rand.nextInt(femaleNames.size()))
        } else {
            throw new IllegalArgumentException("Geschlecht muss 'm' oder 'w' sein.")
        }
    }
	
	
}
