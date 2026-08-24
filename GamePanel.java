package term_;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JFrame {
   private GameAreaPanel gameAreaPanel;
    private StageRecord[] records;  // 스테이지 기록
    private GameMap map;            // 맵 정보
    private MoveCounter moveCounter; // 이동 횟수 기록
    private Timer timer;             // 시간 기록용

    private int TILE_SIZE = 40;

    public GamePanel() {
        //super("PushPush Mario");

        records = new StageRecord[3];
        map = new GameMap();
        moveCounter = new MoveCounter();
        timer = new Timer();

        // JFrame 기본 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        gameAreaPanel = new GameAreaPanel();  //객체 생성
        add(gameAreaPanel, BorderLayout.CENTER);

        int width=TILE_SIZE *13+13;
        int height=TILE_SIZE *13+80; //버튼 영역 고려
        
        setSize(width,height);
        setLocationRelativeTo(null);
        setVisible(true);
        
        // 버튼 패널
        JPanel settingsPanel = new JPanel(new FlowLayout());
        JButton resetButton = new JButton("다시 시작");
        resetButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
              resetCurrentStage();
           }
        });
        JButton exitButton = new JButton("게임 종료");
        exitButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
              System.exit(0);
           }
        });
        settingsPanel.add(resetButton);
        settingsPanel.add(exitButton);

        add(settingsPanel, BorderLayout.SOUTH);

        // 키 이벤트 설정 (JFrame 자체에 키리스너 등록)
        addKeyListener(new MyKeyListener());
        setFocusable(true);

        timer.start();

        // 화면 주기적 갱신 타이머
        new javax.swing.Timer(500, new ActionListener() {
           public void actionPerformed(ActionEvent e) {
              gameAreaPanel.repaint();
           }
        }).start();

        setLocationRelativeTo(null);
        setVisible(true);    
 }
    private class GameAreaPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 게임 화면은 JFrame의 클라이언트 영역 중 중앙 영역을 직접 그려야 하므로
        // 좌측 상단에 여백을 줄 수 있게 약간 조정 (버튼 영역 고려)

        // 게임맵 그리기
        map.paintComponent(g);

        // 정보 표시
        g.setColor(Color.WHITE);
        g.fillRect(8, 3, 150, 25);
        g.fillRect(8, 28, 150, 20);
        g.fillRect(8, 48, 150, 20);

        g.setColor(Color.BLACK);
        g.drawString("< Stage " + (map.getCurrentStage() + 1) + " >", 10, 15);
        g.drawString("스텝(step): " + moveCounter.getCount(), 10, 40);
        g.drawString("시간(time): " + timer.getFormattedElapsedTime(), 10, 60);

    }
}
    private void resetCurrentStage() {
        map.resetCurrentStage();
        moveCounter.reset();
        timer.reset();
        gameAreaPanel.repaint();
       requestFocusInWindow();
    }

    private class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            boolean moved = false;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    moved = map.tryMovePlayer(0, -1);
                    break;
                case KeyEvent.VK_DOWN:
                    moved = map.tryMovePlayer(0, 1);
                    break;
                case KeyEvent.VK_LEFT:
                    moved = map.tryMovePlayer(-1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    moved = map.tryMovePlayer(1, 0);
                    break;
            }

            if (moved) {
                moveCounter.increment();
                gameAreaPanel.repaint();

                if (map.isStageCleared()) {
                    int currentStage = map.getCurrentStage();
                    long elapsed = timer.getElapsedSeconds();
                    int moveCount = moveCounter.getCount();

                    records[currentStage] = new StageRecord(currentStage + 1, moveCount, elapsed);

                    timer.reset();
                    moveCounter.reset();

                    if (currentStage == 2) {
                        showResultsWindow();
                    } else {
                        map.nextStage();
                        gameAreaPanel.repaint();
                    }
                }
            }
        }
    }
    

    private void showResultsWindow() {
        JFrame resultFrame = new JFrame("게임 결과");
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setSize(400, 400);
        resultFrame.setLocationRelativeTo(this);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        String resultText = "=== 게임 결과 ===\n\n";
        resultText += "플레이어: " + PlayerName.getPlayerName() + "\n\n";

        for (StageRecord r : records) {
            if (r != null) {
                resultText += String.format("Stage %d - 스텝(step): %d, 시간(time): %s\n",
                    r.getStageNumber(), r.getMoveCount(), r.getFormattedElapsedTime());
            }
        }

        resultArea.setText(resultText);
        resultFrame.add(resultArea);
        resultFrame.setVisible(true);
    }

    public static void launchGame() {
        new GamePanel();
    }

    public static void main(String[] args) {
        launchGame();
    }
}