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

package Day1.SplitFiles;

import javax.swing.*;
import java.io.*;
import java.util.List;

public class Splitter_Task extends SwingWorker<Long, Integer> {

    private File inputFile;
    private File outputFolder;
    private int numberSplitFile;
    private JProgressBar progressBar;

    public Splitter_Task(File inputFile, File outputFolder, int numberSplitFile, JProgressBar progressBar) {
        this.inputFile = inputFile;
        this.outputFolder = outputFolder;
        this.numberSplitFile = numberSplitFile;
        this.progressBar = progressBar;
    }

    @Override
    protected Long doInBackground() throws Exception {
        try {
            // Tạo thư mục đầu ra nếu nó không tồn tại
            if (!outputFolder.exists()) {
                outputFolder.mkdirs();
            }

            // Lấy kích thước toàn bộ file và kích thước mỗi phần
            long totalSize = inputFile.length();
            long splitSize = totalSize / numberSplitFile;

            try (FileInputStream in = new FileInputStream(inputFile);
                 BufferedInputStream bin = new BufferedInputStream(in)) {

                // Chia file thành các phần và lưu vào thư mục đầu ra
                for (int i = 0; i < numberSplitFile; i++) {
                    long start = i * splitSize;

                    long end;
                    if (i == numberSplitFile - 1) {
                        end = totalSize;
                    } else {
                        end = (i + 1) * splitSize;
                    }

                    // Đọc từ file gốc và ghi vào file phần đã chia
                    byte[] buffer = new byte[(int) (end - start)];
                    int length = bin.read(buffer);

                    // Tạo tên file cho mỗi phần
                    String splitFileName = String.format("%s/File%d.txt", outputFolder, i);

                    try (FileOutputStream out = new FileOutputStream(splitFileName);
                         BufferedOutputStream bout = new BufferedOutputStream(out)) {
                        // Ghi dữ liệu vào file phần đã chia
                        bout.write(buffer, 0, length);
                    }

                    // Tính toán và thông báo tiến độ
                    int progress = (int) (((double) i + 1) / numberSplitFile * 100);
                    publish(progress);
                }
            }

        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            e.printStackTrace();
        }
        // Trả về null vì phương thức này đang trả về kiểu Long
        return null;
    }


    @Override
    protected void process(List<Integer> a) {
        int progress = a.get(a.size() - 1);
        progressBar.setValue(progress);
        progressBar.setString(progress +  " %");
    }

    @Override
    protected void done() {
        try {
            JOptionPane.showMessageDialog(null, "Kết thúc chia file thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
