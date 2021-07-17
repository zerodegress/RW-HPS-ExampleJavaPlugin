package example;

import com.github.dr.rwserver.data.Player;
import com.github.dr.rwserver.plugin.event.AbstractEvent;
import com.github.dr.rwserver.util.log.Log;

/**
 * 这个是新的Event的实现
 * 如果要使用.我推荐使用新的实现
 * 因为新的实现有同步和异步
 * 旧的事件是同步的 如果进行高IO 那么游戏体验或许极差
 * @author Dr
 */
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
