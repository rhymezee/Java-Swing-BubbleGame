package bubble.test.ex12;

// 어댑터 -> 걸러내는 역할
public abstract class MoveAdapter implements Moveable {

	@Override
	public void down() {}
	
	/*
	 * 이 상태에서 Bubble 클래스가 MoveAdapter를 상속받아서 다운 메서드를 제외한 left, right, up만 사용할 수 있지만,
	 * 버블 클래스가 이미 JLabel을 상속하고 있다.
	 * 자바는 다중 상속이 안되므로, 이러한 어댑터 패턴으로는 사용할 수 없다.
	 * 그래서 default를 사용한다.
	 */

}