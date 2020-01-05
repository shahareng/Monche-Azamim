
// n vertices, m edges
class Vertex2 
{
	public int name;
	public Edge2[] edges;
	public double dist;
	public int previous;
	public boolean visited;

	public Vertex2(int name, double dist) 
	{
		this.name = name; 
		this.dist = dist;
		previous = -1;
		visited = false;
	}

	public Vertex2(Vertex2 v) 
	{
		this.name = v.name; 
		this.dist = v.dist;
		previous = v.previous;
		visited = v.visited;

	}
	public String toString() {
		return "" + name; 
	}
}
class Edge2{
	public  int vert;
	public double weight;
	public Edge2(int v, double w){ 
		vert = v; 
		weight = w; 
	}
}

public class Dijkstra2 {
	Vertex2[] vertices;
	int source;
	public Dijkstra2(Vertex2[] vs, int source)
	{
		this.source = source;
		vertices = new   Vertex2[vs.length];
		for (int i=0; i<vs.length; i++){
			vertices[i] = vs[i];
		}
	}

	public void computePaths(){
		Vertex2 s = vertices[source];
		s.dist = 0.;
		HeapMin Q = new HeapMin();
		Q.minHeapInsert(s);
		//O(nlogn)
		for (int i=1; i<vertices.length; i++){//O(n)
			Q.minHeapInsert(vertices[i]);//O(logn)
		}
		//O(nlogn) + O(mlogn) = O((m+n)logn)
		while (!Q.isEmpty()) {//O(m)
			Vertex2 u = Q.heapExtractMin();//O(logn)
			// Visit each edge exiting u
			for (Edge2 e : u.edges){
				Vertex2 v = vertices[e.vert];
				if (!v.visited){
					double distU = u.dist + e.weight;
					if (distU < v.dist) {//relaxation
						v.dist = distU ;
						v.previous = vertices[u.name].name;
						Q.heapDecreaseKey(v);//O(logn)
					}
				}
			}
			u.visited = true;
		}
	}

	public void printWeights(){
		System.out.print("weights: ");
		for (Vertex2 v : vertices) {
			System.out.printf(v.dist + ", ");
		}
		System.out.println();
	}
	public String getPath(int v)
	{
		int t = v;
		String ans = t + "";
		while(t != source)
		{
			t = vertices[t].previous;
			ans = t + "->" + ans;
		}
		return ans;
	}
	public void printPaths()
	{
		for (Vertex2 v : vertices)
		{
			System.out.println("price of " + v.name+" = " + v.dist + ", path: " +  getPath(v.name));
		}
		System.out.println();
	}
}
