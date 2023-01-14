package principledev.netheriteroadii.common;

import net.minecraft.util.IIntArray;

public class PurifierContainerItemNumber implements IIntArray {
    int i = 0;

    @Override
    public int get(int index) {
        return i;
    }

    @Override
    public void set(int index, int value) {
        i = value;
    }

    @Override
    public int size() {
        return 7;
    }
}
