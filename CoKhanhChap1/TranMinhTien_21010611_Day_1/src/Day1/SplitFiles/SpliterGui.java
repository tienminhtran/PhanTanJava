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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class SpliterGui extends JFrame implements ActionListener {
    private JTextField txt_inputFile;
    private JTextField txt_outputFile;
    private JTextField txt_numberFile;
    private JButton btn_inputFile;
    private JButton btn_outputFolder;
    private JButton btn_split;
    private JProgressBar progressBar;

    public SpliterGui() {
        setTitle("Spliter and Combiner _ 21010611 - Tran Minh Tien");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        txt_inputFile = new JTextField();
        txt_outputFile = new JTextField();
        txt_numberFile = new JTextField();
        btn_inputFile = new JButton("...");
        btn_outputFolder = new JButton("...");
        btn_split = new JButton("Split Its");
        btn_split.setSize(100, 50);
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        JLabel lbl_inputFile = new JLabel("Input File:");
        JLabel lbl_outputFile = new JLabel("Output File:");
        JLabel lbl_numberFile = new JLabel("Enter number file of split into:");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Input File
        add(lbl_inputFile, gbc);
        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(txt_inputFile, gbc);
        gbc.gridx++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        add(btn_inputFile, gbc);

        // Output File
        gbc.gridx = 0;
        gbc.gridy++;
        add(lbl_outputFile, gbc);
        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txt_outputFile, gbc);
        gbc.gridx++;
        gbc.fill = GridBagConstraints.NONE;
        add(btn_outputFolder, gbc);

        // Number of Files
        gbc.gridx = 0;
        gbc.gridy++;
        add(lbl_numberFile, gbc);
        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txt_numberFile, gbc);

        // Split Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btn_split, gbc);

        // Progress Bar
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(progressBar, gbc);

        btn_inputFile.addActionListener(this);
        btn_outputFolder.addActionListener(this);
        btn_split.addActionListener(this);

        progressBar.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((Integer) evt.getNewValue());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btn_inputFile)) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose input file");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                txt_inputFile.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (o.equals(btn_outputFolder)) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose output folder");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                txt_outputFile.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (o.equals(btn_split)) {
            String pathFileInput = txt_inputFile.getText();
            String pathFileFolder = txt_outputFile.getText();
            int numberFile = Integer.parseInt(txt_numberFile.getText());

            File inputFile = new File(pathFileInput);
            File outPutFolder = new File(pathFileFolder);

            Splitter_Task task = new Splitter_Task(inputFile, outPutFolder, numberFile, progressBar);
            task.execute();

            txt_inputFile.setText("");
            txt_outputFile.setText("");
            txt_numberFile.setText("");
            txt_inputFile.requestFocus();
        }
    }
    public static void main(String[] args) {
        SpliterGui spliterGui = new SpliterGui();
        spliterGui.setSize(500, 300);
        spliterGui.setVisible(true);
    }
}


