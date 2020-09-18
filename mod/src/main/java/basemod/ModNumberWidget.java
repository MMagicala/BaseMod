package basemod;

import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import org.w3c.dom.ranges.RangeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ModNumberWidget {
    private static final int UI_PADDING = 10;
    private static final int LABEL_SPACE_WIDTH = 50;
    private int lowestOffset;
    private ArrayList<ModLabeledButton> btns;

    private Hitbox hb;

    public ModNumberWidget(final ModPanel modPanel, final int value, final int x, final int y, final int numBtns,
                           final Consumer<ModNumberWidget> onValueChanged) throws Exception {
        // Input validation
        // TODO: change exception
        if (value < 0) {
            throw new Exception("Value cannot be below zero");
        }
        if(numBtns % 2 != 0){
            throw new Exception("Must have even number of buttons");
        }

        btns = new ArrayList<>();

        // Create hitbox for the widget
        // TODO: Fix btn height
        hb = new Hitbox(x, y, UI_PADDING*3+LABEL_SPACE_WIDTH, 50);

        // Create center label
        ModLabel centerLabel = new ModLabel(Integer.toString(value), x + UI_PADDING + LABEL_SPACE_WIDTH/2f, y,
                FontHelper.charTitleFont, modPanel, me -> {});
        modPanel.addUIElement(centerLabel);

        // Create btns

        int digitCount = Integer.toString(value).length();
        lowestOffset = (int) Math.pow(10, digitCount - 1);

        int[] deltas = new int[numBtns];
        // Negative btns
        for(int i = 0; i < numBtns/2; i++){
            deltas[i] = ;
        }

        // Positive btns
        for(int i = 0; i < numBtns/2; i++){
            deltas[i] = ;
        }

        // {-10 * lowestOffset, -lowestOffset, lowestOffset, 10 * lowestOffset};

        for (int i = 0; i < 4; i++) {
            StringBuilder sb = new StringBuilder();
            if (deltas[i] > 0) {
                sb.append('+');
            }
            sb.append(deltas[i]);
            int xPos = x + i * UI_PADDING;
            if (i > 1) {
                xPos += LABEL_SPACE_WIDTH;
            }
            int index = i;
            ModLabeledButton deltaBtn = new ModLabeledButton(sb.toString(), xPos, y, modPanel, (btn) -> {
                if (value > 0) {
                    int v = value;
                    v += deltas[index];
                    // Value cannot be below zero
                    if(v < 0){
                        v = 0;
                    }

                    // Update label
                    centerLabel.text = Integer.toString(v);

                    // TODO: Run defined code

                }
            }
            );
            modPanel.addUIElement(deltaBtn);
            btns.add(deltaBtn);
        }

        applyOffset();
    }

    // false = decrease offset
    private void changeOffset(boolean increaseOffset){
        int[] deltas = {-10 * lowestOffset, -lowestOffset, lowestOffset, 10 * lowestOffset};
        StringBuilder sb = new StringBuilder();
        if (deltas[i] > 0) {
            sb.append('+');
        }
        sb.append(deltas[i]);

        if(increaseOffset){

        }else{

        }
    }
}
