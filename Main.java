package term_; // 패키지 선언 (현재 클래스가 속한 패키지)

import javax.swing.*;  // GUI 관련 라이브러리
import java.awt.*;  // 그래픽 처리를 위한 라이브러리
import java.awt.event.*;  // 이벤트 처리를 위한 라이브러리

// JFrame을 상속받아 GUI 창을 생성하는 클래스
public class Main extends JFrame {

    // 생성자: 프로그램 실행 시 GUI 창을 설정하고 초기화
    public Main() {
        setTitle("PushPush Mario"); // 창 제목 설정
        setSize(530, 600); // 창 크기 설정 (너비, 높이)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기 버튼 클릭 시 프로그램 종료
        setLocationRelativeTo(null); //창을 화면 중앙에 배치 *************추가검색
        
        // 커스텀 패널 생성 및 프레임에 추가
        MyPanel panel = new MyPanel(this); // this: Main 객체 전달
        add(panel);

        setVisible(true);  //프레임을 화면에 표시
    }
        
// JPanel을 상속받아 paintComponent를 활용한 배경 이미지 처리 클래스
class MyPanel extends JPanel {
    private ImageIcon icon = new ImageIcon("start.png");
    private Image img = icon.getImage();
    
    public MyPanel(Main frame){
       setLayout(new FlowLayout(FlowLayout.CENTER, 0, 400)); // 가운데 정렬, 위쪽 여백 400
       // JButton: 버튼 컴포넌트 생성
        JButton startButton = new JButton("START!");
        startButton.setBackground(Color.RED); // 버튼 배경색 설정
        startButton.setForeground(Color.WHITE); // 버튼 글씨 색 설정
        startButton.setFont(new Font("Arial", Font.BOLD, 20)); // 버튼 글씨 폰트 설정
        startButton.setPreferredSize(new Dimension(200, 50));  // 크기 지정
        
        // 버튼 클릭 이벤트 처리 (익명 클래스로 Action 리스너 작성 활용)
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               PlayerName.showNameInputWindow(frame);  //어떤 기능을 수행하는 메서드 호출
            }
        });
        add(startButton);  //버튼을 프레임에 추가 -> 없으면 버튼이 프레임에 없음(보여줄게 없음)
    }

    protected void paintComponent(Graphics g) {

        // 배경 이미지 그리기
        super.paintComponent(g);  //패널 내에 이전에 그려진 잔상을 지우기 위해 호출(643p)
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this); //이미지를 꽉차게 그리기

        // PushPush Mario 뒤에 흰색 사각형 추가
        g.setColor(Color.WHITE); // 색을 흰색으로 설정
        g.fillRect(123, 290, 270, 60); // (x, y, 너비, 높이)

        // 제목 텍스트 그리기
        g.setColor(Color.BLACK); // 글씨 색상을 검정으로 설정
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("PushPush Mario", 143, 330); // (x, y) 좌표에 문자열 그리기
    }
}
// 프로그램 실행을 위한 main 메서드
public static void main(String[] args) {
    new Main(); // Main 객체 생성 및 GUI 실행
}
}
