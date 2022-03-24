package bubble.test.ex13;

public interface Moveable {
	
	public abstract void left();
	public abstract void right();
	public abstract void up();
	// public abstract void down();
	
	// 어댑터 패턴의 한계(다중 상속 불가)로 default 사용
	// default를 사용하면 인터페이스도 몸체가 있는 메서드를 만들 수 있다.
	default public void down() {}
	
}