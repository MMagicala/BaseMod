package basemod;

import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.helpers.FontHelper;
import org.w3c.dom.ranges.RangeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ModNumberWidget {
    private static final int UI_PADDING = 10;
    private static final int LABEL_SPACE_WIDTH = 50;
    private int lowestOffset;
    private ArrayList<ModLabeledButton> btns;

    public ModNumberWidget(final ModPanel modPanel, final int value, final int x, final int y, final Consumer onValueChanged) throws Exception {
        // Input validation
        if (value < 0) {
            throw new Exception("Value cannot be below zero");
        }

        btns = new ArrayList<>();

        // Create center label
        ModLabel centerLabel = new ModLabel(Integer.toString(value), x + UI_PADDING + LABEL_SPACE_WIDTH/2f, y, FontHelper.charTitleFont, modPanel, me -> {
        });
        modPanel.addUIElement(centerLabel);

        // Create btns

        int digitCount = Integer.toString(value).length();
        lowestOffset = (int) Math.pow(10, digitCount - 1);

        int[] deltas = {-10 * lowestOffset, -lowestOffset, lowestOffset, 10 * lowestOffset};
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
    }

    private void increaseOffsetByTen(){
        lowestOffset *= 10;
        for(ModLabeledButton btn: btns){
            applyNewOffset();
        }
    }

    private void applyNewOffset(){

    }
}
