package util;

public class Couple<T1, T2> {
	
	T1 first;
	T2 second;
	
	public Couple(T1 a, T2 b) {
		first = a;
		second = b;
	}
	
	public boolean equals(Couple<T1, T2> autre) {
		return (autre.getFirst().equals(this.getFirst()) && autre.getSecond().equals(this.getSecond()));
	}

	public T1 getFirst() {
		return first;
	}

	public void setFirst(T1 first) {
		this.first = first;
	}

	public T2 getSecond() {
		return second;
	}

	public void setSecond(T2 second) {
		this.second = second;
	}
	
}
