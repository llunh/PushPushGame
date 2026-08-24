package term_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayerName extends JFrame {
	private static String playerName;

    // 시작 창 JFrame을 받아서 참조하도록 변경
    public PlayerName(JFrame mainFrame) {
        setTitle("Enter Name");
        setSize(300,150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("닉네임:");
        JTextField nameField = new JTextField(15);
        JButton okButton = new JButton("OK");

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerName = nameField.getText();
                if (playerName.isEmpty()) {
                    return;
                }
                dispose();        // 이름 입력 창 닫기
                mainFrame.dispose();    // 시작 창 닫기

                GamePanel.launchGame(); // 게임 시작
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        panel.add(label);
        panel.add(nameField);
        panel.add(okButton);

        add(panel);
        setVisible(true);
    }

    public static String getPlayerName() {
        return playerName;
    }
    
    public static void showNameInputWindow(JFrame mainFrame) {
    	new PlayerName(mainFrame);
    }
}


