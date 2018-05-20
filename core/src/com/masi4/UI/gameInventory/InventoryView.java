package com.masi4.UI.gameInventory;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.masi4.UI.gameInventory.model.Inventory;
import com.masi4.gamehelpers.helpers.BackgroundColor;

import static com.masi4.UI.gameInventory.InventoryMain.InventoryViewport;
import static com.masi4.UI.gameInventory.InventoryWindow.INVENTORY_HEIGHT;


/**
 * Created by U1wknUzeU6 on 01.05.2018.
 */

public class InventoryView extends Table
{
    private Inventory inventory;
    private int[] lastClickedIndex = {-1,-1}; // Для того, чтобы можно было перетаскивать айтемы в инвентаре

    public InventoryView(Inventory inventory)
    {
        this.inventory = inventory;
        SetStyle();
        LoadScroll();
        Create();
    }

    private void SetStyle()
    {
        setTouchable(Touchable.enabled);
        right().top().pad(10,10,10,30);

        BackgroundColor bc = new BackgroundColor("UI/Inventory/inventoryDefaultSkin.png");
        bc.setColor(29, 29, 29, 255);
        setBackground(bc);

    }

    private void LoadScroll()
    {
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
            }
        });
    }

    private void Create()
    {

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

        addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                if(lastClickedIndex[0] != -1 && lastClickedIndex[1] != -1)
                {
                    inventory.slots.swap(lastClickedIndex[0], lastClickedIndex[1]);
                    //swapActor(lastClickedIndex[0], lastClickedIndex[1]); Не работает!!
                    for(int i = 0; i<2 ;i++){
                        SlotView slotView = new SlotView(inventory.slots.get(lastClickedIndex[i]));
                        getCells().get(lastClickedIndex[i]).clearActor();
                        getCells().get(lastClickedIndex[i]).setActor(slotView)
                                .width(slotView.getWidth())
                                .height(slotView.getHeight())
                                .space(INVENTORY_HEIGHT/50);
                        SetClickListener( getCells().get(lastClickedIndex[i]));
                    }

                    UpdateView();
                    lastClickedIndex[0] = -1;
                    lastClickedIndex[1] = -1;
                }
            }
        });
        for (final Cell cell: this.getCells())
        {
            SetClickListener(cell);
        }

        this.pack();
    }



    void SetClickListener(final Cell cell) // КЭЛЛ)))))))
    {
        cell.getActor().addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {

                if(!((SlotView)cell.getActor()).slot.isEmpty())
                {
                    lastClickedIndex[0] = 6 * cell.getRow() + cell.getColumn();

                }
                else if(lastClickedIndex[0] != -1)
                {
                    if(((SlotView)cell.getActor()).slot.isEmpty())
                    {
                        lastClickedIndex[1] = 6 * cell.getRow() + cell.getColumn();
                    }
                }
            }
        });
    }

    public void UpdateView()
    {
        this.setVisible(false);
        for(int i = 0; i < this.getCells().size; i++)
        {
            ((SlotView)this.getCells().get(i).getActor()).UpdateView();
        }
        this.setVisible(true);
    }
}
