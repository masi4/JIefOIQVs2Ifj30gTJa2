package com.masi4.UI.gameInventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.masi4.gamehelpers.BackgroundColor;

import static com.masi4.UI.gameInventory.InventoryScreen.STAGE_HEIGHT;
import static com.masi4.UI.gameInventory.InventoryScreen.STAGE_WIDTH;

/**
 * Created by U1wknUzeU6 on 01.05.2018.
 */

public class InventoryView extends Table
{
    public InventoryView()
    {
        setTouchable(Touchable.enabled);
        right().top().pad(10,10,10,10);

        BackgroundColor bc = new BackgroundColor("inventoryDefaultSkin.png");
        bc.setColor(2, 179, 228, 255);
        setBackground(bc);

        final float[] yWhenTouched = new float[1];
        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                yWhenTouched[0] = y;
                return super.touchDown(event, x, y, pointer, button);

            }
        });
        addListener(new DragListener(){
            @Override
            public void drag(InputEvent event, float x, float y, int pointer)
            {
                if(getHeight() > STAGE_HEIGHT) {
                    if ((getTop() > STAGE_HEIGHT || yWhenTouched[0] - y < 0)&&(getTop()<getHeight()||yWhenTouched[0] - y > 0)) { // СОЙДЕТ!!!
                        setPosition(getX(), getY() - (yWhenTouched[0] - y));
                    }
                }
            }
        });
        /// Убогий скролл....
        /// Достаточно неубогий
    }
}
