package com.masi4.UI.gameInventory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.masi4.UI.gameInventory.model.objects._InventoryItem;
import com.masi4.UI.gameInventory.model.objects._Useable;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gamehelpers.BackgroundColor;

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

    public ContextMenu()
    {
        super("", contextMenuStyle);

    }

    public void Show(final _InventoryItem item)
    {
        this.clear();
        this.setVisible(true);
        if(item instanceof _Useable)
        {
            ContextMenuLabel label = new ContextMenuLabel("Использовать");
            label.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
                {
                    ((_Useable) item).Use();
                    return true;
                }});
            this.add(label);
        }
        //...
        this.pack();
    }

    public void Hide()
    {
        this.clear();
        this.setVisible(false);
    }
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
        this.setFontScale(2.5f);
    }

}