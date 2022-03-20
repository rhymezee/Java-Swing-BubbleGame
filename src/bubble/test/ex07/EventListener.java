package bubble.test.ex07;

public class EventListener {
	
	/*
	 * 이벤트 리스너 원리
	 * 
	 * 리스너는 키보드를 지켜보고 있다. (이벤트 리스너 등록 = addKeyListener())
	 * 사용자가 왼쪽 버튼을 클릭하면 이벤트 루프에 등록된다.
	 * 이벤트 핸들러를 호출해서 Stack 공간(메서드 내부)의 코드를 실행한다.
	 * 만약에 이벤트 핸들러가 만들어져 있지 않다면, Stack 공간이 없어서 실행할 코드가 없다.
	 * 버블버블 코드로 치면, keyPressed(KeyEvent e) 메서드 내부가 Stack 공간이 된다.
	 * 결국, keyPressed 메서드가 이벤트 핸들러가 된다.
	 * 
	 * 이벤트 루프는 먼저 들어온 것부터 처리하기 때문에 큐라고 보면 된다.
	 * 개발자가 만들어줘야 할 것은 이벤트 리스너와 이벤트 핸들러이다. (이벤트 루프는 이미 만들어져 있다.)
	 * 
	 */

}