package com.masi4.UI.gameInventory;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.masi4.UI.gameInventory.model.objects._InventoryItem;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gamehelpers.BackgroundColor;
import com.masi4.gamehelpers.GamePreferences;
// Показывается в зависимости от контекстного меню
public class DialogBox extends Table
{
    float DEFAULT_SCALE = 2.5f;

    private Label title;
    private Label discription;

    private Label.LabelStyle titleStyle;
    private Label.LabelStyle discriptionStyle;

    public DialogBox()
    {
        SetStyle();
        Load();
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
        titleStyle.font = AssetLoader.default18;
        title = new Label("",titleStyle);

        discriptionStyle = new Label.LabelStyle();
        discriptionStyle.font = AssetLoader.default12;
        discription = new Label("",discriptionStyle);

        title.setFontScale(DEFAULT_SCALE);
        discription.setFontScale(DEFAULT_SCALE);

        this.add(title);
        this.row();
        this.add(discription);
    }

    public void Show(_InventoryItem item)
    {
        title.setVisible(true);
        discription.setVisible(true);

        int id = AssetLoader.Inventory_GetItemId(item);

        title.setText(GamePreferences.loc.format("InventoryItem"+id+"_Title"));
        try {
            discription.setText(GamePreferences.loc.format("InventoryItem" + id + "_Discription", item.GetStats()));
        }
        catch (Exception ex){
            discription.setText(GamePreferences.loc.format("InventoryItem" + id + "_Discription"));
        }
    }

    public void Hide()
    {
        title.setText("");
        discription.setText("");

        title.setVisible(false);
        discription.setVisible(false);
    }

}
