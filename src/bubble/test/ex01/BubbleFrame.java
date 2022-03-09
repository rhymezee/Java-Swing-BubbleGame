package bubble.test.ex01;

import javax.swing.JFrame;

// 1. JFrame을 상속받으면 윈도우 창이 된다.
// 2. 내부에 JPanel을 가지고 있다.
public class BubbleFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public BubbleFrame() {
		setSize(1000, 640);
		getContentPane().setLayout(null); // layout을 border에서 absolute로 바꿈.
		setVisible(true); // JPanel에 그림을 그릴 수 있다.
	}

	public static void main(String[] args) {
		
		new BubbleFrame();

	}

}