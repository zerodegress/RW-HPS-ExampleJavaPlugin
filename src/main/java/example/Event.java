package example;

import com.github.dr.rwserver.data.Player;
import com.github.dr.rwserver.net.core.AbstractNetConnect;
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

    /**
     * 这是目前的1.3.0 -M1的bug
     * 如果要使用新的Event实现 请带上这个覆盖
     */
    @Override
    public String[] registerPlayerConnectPasswdCheckEvent(AbstractNetConnect abstractNetConnect, String passwd) {
        return new String[]{"false",""};
    }


}
