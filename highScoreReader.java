import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class highScoreReader {
    private String[] names;
    private int[] scores;
    private String[] dates;

    public String[] getNames(){return names;}
    public int[] getScores(){return scores;}
    public String[] getDates(){return dates;}

    @SuppressWarnings("resource")
    public highScoreReader(String filename) 
    {
        names = new String[10];
        scores = new int[10];
        dates = new String[10];
        Scanner infile;

        File file = new File("highScores.txt");
        try{
            infile = new Scanner(file);
            if(!file.exists())
            {
                throw new FileNotFoundException("File not found: " + filename);
            }
            
            for (int i = 0; i < 10; i++) {
                names[i] = infile.nextLine().strip();
            }
            for (int i = 0; i < 10; i++) {
                scores[i] = Integer.parseInt(infile.nextLine().strip());
            }
            for (int i = 0; i < 10; i++) {
                dates[i] = infile.nextLine().strip();
            }
            infile.close();
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void bumpScores(int position, String newName, int newScore, String newDate) {
        for (int i = 9; i > position; i--) {
            names[i] = names[i-1];
            scores[i] = scores[i-1];
            dates[i] = dates[i-1];
        }
        names[position] = newName;
        scores[position] = newScore;
        dates[position] = newDate;
    }
    public void updateFile() {
        String newString = "";
        for (int i = 0; i < 10; i++) {
            newString += names[i] + "\n";
        }
        for (int i = 0; i < 10; i++) {
            newString += "" + scores[i] + "\n";
        }
        for (int i = 0; i < 10; i++) {
            newString += dates[i] + "\n";
        }
        try {
            FileWriter infile = new FileWriter("highScores.txt");
            infile.write(newString);
            infile.close();
        } catch (Exception e) {
            System.out.println("Failed to write to file " + e);
        }

    }
}