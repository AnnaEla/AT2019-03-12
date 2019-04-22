package by.it.medvedeva.at11;


import java.io.*;


public class TaskC {
    static DataOutputStream dos1 = null;

    public static void main(String[] args) throws IOException {
        String src = System.getProperty("user.dir") + "/src/by/it/";
        String filename = src + "/medvedeva";

        File catalog = new File(filename);
        String name = catalog.getName();
        System.out.println(name);
        System.out.println(catalog.isDirectory());

        String src1 = System.getProperty("user.dir") + "/src/by/it/medvedeva/";
        String filename1 = src1 + "at11/resultTaskC.txt";
        File f1 = new File(filename1);
        f1.getParentFile().mkdirs();
        f1.createNewFile();


        try {
            dos1 = new DataOutputStream(new FileOutputStream(f1));
            searchFiles(catalog);


        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename1);
        } finally {
            if (dos1 != null) {
                dos1.close();
            }


//
        }



    }

    private static void searchFiles(File object) throws IOException {
        System.out.println("dir:" + object.getName());
        dos1.writeBytes("dir:" + object.getName());
        dos1.writeBytes("\n");
        File[] filesOfObject = object.listFiles();
        for (File el : filesOfObject) {
            if (el.isDirectory()) {
                searchFiles(el);
            }
            if (el.isFile()) {
                System.out.println("file:" + el.getName());
                dos1.writeBytes("file:" + el.getName());
                dos1.writeBytes("\n");
            }


        }


    }


}
