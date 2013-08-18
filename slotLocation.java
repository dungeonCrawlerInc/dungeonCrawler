/**
 * Created with IntelliJ IDEA.
 * User: Grehg
 * Date: 8/17/13
 * Time: 10:44 PM
 * To change this template use File | Settings | File Templates.
 *
 * Representation of each int value
 *     -1 - Default / Void / Null
 *      0 - Reserved (Quest Items...)
 *      1 - consumable
 *      2 - head
 *      3 - shoulders                              \
 *      4 - chest
 *      5 - hands
 *      6 - legs
 *      7 - feet
 *      8 - ring1
 *      9 - ring 2
 *      10 - necklace
 */
public class slotLocation {
    int slotValue = 0;

    public slotLocation(int value)
    {
        setSlotValue(value);
    }

    public int getSlotValue() {
        return slotValue;
    }

    public void setSlotValue(int slotValue) {
        this.slotValue = slotValue;
    }
}
