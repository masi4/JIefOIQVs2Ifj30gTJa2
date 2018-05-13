package com.masi4.UI.gameInventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.masi4.UI.gameInventory.model.Inventory;
import com.masi4.gamehelpers.BackgroundColor;

import static com.masi4.UI.gameInventory.InventoryMain.InventoryViewport;
import static com.masi4.UI.gameInventory.InventoryWindow.INVENTORY_HEIGHT;


/**
 * Created by U1wknUzeU6 on 01.05.2018.
 */

public class InventoryView extends Table
{
    public InventoryView(Inventory inventory)
    {
        setTouchable(Touchable.enabled);
        right().top().pad(10,10,10,30);

        BackgroundColor bc = new BackgroundColor("UI/Inventory/inventoryDefaultSkin.png");
        bc.setColor(29, 29, 29, 255);
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
                            (getY() - (yWhenTouched[0] - y) + getHeight() < INVENTORY_HEIGHT-10) ? INVENTORY_HEIGHT-10 - getHeight() :
                                (getY() - (yWhenTouched[0] - y)));

                if(getY() - (yWhenTouched[0] - y) > 0  ||
                        getY() - (yWhenTouched[0] - y) + getHeight() < InventoryViewport.getWorldHeight()-20)
                {
                    yWhenTouched[0] = y;
                }

                /*
                setPosition(getX(),
                        (getY() - (yWhenTouched[0] - y)) < 100 ? 100:
                                (getHeight() - getY() - (yWhenTouched[0] - y) > INVENTORY_HEIGHT-10) ?  getHeight() -INVENTORY_HEIGHT-10 :
                                        (getY() - (yWhenTouched[0] - y)));*/

               // if(getY() - (yWhenTouched[0] - y) > 0  || getY() - (yWhenTouched[0] - y) + getHeight() < INVENTORY_HEIGHT-10)
               // {
              //      yWhenTouched[0] = y;
               // }
            }
        });

        for(int i = 0; i< inventory.slots.size; i++)
        {
            SlotView slotView = new SlotView(inventory.slots.get(i));
            this.add(slotView)
                    .width(slotView.getWidth())
                    .height(slotView.getHeight())
                    .space(INVENTORY_HEIGHT/50);

            if((i+1)%6==0)// по 6 айтемов в строке
                this.row();
        }

        this.pack();
    }
}
