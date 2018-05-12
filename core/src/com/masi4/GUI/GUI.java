package com.masi4.GUI;

import com.badlogic.gdx.Gdx;
import com.masi4.UI.gameInventory.InventoryScreen;
import com.masi4.gameobjects.Player;
import com.masi4.screens.GameplayScreen;
import static com.masi4.myGame.GameMain.*;

/**
 *  Отвечает за рендер. Проверяет состояние акторов.
 */


public class GUI
{
    private StageLayer stageLayer;
    private Player player;
    private GameplayScreen gameplayScreen;

    public GUI(Player player, GameplayScreen gameplayScreen)
    {
        stageLayer = new StageLayer();
        this.player = player;
        this.gameplayScreen = gameplayScreen;
        Gdx.input.setInputProcessor(stageLayer);
        Gdx.input.setCatchBackKey(true);
    }

    boolean jumpControl = false; // Чтобы нельзя было делать распрыжку

    public void render(float delta)
    {
        stageLayer.render(delta);

        if(stageLayer.GetAttackButton().isPressed())
        {
            // TODO: реализовать атаку через абилити и вообще как надо.
            player.graphics.SetAttack(true);
        }

        if(stageLayer.GetInventoryButton().isPressed())
        {

            Gdx.app.log("Checked", stageLayer.GetInventoryButton().isChecked()+"");
            Gdx.app.log("Pressed", stageLayer.GetInventoryButton().isPressed()+"");
            game.setScreen(new InventoryScreen(gameplayScreen));
        }

        if(stageLayer.GetWalkingController().isTouched())
        {
            if (stageLayer.GetWalkingController().getKnobPercentY() > 0.5 && !jumpControl) {
                player.graphics.jump();
                jumpControl = true;
            }
            if (stageLayer.GetWalkingController().getKnobPercentY() < 0.5 && jumpControl) {
                jumpControl = false;
            }
        }

        if(!player.graphics.isAttacking())
        {
            player.graphics.setVelocityX(300 * stageLayer.GetWalkingController().getKnobPercentX());
        }

    }

    public void dispose()
    {

    }
}
