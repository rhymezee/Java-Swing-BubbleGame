package bubble.test.ex08;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class BackgroundPlayerService implements Runnable {

	/*
	 * 백그라운드에서 계속 관찰
	 * 메인스레드는 바쁘다 -> 키보드 이벤트를 처리하기 바쁘다.
	 * 그래서 새로운 스레드가 되어야 한다.
	 */
	
	private BufferedImage image; // 버퍼로 읽어야 테스트를 할 수 있다.
	private Player player;
	
	public BackgroundPlayerService(Player player) { // 컴포지션
		this.player = player;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		// 플레이어의 위치에 따른 색상 확인
		while(true) {
			Color leftColor = new Color(image.getRGB(player.getX()-10, player.getY()+25));
			Color rightColor = new Color(image.getRGB(player.getX()+50+15, player.getY()+25));
			
			// -2가 나온다는 뜻은 점프했을 때 걸리는 곳이 없다. (흰색 바탕에서 점프)
			int bottomColor = image.getRGB(player.getX()+10, player.getY()+50+5) // -1
										+ image.getRGB(player.getX()+50-10, player.getY()+50+5); // -1

			// System.out.println("leftColor 색상 : " + leftColor);
			// System.out.println("rightColor 색상 : " + rightColor);
			
			// 외벽 충돌 확인
			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				// System.out.println("왼쪽 벽 충돌");
				player.setLeftWallCrash(true);
				player.setLeft(false);
			}
			else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				// System.out.println("오른쪽 벽 충돌");
				player.setRightWallCrash(true);
				player.setRight(false);
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
			
			// 바닥 충돌 확인
			if (bottomColor != -2) {
				// System.out.println("bottomColor 색상 : " + bottomColor);
				// System.out.println("바닥에 충돌함");
				player.setDown(false);
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}