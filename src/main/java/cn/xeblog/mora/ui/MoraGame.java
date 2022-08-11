package cn.xeblog.mora.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @author anlingyi
 * @date 2022/8/11 8:02 PM
 */
public class MoraGame extends JPanel {

    /**
     * 猜拳获胜调用
     */
    private Runnable runnable;

    /**
     * 提示标签
     */
    private JLabel tipsLabel;

    /**
     * 结束标记
     */
    private boolean isOver;

    public MoraGame(Runnable runnable) {
        setPreferredSize(new Dimension(200, 100));
        setLayout(new BorderLayout());
        this.runnable = runnable;
        init();
    }

    private void init() {
        this.tipsLabel = new JLabel("请出拳！", JLabel.CENTER);
        this.tipsLabel.setPreferredSize(new Dimension(200, 50));
        this.tipsLabel.setFont(new Font("", 0, 15));
        this.tipsLabel.setForeground(new Color(255, 128, 128));

        JButton stoneButton = new JButton("石头");
        JButton shearsButton = new JButton("剪刀");
        JButton clothButton = new JButton("布");

        stoneButton.setFocusPainted(false);
        stoneButton.setBorderPainted(false);
        stoneButton.addActionListener(l -> handle(1));

        shearsButton.setFocusPainted(false);
        shearsButton.setBorderPainted(false);
        shearsButton.addActionListener(l -> handle(2));

        clothButton.setFocusPainted(false);
        clothButton.setBorderPainted(false);
        clothButton.addActionListener(l -> handle(3));

        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(200, 30));
        centerPanel.add(stoneButton);
        centerPanel.add(shearsButton);
        centerPanel.add(clothButton);

        add(tipsLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void handle(int selectedValue) {
        if (isOver) {
            return;
        }

        int value = new Random().nextInt(3) + 1;
        boolean isWin = selectedValue == (value - 1 == 0 ? 3 : value - 1);

        String result;
        if (isWin) {
            isOver = true;
            result = "你赢~";
        } else if (selectedValue == value) {
            result = "平局~";
        } else {
            result = "电脑赢~";
        }
        showTips("电脑 -> " + getText(value) + "，你 -> " + getText(selectedValue) + "，" + result);

        if (isWin) {
            new Thread(() -> {
                try {
                    Thread.sleep(800);
                    this.runnable.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    private String getText(int value) {
        switch (value) {
            case 1:
                return "石头";
            case 2:
                return "剪刀";
            case 3:
                return "布";
        }

        return "";
    }

    private void showTips(String tips) {
        tipsLabel.setText(tips);
    }

}
