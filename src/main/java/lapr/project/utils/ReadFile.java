package lapr.project.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    public static void main(String[] args) throws IOException {

        FileReader in = new FileReader("sships.csv");
        BufferedReader br = new BufferedReader(in);

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        in.close();

    }
}







