package term_;

import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.Image;

public class GameMap {
	
	private int totalStages = 3; // 총 스테이지 수
    private int TILE_SIZE = 40; // 타일 크기
    private int ROWS = 13; // 행 개수
    private int COLS = 13; // 열 개수
    private int currentStage = 0; // 현재 스테이지
    

    // 게임 맵 데이터 (2차원 배열 배열 - 스테이지별 맵)
    // 0=빈칸, 1=벽, 2=블록, 3=골, 4=플레이어, 5=하늘색 벽
    private int[][] stage1 ={ {5,5,5,5,5,5,5,5,5,5,5,5,5},
	           {5,5,5,5,5,5,5,5,5,5,5,5,5},
	           {5,5,5,5,1,1,1,5,5,5,5,5,5},
	           {5,5,5,5,1,3,1,5,5,5,5,5,5},
	           {5,5,5,5,1,0,1,1,1,1,5,5,5},
	           {5,5,1,1,1,2,0,2,3,1,5,5,5},
	           {5,5,1,3,0,2,4,1,1,1,5,5,5},
	           {5,5,1,1,1,1,2,1,5,5,5,5,5},
	           {5,5,5,5,5,1,3,1,5,5,5,5,5},
	           {5,5,5,5,5,1,1,1,5,5,5,5,5},
	           {5,5,5,5,5,5,5,5,5,5,5,5,5},
	           {5,5,5,5,5,5,5,5,5,5,5,5,5},
	           {5,5,5,5,5,5,5,5,5,5,5,5,5}}; 
    private int[][] stage2 = {{5,5,5,5,5,5,5,5,5,5,5,5,5},
	           {5,5,5,5,5,5,5,5,5,5,5,5,5},
	           {5,5,1,1,1,1,1,5,5,5,5,5,5},
	           {5,5,1,4,0,0,1,5,5,5,5,5,5},
	           {5,5,1,0,2,2,1,5,1,1,1,5,5},
	           {5,5,1,0,2,0,1,5,1,3,1,5,5},
	           {5,5,1,1,1,0,1,1,1,3,1,5,5},
	           {5,5,5,1,1,0,0,0,0,3,1,5,5},
	           {5,5,5,1,0,0,0,1,0,0,1,5,5},
	           {5,5,5,1,0,0,0,1,1,1,1,5,5},
	           {5,5,5,1,1,1,1,1,5,5,5,5,5},
	           {5,5,5,5,5,5,5,5,5,5,5,5,5},
	           {5,5,5,5,5,5,5,5,5,5,5,5,5}}; 
    private int[][] stage3 = {{5,5,5,5,5,5,5,5,5,5,5,5,5},
	       	   {5,5,5,5,5,5,5,5,5,5,5,5,5},
	           {5,5,5,5,5,5,5,5,5,5,5,5,5},
	           {5,5,1,1,1,1,1,1,1,5,5,5,5},
	           {5,5,1,0,0,0,0,0,1,1,1,5,5},
	           {5,1,1,2,1,1,1,0,0,0,1,5,5},
	           {5,1,0,4,0,2,0,0,2,0,1,5,5},
	           {5,1,0,3,3,1,0,2,0,1,1,5,5},
	           {5,1,1,3,3,1,0,0,0,1,5,5,5},
	           {5,5,1,1,1,1,1,1,1,1,5,5,5},
	           {5,5,5,5,5,5,5,5,5,5,5,5,5},
	           {5,5,5,5,5,5,5,5,5,5,5,5,5},
	           {5,5,5,5,5,5,5,5,5,5,5,5,5}};

    private Player player;      // 플레이어 객체
    private Block[] blocks = new Block[10];  // 블록들 저장 배열
    private Goal[] goals = new Goal[10];// 목표 지점 저장 배열
    private Wall[] walls = new Wall[169];    // 벽 저장 배열
    private Sky[] skies = new Sky[169];

    private int blockCount=0, goalCount=0, wallCount=0, skyCount=0; // 각 개체의 개수

    private Image Tile;
    
    // 생성자: 게임맵 크기와 타일 크기를 받아 초기화
    public GameMap() {
        this.TILE_SIZE = 40;
        this.ROWS =  13;
        this.COLS = 13;
        
        Tile=new ImageIcon("Tile.png").getImage();
        
        loadCurrentMap();  // 처음 스테이지 로드
    }

    // 현재 스테이지 맵 데이터를 불러와서 객체 생성
    public void loadCurrentMap() {
        blockCount = 0;
        goalCount = 0;
        wallCount = 0;
        skyCount = 0;// 개체 수 초기화

        player = null;  // 플레이어 초기화

        int[][] map;
        switch (currentStage) {
            case 0: map = stage1; break;
            case 1: map = stage2; break;
            case 2: map = stage3; break;
            default: map = stage1; // 기본값 (예외 처리)
        }  // 현재 맵 배열

        // 맵 배열을 돌면서 각 위치의 값을 보고 객체 생성
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                switch(map[y][x]) {
                    case 1: // 벽 
                        walls[wallCount++] = new Wall(x, y);
                        break;
                    case 2: // 블록 
                        blocks[blockCount++] = new Block(x, y);
                        break;
                    case 3: // 목표 지점 
                        goals[goalCount++] = new Goal(x, y);
                        break;
                    case 4: // 플레이어 
                        player = new Player(x, y);
                        break;
                    case 5: // 하늘색 벽
                        skies[skyCount++] = new Sky(x, y);
                        break;
                }
            }
        }
    }

    // 화면에 모든 객체를 그리는 메서드
    public void paintComponent(Graphics g) {
        for (int x = 0; x < ROWS; x++) {
        	for(int y=0;y<COLS;y++) {
        		g.drawImage(Tile, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
        	}
        }
        for (int i=0;i<wallCount;i++) {
        	walls[i].draw(g, TILE_SIZE);
        }
        for (int i = 0; i < skyCount; i++) {
                skies[i].draw(g, TILE_SIZE);
        }
        for (int i = 0; i < goalCount; i++) {
            goals[i].draw(g, TILE_SIZE);
        }
        for (int i = 0; i < blockCount; i++) {
            blocks[i].draw(g, TILE_SIZE);
        }
        if (player != null) {
            player.draw(g, TILE_SIZE);
        }
        
    }

    // 플레이어를 dx, dy만큼 움직이려고 시도하는 메서드
    // 이동 성공 시 true, 실패 시 false 반환
    public boolean tryMovePlayer(int dx, int dy) {
        if (player == null) return false;

        int nextX = player.getX() + dx;  // 다음 위치 X
        int nextY = player.getY() + dy;  // 다음 위치 Y

        // 벽이 있으면 이동 불가
        if (isWall(nextX, nextY)) return false;

        // 다음 위치에 블록이 있는지 확인
        int blockIndex = getBlockIndexAt(nextX, nextY);
        if (blockIndex != -1) {
            // 블록을 밀 위치 계산
            int bx = nextX + dx;
            int by = nextY + dy;

            // 블록을 밀 위치에 벽이나 블록이 있으면 이동 불가
            if (isWall(bx, by) || getBlockIndexAt(bx, by) != -1) return false;

            // 블록을 밀기 (이동)
            blocks[blockIndex].move(dx, dy);
            // 플레이어도 이동
            player.move(dx, dy);
            // 목표 달성 여부 확인
            checkGoalCompletion();
            return true;
        } else {
            // 블록이 없으면 플레이어만 이동
            player.move(dx, dy);
            return true;
        }
    }

    // 해당 위치에 벽이 있는지 확인하는 메서드
    private boolean isWall(int x, int y) {
        for (int i = 0; i < wallCount; i++) {
            if (walls[i].getX() == x && walls[i].getY() == y) {
                return true;
            }
        }
        return false;
    }

    // 해당 위치에 블록이 있는지 확인하고 인덱스 반환, 없으면 -1 반환
    private int getBlockIndexAt(int x, int y) {
        for (int i = 0; i < blockCount; i++) {
            if (blocks[i].getX() == x && blocks[i].getY() == y) {
                return i;
            }
        }
        return -1;
    }

    // 목표 지점 위에 블록이 모두 올라갔는지 검사해서
    // 목표 지점과 블록을 제거하는 메서드
    private void checkGoalCompletion() {
        for (int i = 0; i < goalCount; i++) {
            boolean removedGoal = false;
            for (int j = 0; j < blockCount; j++) {
                if (goals[i].getX() == blocks[j].getX() &&
                    goals[i].getY() == blocks[j].getY()) {
                    // 목표 지점과 블록 제거
                    removeGoal(i);
                    removeBlock(j);
                    removedGoal = true;
                    break;  // 더 이상 검사하지 않음
                }
            }
            if (removedGoal) {
                i--; // 배열이 줄어들었으니 인덱스 다시 맞춤
            }
        }
    }

    // 목표 지점 배열에서 index 위치 제거
    private void removeGoal(int index) {
        for (int i = index; i < goalCount - 1; i++) {
            goals[i] = goals[i + 1]; // 다음 목표 지점을 현재 위치로 이동
        }
        goals[--goalCount] = null; // 마지막 요소 제거
    }


    // 블록 배열에서 index 위치 제거
    private void removeBlock(int index) {
    	for (int i = index; i < blockCount - 1; i++) {
            blocks[i] = blocks[i + 1]; // 다음 목표 지점을 현재 위치로 이동
        }
        blocks[--blockCount] = null;
    }

    // 스테이지가 클리어됐는지(모든 목표 지점이 없어진 상태) 반환
    public boolean isStageCleared() {
        return goalCount == 0;
    }
    
    public void resetCurrentStage() {
    	loadCurrentMap();
    	
    }

    // 다음 스테이지로 이동하는 메서드
    public void nextStage() {
        currentStage++;
        if (currentStage >= totalStages) {
            System.exit(0);
        }
        loadCurrentMap();
    }

    // 현재 플레이어 객체 반환
    public Player getPlayer() {
        return this.player;
    }

    // 현재 스테이지 번호 반환
    public int getCurrentStage() {
        return currentStage;
    }
    public boolean isLastStage() {
        return currentStage == totalStages;
        
    }
}
