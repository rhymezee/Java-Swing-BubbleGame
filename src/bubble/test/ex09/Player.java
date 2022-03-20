package bubble.test.ex09;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends JLabel implements Moveable {

	private static final long serialVersionUID = 1L;
	
	// 위치 상태
	private int x;
	private int y;
	
	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down; // boolean 타입은 getter, setter가 get, set 형식이 아닌 is로 붙는다.
	
	// 속도 상태
	private final int SPEED = 4; // 상수는 대문자로 적는다.
	private final int JUMPSPEED = 2;
	
	 // 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	
	private ImageIcon playerR, playerL;
	
	public Player() {
		initObject();
		initSetting();
		initBackgroundPlayerService();
	}
	
	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}
	
	private void initSetting() {
		x = 80;
		y = 535;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		leftWallCrash = false;
		rightWallCrash = false;
		
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	@Override
	public void left() {
		// System.out.println("left");
		left = true;
		new Thread(() -> {
			while(left) {
				setIcon(playerL);
				x = x - SPEED;
				setLocation(x, y);
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
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
		
		/*
		 * Thread() 여기 타겟이 들어와야 하는데, Thread 타고 들어가보면 Runnable 타입의 target 매개변수가 보이고,
		 * 이 Runnable을 다시 타고 들어가보면, run이라는 추상메서드를 가지고 있는 인터페이스인걸 확인할 수 있다.
		 * 그럼 이 run이라는 메서드를 구현한 구현체가 필요한데, 매개변수에 익명클래스를 넣어준다.
		 * 요즘엔 잘 안쓰는 방식으로 람다식으로 구현.
		 */
		
		// System.out.println("right");
		right = true;
		new Thread(() -> {
			while(right) {
				setIcon(playerR);
				x = x + SPEED;
				setLocation(x, y);
				
				try {
					Thread.sleep(10); // 0.01초
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void up() { // left + up, right + up 2가지가 가능하다.
		// System.out.println("up");
		up = true;
		
		new Thread(() -> {
			for (int i=0; i<130/JUMPSPEED; i++) {
				y = y - JUMPSPEED;
				setLocation(x, y);
				
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			up = false; // left, right는 keyReleased에서 해제 이벤트를 줬다.
			down();
		}).start();
	}

	@Override
	public void down() {
		// System.out.println("down");
		down = true;
		
		new Thread(() -> {
			while(down) {
				y = y + JUMPSPEED;
				setLocation(x, y);
				
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			down = false;
		}).start();
	}
	
	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayerService(this)).start();
	}

}