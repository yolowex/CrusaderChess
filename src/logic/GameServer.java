package logic;

import common.enums.GameMode;

public class GameServer extends Game{
    // refer to SocketManager & SocketMessage
    public GameServer(GameMode gameMode) {
        super(gameMode);
    }
}
