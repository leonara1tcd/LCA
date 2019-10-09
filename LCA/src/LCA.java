public class LCA {

	Digraph digraph;

	public LCA(Digraph g) {
		this.digraph = g;
	}

	public boolean hasCycle() {
		DirectedCycle testDAG = new DirectedCycle(digraph);
		return testDAG.hasCycle();
	}

	public int lowestCommonAncestor(int a, int b) {
		if (hasCycle()) {
			return -1;
		} else if (digraph.V() == 0) {
			return -1;
		} else {
			return 0;
		}
	}

	public static void main(String[] rgs) {
		Digraph v = new Digraph(2);
		v.addEdge(0, 1);
	}
}