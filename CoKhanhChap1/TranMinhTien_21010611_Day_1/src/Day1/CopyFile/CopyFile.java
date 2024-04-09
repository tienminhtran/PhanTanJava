/*
 * @(#)CopyFile.java 1.0  14/1/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     14/1/2024
 *@Version:  1.0
 */


package Day1.CopyFile;

import java.io.*;

public class CopyFile {
    private String source;
    private String destination;

    public CopyFile(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public void copy(ProgressCallback progressCallback) {
        try (
                FileInputStream in = new FileInputStream(source);
                FileOutputStream out = new FileOutputStream(destination);

                BufferedInputStream bin = new BufferedInputStream(in);
                BufferedOutputStream bout = new BufferedOutputStream(out);
        ) {
//            byte[] buffer = new byte[5];
//
//            while (bin.available() > 0) {
//                int length = bin.read(buffer);
//                bout.write(buffer);
//            }

            byte[] buffer = new byte[1024];
            long totalBytes = new File(source).length();
            long copiedBytes = 0;

            int bytesRead;
            while ((bytesRead = bin.read(buffer)) != -1) {
                bout.write(buffer, 0, bytesRead);
                copiedBytes =copiedBytes + bytesRead;
                int progress = (int) ((copiedBytes * 100) / totalBytes);
                // Gửi tiến trình sao chép về callback
                if (progressCallback != null) {
                    progressCallback.onProgress(progress);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}