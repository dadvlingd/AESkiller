package com.example.aeskiller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class aeskiller {
    public static void main(String[] args) {
        // 创建一个 JFrame 实例
        JFrame frame = new JFrame("AES_KILLER");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建一个 JPanel 实例
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // 设置框架可见
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // 创建一个标签
        JLabel userLabel = new JLabel("AES密钥数组:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        // 创建一个文本域用于用户输入
        JTextField KeyText = new JTextField("[1903650162, 1954116969, 1869623602, 859059510]");
        KeyText.setBounds(10, 40, 350, 25);
        panel.add(KeyText);

        // 创建一个按钮
        JButton loginButton = new JButton("解析");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
        JLabel mseLabel = new JLabel("返回值:");
        mseLabel.setBounds(10, 110, 80, 25);
        panel.add(mseLabel);
        // 创建一个标签显示消息
        JTextField messageLabel = new JTextField("");
        messageLabel.setBounds(50, 110, 300, 25);
        panel.add(messageLabel);
        JLabel authorLabel = new JLabel("作者:yuday");
        authorLabel.setBounds(10, 200, 80, 25);
        panel.add(authorLabel);



        // 添加按钮点击事件
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = KeyText.getText();
                if(key.isEmpty()){
                    messageLabel.setText("请输入需要解析的数组！");

                }else {

                    messageLabel.setText(decode(key));
                }
            }
        });

    }
    public static String decode(String input){
        // 移除方括号并按逗号分割
        String[] stringArray = input.replaceAll("\\[|\\]", "").split(", ");

        // 转换为整数数组
        int[] keyArray= new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            keyArray[i] = Integer.parseInt(stringArray[i]);
        }



        // 将整数数组转换为字节数组
        byte[] keyBytes = new byte[keyArray.length * 4];
        ByteBuffer buffer = ByteBuffer.wrap(keyBytes);
        for (int keyPart : keyArray) {
            buffer.putInt(keyPart);
        }

        // 将字节数组转换为字符串
        String keyString = new String(keyBytes, StandardCharsets.UTF_8);
        return keyString;


    }
}
