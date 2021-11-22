package example;

import com.github.dr.rwserver.data.Player;
import com.github.dr.rwserver.net.core.server.AbstractNetConnect;
import com.github.dr.rwserver.plugin.event.AbstractEvent;
import com.github.dr.rwserver.util.log.Log;
import org.jetbrains.annotations.NotNull;

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

    @Override
    public void registerGameOverEvent() {

    }

    @Override
    public void registerGameStartEvent() {

    }

    @Override
    public void registerPlayerBanEvent(@NotNull Player player) {

    }

    @Override
    public void registerPlayerChatEvent(@NotNull Player player, @NotNull String s) {

    }

    @Override
    public void registerPlayerConnectEvent(@NotNull Player player) {

    }

    @NotNull
    @Override
    public String[] registerPlayerConnectPasswdCheckEvent(@NotNull AbstractNetConnect abstractNetConnect, @NotNull String s) {
        return new String[] {"false",""};
    }

    @Override
    public void registerPlayerIpBanEvent(@NotNull Player player) {

    }

    @Override
    public void registerPlayerIpUnbanEvent(@NotNull String s) {

    }

    @Override
    public void registerPlayerLeaveEvent(@NotNull Player player) {

    }

    @Override
    public void registerPlayerReJoinEvent(@NotNull Player player) {

    }

    @Override
    public void registerPlayerUnbanEvent(@NotNull Player player) {

    }
}
