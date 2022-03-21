package bubble.test.ex11;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel backgroundMap;
	private Player player;
	
	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true);
	}
	
	private void initObject() {
		/*
		backgroundMap = new JLabel("안녕"); // 이 상태로는 안보인다.
		add(backgroundMap); // 추가해주면 보인다. (JFrame에 JLabel이 그려진다.), 크기가 없어서 아직 안보인다.
		backgroundMap.setSize(100, 100); // 크기
		backgroundMap.setLocation(300, 300); // 위치
		*/
		
		/*
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		backgroundMap.setSize(1000, 600);
		add(backgroundMap); // JLabel을 add를 하게 되면 JFrame 안에 JPanel(getContentPane()) 안에 만들어진다.
		*/
		
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap); // JPanel을 JLabel로 바꿈. (굳이 3계층으로 만들 필요가 없어서)
		player = new Player();
		add(player);
	}
	
	private void initSetting() {
		setSize(1000, 640);
		setLayout(null); // absolute로 바뀜.
		setLocationRelativeTo(null); // JFrame이 가운데 실행 됨.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame을 끄면 JVM도 같이 종료 됨.
	}
	
	private void initListener() {
		/*
		 * addKeyListener를 타고 들어가면 매개변수로 KeyListener 타입이 와야하고,
		 * 다시 KeyListener를 타고 들어가면 인터페이스로 추상메서드를 3개 들고 있다.
		 * 여기서 keyPressed 메서드만 쓰고 싶을 때 adapter 패턴을 사용한다.
		 * KeyAdapter를 사용하면 이미 얘가 KeyListener의 구현체로 3개 메서드를 구현해놔서 오류가 안난다.
		 * 그래서 adapter 패턴을 사용해서 구현하고 싶은 메서드만 사용할 수 있다.
		 */
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) { // 키보드 클릭 이벤트 핸들러
				// System.out.println(e.getKeyCode());
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT : // 37
						if (!player.isLeft() && !player.isLeftWallCrash()) {
							player.left();
						}
						break;
					case KeyEvent.VK_UP : // 38
						if (!player.isUp() && !player.isDown()) {
							player.up();
						}
						break;
					case KeyEvent.VK_RIGHT : // 39
						if (!player.isRight() && !player.isRightWallCrash()) {
							player.right();
						}
						break;
					case KeyEvent.VK_SPACE : 
						Bubble bubble = new Bubble(player);
						add(bubble);
						break;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) { // 키보드 해제 이벤트 핸들러
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						player.setLeft(false);
						break;
					case KeyEvent.VK_RIGHT:
						player.setRight(false);
						break;
				}
			}
		});
	}

	public static void main(String[] args) {
		
		new BubbleFrame();

	}

}