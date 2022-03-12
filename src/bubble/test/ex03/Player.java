package bubble.test.ex03;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

	private static final long serialVersionUID = 1L;
	
	// 위치 상태
	private int x;
	private int y;
	
	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private ImageIcon playerR, playerL;
	
	public Player() {
		initObject();
		initSetting();
	}
	
	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}
	
	private void initSetting() {
		x = 55;
		y = 535;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	@Override
	public void left() {
		setIcon(playerL);
		x = x - 10;
		setLocation(x, y);
	}

	@Override
	public void right() {
		/*
		 * Thread.sleep(2000); = 2초 멈췄다가 이동시킴.
		 * 예를 들어 오른쪽 버튼을 5번 누르면 10초 후에 오른쪽으로 5칸 순간이동 한다.
		 * 그림 변경 시점은 이벤트 루프의 모든 임무(task)가 완료되어야 repaint가 된다.
		 * 
		 * 메인 스레드는 key를 이벤트 루프에게 전달한다.
		 * 메인 스레드만 있으면 동시에 2개의 key를 누르면 하나의 key만 전달할 수 있다.
		 */
		
		setIcon(playerR);
		x = x + 10;
		setLocation(x, y);
	}

	@Override
	public void up() {
		
	}

	@Override
	public void down() {
		
	}

}