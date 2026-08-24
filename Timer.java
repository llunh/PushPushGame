package term_;

public class Timer {
	private long startTime;   // 타이머가 시작된 시간 (밀리초)
	 private long endTime;     // 타이머가 멈춘 시간 (밀리초)
	 private boolean running;  // 타이머가 작동 중인지 여부

	 // 타이머 시작
	 public void start() {
	     startTime = System.currentTimeMillis(); // 현재 시간을 시작 시간으로 저장
	     running = true;                         // 타이머 작동 중으로 설정
	 }

	 // 타이머 멈춤
	 public void stop() {
	     endTime = System.currentTimeMillis();   // 현재 시간을 멈춘 시간으로 저장
	     running = false;                        // 타이머 정지 상태로 설정
	 }

	 // 타이머 리셋 (시간 초기화)
	 public void reset() {
	     startTime = System.currentTimeMillis(); // 시작 시간을 현재 시간으로 초기화
	     if (!running) {
	         endTime = startTime;               // 정지 상태일 경우 멈춘 시간도 동일하게 설정
	     }
	 }

	 // 경과된 시간을 밀리초(ms) 단위로 가져오기
	 public long getElapsedMillis() {
	     if (running) {
	         return System.currentTimeMillis() - startTime; // 현재 시간에서 시작 시간 차이
	     } else {
	         return endTime - startTime;                    // 멈춘 시간에서 시작 시간 차이
	     }
	 }

	 // 경과된 시간을 초 단위로 반환
	 public int getElapsedSeconds() {
	     return (int) (getElapsedMillis() / 1000); // 밀리초를 초로 변환
	 }

	 // 경과된 시간을 "분:초" 형식의 문자열로 반환
	 public String getFormattedElapsedTime() {
	     long totalSeconds = getElapsedMillis() / 1000;
	     long minutes = totalSeconds / 60;
	     long seconds = totalSeconds % 60;
	     return String.format("%02d:%02d", minutes, seconds); // 예: 01:05 (1분 5초)
	 }
}

