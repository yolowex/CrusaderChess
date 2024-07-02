package gui.app;

public class AppSettings {
    private static AppSettings instance = null;

    public boolean soundMuted = false;
    public boolean musicMuted = true;

    private AppSettings(){}

    public static AppSettings getInstance() {
        if (instance == null){
            instance = new AppSettings();
        }
        return instance;
    }
}
