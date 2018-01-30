package cn.PApudding.UI;

import cn.PApudding.BytesPipeline.File2Bytes;
import cn.PApudding.Util.CodeGenerate;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class MainFrame extends JFrame {
    private JLabel label_SelectFile = new JLabel("选择文件");
    private JLabel label_SelectAlgorithm = new JLabel("选择算法");
    private JLabel label_OriginCode = new JLabel("比对值");
    private JComboBox<String> comboBox_Algorithm = new JComboBox<>();
    private JButton button_SelectFile = new JButton("打开..");
    private JButton button_Confirm = new JButton("生 成");
    private JButton button_Compare = new JButton("开始比对");
    private JTextArea textArea_Result = new JTextArea();
    private JTextArea textArea_File = new JTextArea();
    private JTextArea textArea_originCode = new JTextArea();
    private Container container;

    private String filePath;

    public MainFrame() {
        frameConfig();
        setAllComponentsBounds();
        setAllComponentsFont();
        addComponents();
        specialComponentsConfig();
        addButtonListener();
    }

    private void frameConfig() {
        container = this.getContentPane();
        this.setLayout(null);
        this.setLocationByPlatform(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(600, 400);
        this.setMaximumSize(new Dimension(600, 400));
        this.setMinimumSize(new Dimension(600, 400));
    }

    private void addComponents() {
        container.add(label_SelectFile);
        container.add(label_SelectAlgorithm);
        container.add(label_OriginCode);
        container.add(comboBox_Algorithm);
        container.add(button_SelectFile);
        container.add(button_Confirm);
        container.add(button_Compare);
        container.add(textArea_Result);
        container.add(textArea_File);
        container.add(textArea_originCode);
    }

    private void setAllComponentsBounds() {
        label_SelectFile.setBounds(80, 30, 75, 18);
        label_SelectAlgorithm.setBounds(80, 60, 75, 18);
        label_OriginCode.setBounds(80, 145, 75, 18);
        comboBox_Algorithm.setBounds(160, 60, 75, 20);
        button_SelectFile.setBounds(365, 30, 75, 20);
        button_Confirm.setBounds(365, 60, 75, 18);
        button_Compare.setBounds(405, 220, 75, 18);
        textArea_Result.setBounds(80, 90, 400, 50);
        textArea_File.setBounds(160, 30, 200, 18);
        textArea_originCode.setBounds(80, 165, 400, 50);
    }

    private void setAllComponentsFont() {
        label_SelectFile.setFont(new Font("宋体", 0, 18));
        label_SelectAlgorithm.setFont(new Font("宋体", 0, 18));
        label_OriginCode.setFont(new Font("宋体", 0, 18));
        comboBox_Algorithm.setFont(new Font("宋体", 0, 14));
        button_SelectFile.setFont(new Font("宋体", 0, 12));
        button_Confirm.setFont(new Font("宋体", 0, 12));
        button_Compare.setFont(new Font("宋体", 0, 10));
        textArea_Result.setFont(new Font("宋体", 0, 14));
        textArea_File.setFont(new Font("宋体", 0, 16));
        textArea_originCode.setFont(new Font("宋体", 0, 14));
    }

    private void specialComponentsConfig() {
        textArea_File.setEditable(false);
        textArea_Result.setWrapStyleWord(true);
        textArea_Result.setLineWrap(true);
        textArea_Result.setEditable(false);
        textArea_originCode.setWrapStyleWord(true);
        textArea_originCode.setLineWrap(true);
        comboBox_Algorithm.addItem("MD5");
        comboBox_Algorithm.addItem("SHA-1");
        comboBox_Algorithm.addItem("SHA-256");
        comboBox_Algorithm.addItem("SHA-512");
        comboBox_Algorithm.addItem("SHA-224");
        comboBox_Algorithm.addItem("SHA-384");
    }

    private void addButtonListener() {
        button_SelectFile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    filePath = jFileChooser.getSelectedFile().getAbsolutePath();
                    textArea_File.setText(jFileChooser.getSelectedFile().getName());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button_SelectFile.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_SelectFile.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        button_Confirm.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                File2Bytes file2Bytes = File2Bytes.getInstance(filePath);
                try {
                    String value = CodeGenerate.generateValueByBytes(file2Bytes.getFileBytes(),comboBox_Algorithm.getSelectedItem().toString());
                    textArea_Result.setText(value);
                } catch (NoSuchAlgorithmException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button_Confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_Confirm.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        button_Compare.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(textArea_Result.getText().equals(textArea_originCode.getText())){
                    JOptionPane.showMessageDialog(null,"比对数据一致");
                }else {
                    JOptionPane.showMessageDialog(null,"比对数据不一致");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
