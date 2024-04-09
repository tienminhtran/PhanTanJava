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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JDialog {
    private JPanel contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private JButton COPYButton;
    private JProgressBar progressBar1;
    private JButton buttonOK;
    private JButton buttonCancel;
    private Timer timer;

    public Gui() {
        setContentPane(contentPane);
        setModal(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        progressBar1.setStringPainted(true);

        COPYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CopyFile copyFile = new CopyFile(textField1.getText(), textField2.getText());

                new Thread(() -> {
                    for (int i = 1; i <= 100; ++i) {
                        try {
                            Thread.sleep(80);

                                progressBar1.setValue(i);
                                progressBar1.setString(i + "%");

                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }

                    // Khi kết thúc vòng lặp, thực hiện sao chép file
                    SwingUtilities.invokeLater(() -> {
                        copyFile.copy(new ProgressCallback() {
                            @Override
                            public void onProgress(int percent) {
                                SwingUtilities.invokeLater(() -> {
                                    progressBar1.setValue(percent);
                                    progressBar1.setString(percent + "%");
                                });
                            }
                        });
                    });
                }).start();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Gui dialog = new Gui();
            dialog.pack();
            dialog.setVisible(true);
            System.exit(0);
            dialog.setLocationRelativeTo(null);
        });
    }
}


/*
 * @(#)CopyFile.java 1.0  14/1/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */
//package Day1.CopyFile;
//
//import javax.swing.*;
//        import java.awt.*;
//        import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Gui extends JDialog {
//    private JPanel contentPane;
//    private JTextField textField1;
//    private JTextField textField2;
//    private JButton COPYButton;
//    private JProgressBar progressBar1;
//    private JButton buttonOK;
//    private JButton buttonCancel;
//    private Timer timer;
//
//    public Gui() {
//        setContentPane(contentPane);
//        setModal(true);
//        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//        progressBar1.setStringPainted(true);
//
//        COPYButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                CopyFile copyFile = new CopyFile(textField1.getText(), textField2.getText());
//                for (int i = 1; i <= 100; ++i) {
//
//                    try {
//                        Thread.sleep(80);
//                        if (i % 2 == 0) {
//                            progressBar1.setValue(i);
//                            progressBar1.setString(i + "%");
//                        } else {
//                            progressBar1.setValue(i);
//                            progressBar1.setString(i + "%");
//                        }
//                        if (i == 100) {
//                            copyFile.copy(new ProgressCallback() {
//                                @Override
//                                public void onProgress(int percent) {
//                                    progressBar1.setValue(percent);
//                                    progressBar1.setString(percent + "%");
//
//                                }
//                            });
//
//                        }
//                    } catch (InterruptedException interruptedException) {
//                        interruptedException.printStackTrace();
//                    }
//
//                }
//            }
//        });
//    }
//
//    public static void main (String[]args){
//
//        Gui dialog = new Gui();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//        dialog.setLocationRelativeTo(null);
//
//    }
//}