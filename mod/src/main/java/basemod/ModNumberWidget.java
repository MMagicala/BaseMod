package basemod;

import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.helpers.FontHelper;
import org.w3c.dom.ranges.RangeException;

import java.io.IOException;

public class ModNumberWidget {
    private static final int UI_PADDING = 10;
    private static final int LABEL_SPACE_WIDTH = 50;
    private int lowestOffset;

    public ModNumberWidget(ModPanel modPanel, SpireConfig config, String key, int x, int y) throws Exception {
        // Input validation
        if(modPanel == null){
            
        }

        if(config.getInt(key) < 0){
            throw new Exception("Value cannot be below zero");
        }

        int digitCount = Integer.toString(value).length();
        lowestOffset = (int)Math.pow(10, digitCount-1);

        int[] deltas = {-10*lowestOffset, -lowestOffset, lowestOffset, 10*lowestOffset};
        for (int i = 0; i < 4; i++) {
            StringBuilder sb = new StringBuilder();
            if (deltas[i] > 0) {
                sb.append('+');
            }
            sb.append(deltas[i]);
            int xPos = x + i * UI_PADDING;
            if(i > 1){
                xPos += LABEL_SPACE_WIDTH;
            }
            ModLabeledButton deltaBtn = new ModLabeledButton(sb.toString(), xPos, y, modPanel, (btn) -> {
/*
                if (config.getInt(TIME_LEFT_KEY) > 0) {
                    config.setInt(TIME_LEFT_KEY, config.getInt(TIME_LEFT_KEY) + deltas[index]);
                    // Time left cannot be below 0
                    if (config.getInt(TIME_LEFT_KEY) < 0) {
                        config.setInt(TIME_LEFT_KEY, 0);
                    }

                  // Update label
                label.text = Integer.toString(config.getInt(TIME_LEFT_KEY));

                try {
                    config.save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
*/
            });
            modPanel.addUIElement(deltaBtn);
        }

        // Center label
        ModLabel centerLabel = new ModLabel(Integer.toString(value), x + UI_PADDING * 1.5f, y, FontHelper.charTitleFont, modPanel, me -> {});
        modPanel.addUIElement(centerLabel);
    }

    private void
}
