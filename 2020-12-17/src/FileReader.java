import java.io.File;

public class FileReader {
    public static void main(String[] args) {
        File fp=new File("F:\\college life");
        readAll(fp);
    }
    static void readAll(File fp){
        File[] fps=fp.listFiles();
        for (File f:
             fps) {
            if (f.isDirectory()) {
                readAll(f);
            }
            System.out.println(f.getName());
        }
    }
}
