package com.masi4.UI.gameInventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.masi4.UI.gameInventory.model.Slot;
import com.masi4.UI.gameInventory.model.objects._Useable;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gamehelpers.BackgroundColor;

import static com.masi4.UI.gameInventory.InventoryMain.InventoryViewport;
import static com.masi4.UI.gameInventory.InventoryMain.contextMenu;
import static com.masi4.UI.gameInventory.InventoryWindow.INVENTORY_HEIGHT;
import static com.masi4.gamehelpers.GameTextureRegions.itemWidth;

/**
 * Created by U1wknUzeU6 on 23.04.2018.
 */

public class SlotView extends Table
{
    Slot slot;
    public SlotView(final Slot slot)
    {
        this.slot = slot;

        BackgroundColor bc = new BackgroundColor("UI/Inventory/inventoryDefaultSkin.png");
        bc.setColor(69, 69, 69, 255);
        setBackground(bc);

        //setBackground(new TextureRegionDrawable(AssetLoader.GameInventory_SlotTextureRegion));
        setSize(INVENTORY_HEIGHT/8, INVENTORY_HEIGHT/8);
        if(!slot.isEmpty()) {
            //this.add(new ItemView(slot.GetItem())).fill(true);
            Label.LabelStyle labelStyle = new Label.LabelStyle(AssetLoader.default12_outline, Color.WHITE);

            Label count = new Label(String.valueOf(slot.GetCount()), labelStyle);
            count.setAlignment(Align.bottomRight);

            ItemView itemView = new ItemView(slot.GetItem());
            itemView.getImageCell().size(this.getWidth(), this.getHeight());

            count.setFontScale(this.getWidth()/itemWidth);

            Stack stack = new Stack();
            stack.sizeBy(this.getWidth(),this.getHeight());
            stack.addActor(itemView);
            stack.addActor(count);
            this.add(stack);
        }

        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                contextMenu.Show(slot.GetItem());
                contextMenu.isTrueClick = true;
                return true;
            }});

    }

}
