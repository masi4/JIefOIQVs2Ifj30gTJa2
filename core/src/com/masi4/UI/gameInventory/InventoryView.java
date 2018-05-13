package com.masi4.UI.gameInventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.masi4.gamehelpers.BackgroundColor;

import static com.masi4.UI.gameInventory.InventoryMain.InventoryViewport;
import static com.masi4.UI.gameInventory.InventoryScreen.STAGE_HEIGHT;


/**
 * Created by U1wknUzeU6 on 01.05.2018.
 */

public class InventoryView extends Table
{
    public InventoryView()
    {
        setTouchable(Touchable.enabled);
        right().top().pad(10,10,10,10);


        BackgroundColor bc = new BackgroundColor("UI/Inventory/inventoryDefaultSkin.png");
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
                setPosition(getX(),
                        (getY() - (yWhenTouched[0] - y)) > 0 ? 0:
                                (getY() - (yWhenTouched[0] - y) + getHeight() < InventoryViewport.getWorldHeight()-20) ? InventoryViewport.getWorldHeight()-20 - getHeight() :
                                        (getY() - (yWhenTouched[0] - y)));
               
                if(getY() - (yWhenTouched[0] - y) > 0  ||
                   getY() - (yWhenTouched[0] - y) + getHeight() < InventoryViewport.getWorldHeight()-20)
                {
                    yWhenTouched[0] = y;
                }


                Gdx.app.log("sa ", getTop()+" "+(getY() - (yWhenTouched[0] - y)));
                Gdx.app.log("sa ", getTop()+" "+ getY());
                Gdx.app.log("sa ", getTop()+" "+(yWhenTouched[0] - y));
                Gdx.app.log("asd", getHeight()+"");
            }
        });
        /// Убогий скролл....
        /// Достаточно неубогий
    }
}
