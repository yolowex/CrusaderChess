package logic;

import common.enums.GameMode;
import gui.app.AppSettings;

public class GameFactory {
    public static Game getGame(GameMode gameMode){
        if (gameMode == GameMode.NORMAL){
            return new Game(gameMode);
        }
        else if (gameMode == GameMode.PVP_SERVER){
            return new GameServer(gameMode);
        }
        else if (gameMode == GameMode.PVP_CLIENT){
            // mute music in client to prevent two musics playing ATST
            AppSettings.getInstance().musicMuted = true;
            return new GameClient(gameMode);
        }
        throw new Error("Bad game mode! "+gameMode);
    }
}
