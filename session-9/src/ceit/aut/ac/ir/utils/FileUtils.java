package ceit.aut.ac.ir.utils;

import ceit.aut.ac.ir.model.Note;

import java.io.*;

public class FileUtils {

    private static final String NOTES_PATH = "./notes/";

    //It's a static initializer. It's executed when the class is loaded.
    //It's similar to constructor
    static {
        boolean isSuccessful = new File(NOTES_PATH).mkdirs();
        System.out.println("Creating " + NOTES_PATH + " directory is successful: " + isSuccessful);
    }

    public static File[] getFilesInDirectory() {
        return new File(NOTES_PATH).listFiles();
    }


    public static String fileReader(File file) {
        StringBuilder content = new StringBuilder();
        try(FileReader reader = new FileReader(file)) {
            int ptr;
            while((ptr = reader.read()) != -1){
                content.append((char) ptr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void fileWriter(String content) {
        String fileName = getProperFileName(content);
        try(FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String bufferedFileReader(File file) {
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int ptr;
            while((ptr = reader.read()) != -1){
                content.append((char) ptr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void bufferedFileWriter(String content) {
        String fileName = getProperFileName(content);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Note NoteReader(File file) {
        Note retNote = null;
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
            retNote = (Note) reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return retNote;
    }

    public static void NoteWriter(Note note) {
        String fileName = note.getTitle() + ".bin";
        try(ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(NOTES_PATH + fileName))) {
            write.writeObject(note);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProperFileName(String content) {
        int loc = content.indexOf("\n");
        if (loc != -1) {
            return content.substring(0, loc);
        }
        if (!content.isEmpty()) {
            return content;
        }
        return System.currentTimeMillis() + "_new file.txt";
    }
}
