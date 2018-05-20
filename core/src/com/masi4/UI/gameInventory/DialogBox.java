package com.masi4.UI.gameInventory;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.masi4.UI.gameInventory.model.objects._InventoryItem;
import com.masi4.gamehelpers.resourceHandlers.AssetLoader;
import com.masi4.gamehelpers.helpers.BackgroundColor;


// Показывается в зависимости от контекстного меню
public class DialogBox extends Table
{
    float DEFAULT_SCALE = 2f;

    private Label title;
    private Label discription;

    private Label.LabelStyle titleStyle;
    private Label.LabelStyle discriptionStyle;

    public DialogBox()
    {
        SetStyle();
        Load();
        setVisible(false);
    }

    private void SetStyle()
    {
        pad(10,30,10,10);
        align(Align.left);

        BackgroundColor bc = new BackgroundColor("UI/Inventory/inventoryDefaultSkin.png");
        bc.setColor(29, 29, 29, 255);
        setBackground(bc);
    }

    private void Load()
    {
        titleStyle = new Label.LabelStyle();
        titleStyle.font = AssetLoader.default22;
        title = new Label("",titleStyle);

        discriptionStyle = new Label.LabelStyle();
        discriptionStyle.font = AssetLoader.default18;
        discriptionStyle.font.getData().markupEnabled = true;
        discription = new Label("",discriptionStyle);

        title.setFontScale(DEFAULT_SCALE);
        discription.setFontScale(DEFAULT_SCALE);


        this.add(title).fillX().left();
        this.row();
        this.add(discription).fillX().left();
    }

    public void Show(_InventoryItem item)
    {
        setVisible(true);
        title.setVisible(true);
        discription.setVisible(true);

        title.setText(__InventoryStatics.GetItemTitle(item));
        discription.setText(__InventoryStatics.GetItemDescription(item));

        title.setColor(__InventoryStatics.GetItemColor(item));

    }

    public void Hide()
    {
        title.setText("");
        discription.setText("");

        setVisible(false);
        title.setVisible(false);
        discription.setVisible(false);
    }

}
