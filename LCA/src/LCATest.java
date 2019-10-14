import static org.junit.Assert.*;
import org.junit.Test;

public class LCATest
{
	
	@Test
	public void testLCA() {
		LCA<Integer, Integer> lca = new LCA<Integer, Integer>();

		assertSame("Testing LCA for null root", null, lca.lowestCommonAncestor(lca.root, 1, 2));

		lca.put(8, 8);   //        _8_
		lca.put(7, 7);   //      /     \
		lca.put(3, 3);   //    _3_      7
		lca.put(1, 1);   //  /     \
		lca.put(2, 2);   // 1       4
		lca.put(4, 4);   //  \     /
		lca.put(6, 6);   //   2   6
		lca.put(5, 5);   //        \
						//			5
		assertSame("Testing for LCA of two nodes", 3, lca.lowestCommonAncestor(lca.root, 2,4));
		assertSame("Testing for LCA where LCA itself is one of the nodes", 8, lca.lowestCommonAncestor(lca.root, 7,8));
		assertSame("Testing for LCA where LCA itself is one of the nodes", 8, lca.lowestCommonAncestor(lca.root, 3,8));
	}

	
	
	@Test
	public void testDelete()
	{
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
		LCA.delete(1);
		assertEquals("Deleting from empty tree", "()", LCA.printKeysInOrder());

		LCA.put(7, 7);   //        _7_
		LCA.put(8, 8);   //      /     \
		LCA.put(3, 3);   //    _3_      8
		LCA.put(1, 1);   //  /     \
		LCA.put(2, 2);   // 1       6
		LCA.put(6, 6);   //  \     /
		LCA.put(4, 4);   //   2   4
		LCA.put(5, 5);   //        \
						//			5

		assertEquals("Checking order of constructed tree",
				"(((()1(()2()))3((()4(()5()))6()))7(()8()))", LCA.printKeysInOrder());
		
		LCA.delete(9);
		assertEquals("Deleting non-existent key",
				"(((()1(()2()))3((()4(()5()))6()))7(()8()))", LCA.printKeysInOrder());

		LCA.delete(8);
		assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", LCA.printKeysInOrder());

		LCA.delete(6);
		assertEquals("Deleting node with single child",
				"(((()1(()2()))3(()4(()5())))7())", LCA.printKeysInOrder());

		LCA.delete(3);
		assertEquals("Deleting node with two children",
				"(((()1())2(()4(()5())))7())", LCA.printKeysInOrder());
		
	}


	
	@Test
	public void testPut() {
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
		LCA.put(1, null);
		LCA.put(11, 1);
		LCA.put(12,2);
		LCA.put(12, 12);

		assertEquals("Putting nodes", "(()11(()12()))", LCA.printKeysInOrder());
	}
	

	@Test
	public void testEmpty()
	{
		DAG lca = new DAG(6);
		assertEquals("Testing LCA is -1", -1, lca.findLCA(1, 3));
	}
	
	
	
	@Test
	public void testForNoCommonAncestors()
	{
		DAG lca = new DAG(11);
		
		lca.addEdge(0, 1);
		lca.addEdge(0, 2);
		lca.addEdge(1, 2);
		lca.addEdge(2, 3);
		lca.addEdge(3, 4);
		lca.addEdge(1, 5);
		lca.addEdge(3, 5);

		assertEquals("Finding LCA when there is no LCA", 0, lca.findLCA(3, 1));
		assertEquals("", 1, lca.findLCA(1, 2));
		assertEquals("", 3, lca.findLCA(4, 5));

		assertEquals("Finding LCA when one node doesnt exist", -1, lca.findLCA(6, 3));
	}

	@Test
	public void testForDAG()
	{
		DAG dag = new DAG(10);
		dag.addEdge(1, 2);
		dag.addEdge(1, 3);
		dag.addEdge(3, 4);
		dag.addEdge(4, 5);
		dag.addEdge(4, 6);

		assertEquals("", 5, dag.E());
		assertEquals("", 10, dag.V());
		String ans = "[5, 6]";
		assertEquals("",ans, dag.adj(4).toString());
	}

	
	@Test
	public void testAddEdge()
	{
		DAG test = new DAG(10);
		test.addEdge(0, 2);

		test.addEdge(-2, -5);
		assertEquals("Testing edge count is 1", 1, test.E());

		test.addEdge(1, 3);
		assertEquals("Testing edge count is 2", 2, test.E());
	}

	@Test(expected=Exception.class)
	public void exceptionTest()
	{
		//Can't make a directed graph with less than 0 vertices
		DAG test = new DAG(-1);
	}

	
	@Test
	public void testsForCycle()
	{
		DAG test = new DAG(20);
		test.addEdge(0, 1);
		test.addEdge(1, 2);
		test.addEdge(2, 0);

		
		test.findCycle(0);

		//Has a cycle from 0 - 1 - 2 - 0, should return true.
		assertTrue(test.hasCycle());

		DAG test2 = new DAG(20);
		test2.addEdge(0, 1);
		test2.addEdge(1, 3);
		test2.addEdge(2, 4);
		test2.findCycle(0);
		
		//No Cycle exists, should return false
		assertFalse(test2.hasCycle());
	}


}