package basemod;

import basemod.ModLabel;
import basemod.ModLabeledButton;
import basemod.ModPanel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ModNumberWidget {
    private static final int UI_PADDING = 10;
    private static final int LABEL_SPACE_WIDTH = 50;
    private int lowestOffset;
    private ModLabeledButton[] btns;

    private Hitbox hb;

    public ModNumberWidget(final ModPanel modPanel, final int value, final int x, final int y, final int numBtns,
                           final Consumer<ModNumberWidget> onValueChanged) throws Exception {
        // Input validation
        // TODO: change exception
        if (value < 0) {
            throw new Exception("Value cannot be below zero");
        }
        if (numBtns % 2 != 0) {
            throw new Exception("Must have even number of buttons");
        }

        btns = new ModLabeledButton[numBtns];

        // Create hitbox for the widget
        // TODO: Fix btn height
        hb = new Hitbox(x, y, UI_PADDING * 3 + LABEL_SPACE_WIDTH, 50);

        // Create center label
        ModLabel centerLabel = new ModLabel(Integer.toString(value), x + UI_PADDING + LABEL_SPACE_WIDTH / 2f, y,
                FontHelper.charTitleFont, modPanel, me -> {
            // Use the label update function to check for mouse wheel input on the widget
            if(hb.hovered) {
                if (Gdx.input.isButtonPressed(Input.Buttons.FORWARD)) {
                    changeOffset(false);
                }
                if (Gdx.input.isButtonPressed(Input.Buttons.BACK)) {
                    changeOffset(true);
                }
            }
        });
        modPanel.addUIElement(centerLabel);

        // Determine lowest offset
        int digitCount = Integer.toString(value).length();
        lowestOffset = (int) Math.pow(10, digitCount - 1);

        // Create btns
        for (int i = 0; i < numBtns; i++) {
            int xPos = x + i * UI_PADDING;
            // Account for label
            if (i > 1) {
                xPos += LABEL_SPACE_WIDTH;
            }
            int index = i;

            ModLabeledButton deltaBtn = new ModLabeledButton("", xPos, y, modPanel, (btn) -> {
                if (value > 0) {
                    int v = value;
                    v += Integer.parseInt(btns[index].label);
                    // Value cannot be below zero
                    if (v < 0) {
                        v = 0;
                    }

                    // Update label
                    centerLabel.text = Integer.toString(v);

                    // TODO: Run defined code
                }
            }
            );
            modPanel.addUIElement(deltaBtn);
            btns[i] = deltaBtn;
        }
        applyOffsetToLabels();
    }

    // false = decrease offset
    private void changeOffset(boolean increaseOffset) {
        if(increaseOffset){
            lowestOffset *= 10;
            // Limit for offset is 1
        }else if(lowestOffset >= 10){
            lowestOffset /= 10;
        }
        applyOffsetToLabels();
    }

    private void applyOffsetToLabels(){
        // Determine labels for btns
        int[] deltas = new int[btns.length];
        for(int i = 0; i < 2; i++){
            // Determine sign
            char sign = i == 0 ? '-' : '+';
            // Create labels for each side
            for(int j = 0; j < btns.length/2; j++){
                StringBuilder sb = new StringBuilder();
                // Determine sign and appropriate index
                int idx = j;
                if(i == 1){
                    idx += btns.length/2;
                }
                sb.append(sign);

                float multiplier = btns.length/2f - (j + 1);
                deltas[idx] = (int)Math.pow(10, multiplier)*lowestOffset;
                sb.append(deltas[j+btns.length/2]);
                btns[]
            }
        }
    }
}
