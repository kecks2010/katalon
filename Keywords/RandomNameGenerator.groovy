package custom

import com.kms.katalon.core.annotation.Keyword
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Random

public class RandomNameGenerator {

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
