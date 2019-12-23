

public class TestDijkstra {
	public static double infinity = Double.POSITIVE_INFINITY;
	public static Vertex2[] initGraph1(){
		Vertex2 v0 = new Vertex2(0, infinity); 
		Vertex2 v1 = new Vertex2(1, infinity); 
		Vertex2 v2 = new Vertex2(2, infinity);
		Vertex2 v3 = new Vertex2(3, infinity);
		Vertex2 v4 = new Vertex2(4, infinity);
		Vertex2 v5 = new Vertex2(5, infinity);
		v0.edges = new Edge2[]{new Edge2(1,7), new Edge2(2,9), new Edge2(5,14)};
		v1.edges = new Edge2[]{new Edge2(0,7), new Edge2(2,10), new Edge2(3,115)};
		v2.edges = new Edge2[]{new Edge2(0,9), new Edge2(1, 10), new Edge2(3,11), new Edge2(5,2)};
		v3.edges = new Edge2[]{new Edge2(1,15), new Edge2(2,11), new Edge2(4, 6)};
		v4.edges = new Edge2[]{new Edge2(3,6), new Edge2(5,9)};
		v5.edges = new Edge2[]{new Edge2(4,9), new Edge2(2,2), new Edge2(0,14)};
		Vertex2[] vs = {v0,v1,v2,v3,v4,v5};
		return vs;
	}
	public static Vertex2[] initGraph2(){
		Vertex2 v0 = new Vertex2(0, infinity); 
		Vertex2 v1 = new Vertex2(1, infinity); 
		Vertex2 v2 = new Vertex2(2, infinity);
		Vertex2 v3 = new Vertex2(3, infinity);
		Vertex2 v4 = new Vertex2(4, infinity);
		v0.edges = new Edge2[]{new Edge2(1,10), new Edge2(4,5)};
		v1.edges = new Edge2[]{new Edge2(2,1), new Edge2(4,2)};
		v2.edges = new Edge2[]{new Edge2(3,4)};
		v3.edges = new Edge2[]{new Edge2(2,6), new Edge2(0,7)};
		v4.edges = new Edge2[]{new Edge2(3,2), new Edge2(2,9), new Edge2(1,3)};
		Vertex2[] vs = {v0,v1,v2,v3,v4};
		return vs;	
	}
	public static void main(String[] args) {
		System.out.println("init 1");
		Dijkstra2 ds = new Dijkstra2(initGraph1(), 0);
		ds.computePaths();
		ds.printWeights();
		ds.printPaths();
		//////
		System.out.println("init 2");
		Dijkstra2 ds2 = new Dijkstra2(initGraph2(), 0);
		ds2.computePaths();
		ds2.printWeights();
		ds2.printPaths();

	}
}
/*
OUTPUT inint1
weights: 0.0, 7.0, 9.0, 20.0, 20.0, 11.0, 
price of 0 = 0.0, path: 0
price of 1 = 7.0, path: 0->1
price of 2 = 9.0, path: 0->2
price of 3 = 20.0, path: 0->2->3
price of 4 = 20.0, path: 0->2->5->4
price of 5 = 11.0, path: 0->2->5

	OUTPUT inint2
weights: 0.0, 8.0, 9.0, 7.0, 5.0, 
price of 0 = 0.0, path: 0
price of 1 = 8.0, path: 0->4->1
price of 2 = 9.0, path: 0->4->1->2
price of 3 = 7.0, path: 0->4->3
price of 4 = 5.0, path: 0->4
*/
