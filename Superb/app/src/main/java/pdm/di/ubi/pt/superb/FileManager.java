package pdm.di.ubi.pt.superb;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileManager {
    final public static String SALUTATION = "/data/data/pdm.di.ubi.pt.superb/Salutations.ash";
    final public static String ICEBREAK = "/data/data/pdm.di.ubi.pt.superb/IceBreaks.ash";
    final public static String VOWS = "/data/data/pdm.di.ubi.pt.superb/Vows.ash";
    final public static String DISMISSAL = "/data/data/pdm.di.ubi.pt.superb/Dismissals.ash";

    private static ArrayList<String> readFile(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine);
            }
        }
        catch (IOException e)
        {
            Log.e("FileManager", "Error: " + e.getMessage());
        }

        String result = contentBuilder.toString();

        String[] aglomerado = result.split("\\$");
        return new ArrayList<String>(Arrays.asList(aglomerado));
    }

    public static Detail getDetail() {
        try {
            return new Detail(
                    readFile(SALUTATION),
                    readFile(ICEBREAK),
                    readFile(VOWS),
                    readFile(DISMISSAL)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; //ALWAYS TEST IF NULL!
    }
}