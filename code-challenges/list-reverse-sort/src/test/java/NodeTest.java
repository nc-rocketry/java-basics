import morsecode.lists.Node;
import morsecode.lists.NodeList;
import org.junit.Assert;
import org.junit.Test;

public class NodeTest {


    @Test
    public void testNodeList() {
        Node<Integer> node= new Node<>(5);
        node.add(3).add(4);
        node.add(1).add(2);
        Assert.assertEquals(5, node.get().intValue());
        node= node.next();
        Assert.assertEquals(3, node.get().intValue());
        node= node.next();
        Assert.assertEquals(4, node.get().intValue());
        node= node.next();
        Assert.assertEquals(1, node.get().intValue());
        node= node.next();
        Assert.assertEquals(2, node.get().intValue());

    }

    @Test
    public void testReverse() {

        NodeList<Integer> node= new NodeList<>();
        for (int i= 0; i <= 5; i++) {
            node.add(i);
        }

        Node<Integer> reverse= node.reverse().getHead();

        for (int i= 5; i >= 0; i--) {
            Assert.assertEquals(i, reverse.get().intValue());
            reverse = reverse.next();
        }

    }

    @Test
    public void testSort() {

        NodeList<Integer> node= new NodeList<>();
        node.add(5);
        node.add(3).add(4);
        node.add(1).add(2);

        NodeList<Integer> sorted= node.sort();

        for (int i= 1; i <= 5; i++) {
            Assert.assertEquals(i, sorted.get(i-1).intValue());
        }

    }


    @Test
    public void testIterator() {

        NodeList<Integer> list= new NodeList<>();
        list.add(3).add(4);
        list.add(1).add(2);

        int[] expected= new int[] {3,4,1,2};
        int i= 0;
        for (Integer value : list) {
            Assert.assertEquals(expected[i++], value.intValue());
        }

    }


    @Test
    public void testIndexing() {

        NodeList<Integer> list= new NodeList<>();
        list.add(3).add(4);
        list.add(1).add(2);

        Assert.assertEquals(4, list.get(1).intValue());
        Assert.assertEquals(3, list.get(0).intValue());
        Assert.assertEquals(1, list.get(2).intValue());
        Assert.assertEquals(2, list.get(3).intValue());
    }


    @Test
    public void testContains() {

        NodeList<Integer> list= new NodeList<>();
        list.add(3).add(4);
        list.add(1).add(2);

        Assert.assertTrue(list.contains(3));
        Assert.assertFalse(list.contains(5));
    }


    @Test
    public void testIsEmpty() {
        NodeList<Integer> empty= new NodeList<>();
        Assert.assertTrue(empty.isEmpty());
        empty.add(3);
        Assert.assertFalse(empty.isEmpty());
    }

}
