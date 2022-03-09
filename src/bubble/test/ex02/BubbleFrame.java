package bubble.test.ex02;

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

	public static void main(String[] args) {
		
		new BubbleFrame();

	}

}