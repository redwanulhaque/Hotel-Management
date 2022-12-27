import java.util.ArrayList;
import java.util.List;

public class Room{
    private List<RoomWatcher> roomWatcher = new ArrayList<>();

    public void addWatcher(RoomWatcher cw){
        roomWatcher.add(cw);
    }

    public void removeWatch(RoomWatcher cw){
        roomWatcher.remove(cw);
    }

    public boolean notify(int numOfRoom){
        for(RoomWatcher cw: roomWatcher)
            cw.notify(numOfRoom);
        return true;
    }


}