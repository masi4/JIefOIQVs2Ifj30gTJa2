package com.masi4.UI.gameInventory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.masi4.UI.gameInventory.model.Inventory;
import com.masi4.UI.gameInventory.model.Slot;
import com.masi4.UI.gameInventory.model.objects._Equipable;
import com.masi4.UI.gameInventory.model.objects._InventoryItem;
import com.masi4.UI.gameInventory.model.objects._Useable;
import com.masi4.gamehelpers.recourceHandlers.AssetLoader;
import com.masi4.gamehelpers.helpers.BackgroundColor;

public class ContextMenu extends Window
{
    private static final Window.WindowStyle contextMenuStyle;
    static
    {
        BackgroundColor bc = new BackgroundColor("UI/Inventory/inventoryDefaultSkin.png");
        bc.setColor(0, 0, 0, 255);

        contextMenuStyle= new Window.WindowStyle(
                AssetLoader.default18,
                Color.WHITE,
                bc);
    }
    // Чтобы контекстное меню жило только когда кликаешь по итему. Не критично, но можно переделать.
    public boolean isTrueClick = false;

    private _InventoryItem item;

    private Slot slot;
    private SlotView slotView;

    private Inventory inventory;
    private InventoryView inventoryView;

    private InventoryMain inventoryMain;

    public ContextMenu(InventoryMain inventoryMain)
    {
        super("", contextMenuStyle);
        SetStyle();
        this.inventoryMain = inventoryMain;
        inventoryView = inventoryMain.GetInventoryWindow().GetInventoryView();
        inventory = inventoryMain.GetInventory();
    }

    private void SetStyle()
    {
        this.setTouchable(Touchable.enabled);
        this.pad(5);
    }

    public void Show(final SlotView slotView0)
    {
        this.clear();
        this.setVisible(true);
        slotView = slotView0;
        slot = slotView0.slot;
        item = slotView0.slot.GetItem();

        LoadMenu();
        this.pack();
    }

    public void Hide()
    {
        this.clear();
        this.setVisible(false);
    }

    private void LoadMenu()
    {
        ////////////////////////////////////////////////////////////////////////////////////////////
        if(item instanceof _Useable)
        {
            ContextMenuLabel label = new ContextMenuLabel("Использовать");

            label.addListener(new ClickListener()
            {
                @Override
                public void clicked(InputEvent event, float x, float y)
                {
                    ((_Useable) item).Use();
                    slot.Reduce(1);
                    slotView.UpdateView();
                    Hide();
                }
            });
            this.add(label).left();
            this.row();
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        if(item instanceof _Equipable)
        {
            ContextMenuLabel label = new ContextMenuLabel("Надеть");

            label.addListener(new ClickListener()
            {
                @Override
                public void clicked(InputEvent event, float x, float y)
                {
                    ((_Equipable) item).Equip();
                    Hide();
                }
            });
            this.add(label).left();
            this.row();
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        if(item instanceof _InventoryItem)
        {
            ContextMenuLabel label1 = new ContextMenuLabel("Разделить");

            label1.addListener(new ClickListener()
            {
                @Override
                public void clicked(InputEvent event, float x, float y)
                {
                    Slot slot1 = inventory.GetFirstEmptySlot();
                    if(slot1 != null) {
                        if(slot.Split(slot1));
                        inventoryView.UpdateView();
                    }
                    Hide();
                }
            });
            this.add(label1).left();
            this.row();
            ////////////////////////////////////////////////////////////////////////////////////////
            ContextMenuLabel label2 = new ContextMenuLabel("Выбросить");

            label2.addListener(new ClickListener()
            {
                @Override
                public void clicked(InputEvent event, float x, float y)
                {
                    slot.Remove();
                    slotView.UpdateView();
                    Hide();
                }
            });
            this.add(label2).left();
            this.row();
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
    }

    ////////////////////////////////// ПРАВИЛЬНЫЕ ПОЗИЦИИ //////////////////////////////////////////
    @Override
    public void setPosition(float x,float y)
    {
        super.setPosition(x, y-getHeight());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

}

class ContextMenuLabel extends Label
{
    private final static LabelStyle labelStyle;

    static
    {
        labelStyle = new Label.LabelStyle(AssetLoader.default18, Color.WHITE);
    }

    public ContextMenuLabel(CharSequence text)
    {
        super(text, labelStyle);
        SetStyle();

        this.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
            {
                setPosition(getX() + 5,getY() );
                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button)
            {
                setPosition(getX() - 5,getY() );
            }
        });
    }
    private void SetStyle()
    {
        this.setFontScale(2.5f);
        this.setTouchable(Touchable.enabled);
    }
}