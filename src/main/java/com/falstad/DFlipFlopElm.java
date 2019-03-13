package com.falstad;

import java.awt.*;
import java.util.StringTokenizer;

class DFlipFlopElm extends ChipElm {

    final int FLAG_RESET = 2;
    final int FLAG_SET = 4;

    boolean hasReset() {
        return (flags & FLAG_RESET) != 0 || hasSet();
    }

    boolean hasSet() {
        return (flags & FLAG_SET) != 0;
    }

    public DFlipFlopElm(int xx, int yy) {
        super(xx, yy);
    }

    public DFlipFlopElm(int xa, int ya, int xb, int yb, int f,
            StringTokenizer st) {
        super(xa, ya, xb, yb, f, st);
        pins[2].value = !pins[1].value;
    }

    String getChipName() {
        return "D flip-flop";
    }

    void setupPins() {
        sizeX = 2;
        sizeY = 3;
        pins = new Pin[getPostCount()];
        pins[0] = new Pin(0, SIDE_W, "D");
        pins[1] = new Pin(0, SIDE_E, "Q");
        pins[1].output = pins[1].state = true;
        pins[2] = new Pin(hasSet() ? 1 : 2, SIDE_E, "Q");
        pins[2].output = true;
        pins[2].lineOver = true;
        pins[3] = new Pin(1, SIDE_W, "");
        pins[3].clock = true;
        if (!hasSet()) {
            if (hasReset()) {
                pins[4] = new Pin(2, SIDE_W, "R");
            }
        } else {
            pins[5] = new Pin(2, SIDE_W, "S");
            pins[4] = new Pin(2, SIDE_E, "R");
        }
    }

    int getPostCount() {
        return 4 + (hasReset() ? 1 : 0) + (hasSet() ? 1 : 0);
    }

    int getVoltageSourceCount() {
        return 2;
    }

    void reset() {
        super.reset();
        volts[2] = 5;
        pins[2].value = true;
    }

    void execute() {
        if (pins[3].value && !lastClock) {
            pins[1].value = pins[0].value;
            pins[2].value = !pins[0].value;
        }
        if (hasSet() && pins[5].value) {
            pins[1].value = true;
            pins[2].value = false;
        }
        if (hasReset() && pins[4].value) {
            pins[1].value = false;
            pins[2].value = true;
        }
        lastClock = pins[3].value;
    }

    int getDumpType() {
        return 155;
    }

    public EditInfo getEditInfo(int n) {
        if (n == 2) {
            EditInfo ei = new EditInfo("", 0, -1, -1);
            ei.checkbox = new Checkbox("Reset Pin", hasReset());
            return ei;
        }
        if (n == 3) {
            EditInfo ei = new EditInfo("", 0, -1, -1);
            ei.checkbox = new Checkbox("Set Pin", hasSet());
            return ei;
        }
        return super.getEditInfo(n);
    }

    public void setEditValue(int n, EditInfo ei) {
        if (n == 2) {
            if (ei.checkbox.getState()) {
                flags |= FLAG_RESET;
            } else {
                flags &= ~FLAG_RESET | FLAG_SET;
            }
            setupPins();
            allocNodes();
            setPoints();
        }
        if (n == 3) {
            if (ei.checkbox.getState()) {
                flags |= FLAG_SET;
            } else {
                flags &= ~FLAG_SET;
            }
            setupPins();
            allocNodes();
            setPoints();
        }
        super.setEditValue(n, ei);
    }
}
