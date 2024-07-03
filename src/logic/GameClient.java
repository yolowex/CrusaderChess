package logic;

import common.enums.GameMode;

public class GameClient extends Game{
    // refer to SocketManager & SocketMessage
    public GameClient(GameMode gameMode) {
        super(gameMode);
    }
}
