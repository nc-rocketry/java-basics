import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArrayOrganizer {

    @Test
    public void testNewInputArray() {

    }

    @Test
    public void testOrganizer() {
        for (int i= 3; i <= 40; i += 5) {
            testN(i);
        }
    }

    public void testN(int n) {

        ArrayOrganizer organizer= new ArrayOrganizer();
        int[] input = ArrayTool.createRandomArray(n);
        int[] result= organizer.organize(input);
        verifyResult(organizer, input, result);
    }

    private boolean verifyResult(ArrayOrganizer o, int[] input, int[] result) {
        System.out.println(o.getMoves() +" moves[n="+ input.length +"] "+ ArrayTool.format(input) +" -> "+ ArrayTool.format(result));
        o.resetMoveCounter();
        for (int i= 0; i < result.length; i++) {
            assertEquals("element["+ i +"] expected value "+ i +" but found "+ result[i], i, result[i]);
        }
        return true;
    }

}
