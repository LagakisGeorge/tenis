package com.example.tenis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateCsv {

    public  static FileWriter generateCsvFile(File sFileName, String fileContent) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(sFileName);
            writer.append(fileContent);
            writer.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally
        {
            try {
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return writer;
    }
}
