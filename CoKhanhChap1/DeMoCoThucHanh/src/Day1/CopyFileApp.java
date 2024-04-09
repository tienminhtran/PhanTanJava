package Day1;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopyFileApp {

    public static void main(String[] args) {
        CopyFile copyFile = new CopyFile("D:\\BAI_TAP_LTPT_DHKTPM17A\\CoKhanhChap1\\DeMoCoThucHanh\\src\\Day1\\data.txt", "D:\\BAI_TAP_LTPT_DHKTPM17A\\CoKhanhChap1\\DeMoCoThucHanh\\src\\Day1\\output.txt");
        copyFile.copy();
    }
}

class CopyFile {
    private String source;
    private String destination;

    public CopyFile(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public void copy() {

        try(
                FileInputStream in = new FileInputStream(source);
                FileOutputStream out = new FileOutputStream(destination);

                BufferedInputStream bin = new BufferedInputStream(in);
                BufferedOutputStream bout = new BufferedOutputStream(out);
                ){

            byte[] buffer = new byte[5];

            while (bin.available() > 0){
                int length = bin.read(buffer);
                bout.write(buffer);
//                bout.write(buffer, 0, length);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
