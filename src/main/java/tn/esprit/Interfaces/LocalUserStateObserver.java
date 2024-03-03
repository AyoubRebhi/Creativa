package tn.esprit.Interfaces;

public interface LocalUserStateObserver {
    void onUserStateChanged(int userId, boolean isBlocked);

}
