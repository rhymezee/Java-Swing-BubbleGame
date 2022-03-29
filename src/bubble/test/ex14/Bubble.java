package bubble.test.ex14;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

	private static final long serialVersionUID = 1L;
	
	// 의존성 컴포지션
	private Player player;
	private BackgroundBubbleService backgroundBubbleService;
	private BubbleFrame mContext; // my context
	
	// 위치 상태
	private int x;
	private int y;
	
	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	
	// 적군을 맞춘 상태
	private int state; // 0(물방울), 1(적을 가둔 물방울)
	
	private ImageIcon bubble; // 그냥 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울이 터진 상태
	
	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		initObject();
		initSetting();
		// initThread();
	}
	
	private void initObject() {
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");
		backgroundBubbleService = new BackgroundBubbleService(this);
	}
	
	private void initSetting() {
		left = false;
		right = false;
		up = false;
		
		x = player.getX();
		y = player.getY();
		
		setIcon(bubble);
		setSize(50, 50);
		
		state = 0; // 물방울 상태
	}
	
	/*
	private void initThread() {
		 // 플레이어의 쓰레드는 대각선으로 올라가는 동작을 위해 right와 up 각각 스레드가 필요하지만, 버블은 쓰레드가 하나만 필요하다.
		new Thread(() -> {
			if (player.getPlayerWay() == PlayerWay.LEFT) {
				left();
			}
			else {
				right();
			}
		}).start();
	}
	*/

	@Override
	public void left() {
		left = true;
		for (int i=0; i<400; i++) {
			x--;
			setLocation(x, y);
			
			if (backgroundBubbleService.leftWall()) {
				left = false;
				break;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		for (int i=0; i<400; i++) {
			x++;
			setLocation(x, y);
			
			if (backgroundBubbleService.rightWall()) {
				right = false;
				break;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void up() {
		up = true;
		while (up) {
			y--;
			setLocation(x, y);
			
			if (backgroundBubbleService.topWall()) {
				up = false;
				break;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		clearBubble();
	}
	
	private void clearBubble() {
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(500);
			/*
			 * main을 가진 클래스는 모든 객체(heap)의 정보를 가지고 있다.
			 * 즉, BubbleFrame은 player, bubble의 주소를 알고 있다.
			 * 
			 * main을 가진 클래스 -> Context(모든 정보)를 가지고 있다.
			 */
			mContext.remove(this); // BubbleFrame의 bubble이 메모리에서 소멸된다.
			mContext.repaint(); // BubbleFrame에서 메모리에 없는 것들은 제외하고, 있는 것들만 다시 그린다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}