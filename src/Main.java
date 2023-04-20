import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String folder = "E:\\TestFolder\\";
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter file name (.txt): ");
        String filename = reader.nextLine().toLowerCase().trim() + ".txt";
        File f = new File(folder + filename);
        if (!f.exists()){
            System.out.println("The file does not exist!");
            if (f.createNewFile()){
                System.out.println("File created");
                writeToFile(folder + filename);
            }
            else {
                System.out.println("File could not be created!");
            }
        } else {
            System.out.println("File exists!");
            writeToFile(folder + filename);
        }

        readFile(f, folder + filename);
        getFileInfo(f, folder + filename);
        deleteFile(f, folder + filename);
    }

    private static void writeToFile(String filename){
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("""
                    This is a file with multiple lines.
                    This is the second line to the file's information
                    This is the third line and is not very exciting.
                    You are now reading the final line, well done!
                    Sorry, I lied, this is the last line... ha ha ha!
                    """);
            myWriter.close();
            System.out.println("Successfully wrote to the file!");
        } catch (IOException e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }

    private static void readFile(File f, String filename){
        try {
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    private static void getFileInfo(File f, String filename){
        if (f.exists()){
            System.out.println("Filename : " + f.getName());
            System.out.println("Absolution Path: " + f.getAbsolutePath());
            System.out.println("Writeable: " + f.canWrite());
            System.out.println("Readable: " + f.canRead());
            System.out.println("File size: " + f.length() + " bytes");
        }
        else {
            System.out.println("File not found!");
        }
    }

    private static void deleteFile(File f, String filename){
        //Check user wants to remove the file.
        System.out.println("To remove the file type the filename: " + f.getName() + " on the next line!");
        Scanner scan = new Scanner(System.in);
        String verify = scan.nextLine();
        if (verify.equals(f.getName()) && (f.delete())){
                System.out.println(f.getName() + " has been deleted!");
            } else {
                System.out.println("Failed to delete file " + f.getName());
            }
        }
}
