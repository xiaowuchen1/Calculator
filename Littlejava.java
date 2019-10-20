import java.util.Scanner;

public class Littlejava {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		int y = in.nextInt();
		int z = in.nextInt();
		MyVector a = new MyVector();
		MyVector b = new MyVector(x, y, z);
		MyVector xx = a.add(b);
		xx.display();
		MyVector yy = a.sub(b);
		yy.display();
		MyVector zz = a.cross(b);
		zz.display();
		int dd = a.dot(b);
		System.out.println(dd);

        int g = in.nextInt();
    	in.close();
	}

}

class MyVector {
	private int x;
	private int y;
	private int z;

	public MyVector() {
		this(9, 8, 7);
	}

	public MyVector(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// 向量加法
	public MyVector add(MyVector v) {
		MyVector temp = new MyVector();
		temp.x = this.x + v.x;
		temp.y = this.y + v.y;
		temp.z = this.z + v.z;
		return temp;
	}

	// 向量减法
	public MyVector sub(MyVector v) {
		MyVector temp = new MyVector();
		temp.x = this.x - v.x;
		temp.y = this.y - v.y;
		temp.z = this.z - v.z;
		return temp;
	}

	// 向量叉乘
	public MyVector cross(MyVector v) {
		MyVector temp = new MyVector();
		temp.x = this.y * v.z - this.z * v.y;
		temp.y = this.z * v.x - this.x * v.z;
		temp.z = this.x * v.y - this.y * v.x;
		return temp;
	}

	// 向量点乘
	public int dot(MyVector v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}

	public void display() {
		System.out.printf("(%d,%d,%d)\n", x, y, z);
	}
}