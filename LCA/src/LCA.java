public class LCA <Key extends Comparable<Key>, Value>
{
	Node root;

	class Node
	{
		private Key key;
		private Value val;
		private Node left, right;
		private int N;

		public Node(Key key, Value val, int N)
		{
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	class DAG
	{
		private Node root;
		public DAG(Node root)
		{
			this.root = root;
		}
	}

	
}