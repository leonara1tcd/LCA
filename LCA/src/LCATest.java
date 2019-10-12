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
}