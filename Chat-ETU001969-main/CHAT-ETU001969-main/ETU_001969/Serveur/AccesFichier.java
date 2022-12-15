package input;

import main.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AccesFichier {
    public static void enregistrer(String message,String Pathfichier) throws IOException {
        File file = new File(Main.data+Pathfichier+".txt");
        FileWriter writer = new FileWriter(file,true);
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        String verif = bufferedReader.readLine();
        if (verif!= null){
            bufferedWriter.write("\n");
        }

        bufferedWriter.write(message);
        bufferedWriter.close();
    }

    public static void creer(File file) throws IOException {
        if(!file.exists()) {
            file.createNewFile();
        }
    }

    public static int countLines(String filename) throws IOException {
        Path path = Paths.get(filename);
        int nbline = 0;

        nbline = (int) Files.lines(path).count();
        return nbline;
    }

    public static String[][] getData(String fichier) throws Exception{
        String filename = fichier;

        int nbLine = countLines(filename);
        String[][] data = new String[nbLine][2];

        FileReader read = new FileReader(filename);
        BufferedReader br = new BufferedReader(read);
        int i=0;
        String line = br.readLine();
        while(line != null){
            data[i] = line.split("::");
            line = br.readLine();
            i++;
        }
        return data;
    }
}
