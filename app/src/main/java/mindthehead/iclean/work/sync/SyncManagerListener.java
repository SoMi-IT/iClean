package mindthehead.iclean.work.sync;

public interface SyncManagerListener {

    void onSyncSuccessful(String message);
    void onSyncError(String error);

}//SyncManagerListener
