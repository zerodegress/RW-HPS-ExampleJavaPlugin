package example;

import com.github.dr.rwserver.data.Player;
import com.github.dr.rwserver.plugin.event.AbstractEvent;
import com.github.dr.rwserver.util.log.Log;

public class Event implements AbstractEvent {
    @Override
    public void registerServerLoadEvent() {
        Log.clog("Example Plugin加载完了");
    }

    @Override
    public void registerPlayerJoinEvent(Player player) {
        player.sendSystemMessage("你好!! 这是RW-HPS新的Event的实现");
    }
}
