package term_;


public class StageRecord {
	private int stageNumber;     // 스테이지 번호 (예: 1, 2, 3 ...)
    private int moveCount;       // 해당 스테이지에서 플레이어가 움직인 횟수
    private long elapsedSeconds; // 걸린 시간 (초 단위)

    // 생성자: 스테이지 번호, 이동 횟수, 시간 받아서 저장
    public StageRecord(int _stageNumber, int _moveCount, long _elapsedSeconds) {
        this.stageNumber = _stageNumber;
        this.moveCount = _moveCount;
        this.elapsedSeconds = _elapsedSeconds;
    }

    // 스테이지 번호 가져오기
    public int getStageNumber() {
        return stageNumber;
    }

    // 이동 횟수 가져오기
    public int getMoveCount() {
        return moveCount;
    }

    // 걸린 시간 가져오기(초)
    public long getElapsedSeconds() {
        return elapsedSeconds;
    }

    // 걸린 시간을 "분:초" 문자열로 바꿔서 반환하는 함수
    public String getFormattedElapsedTime() {
        long minutes = elapsedSeconds / 60;             // 분 계산
        long seconds = elapsedSeconds % 60;             // 초 계산
        return String.format("%02d:%02d", minutes, seconds);  // 예: 01:05
    }
}
