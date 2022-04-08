package example;

import cn.rwhps.server.net.core.ConnectionAgreement;
import cn.rwhps.server.plugin.event.AbstractGlobalEvent;
import cn.rwhps.server.util.log.Log;
import org.jetbrains.annotations.NotNull;

public class EventGlobal implements AbstractGlobalEvent {
    @Override
    public void registerNewCloseEvent(@NotNull ConnectionAgreement connectionAgreement) {

    }

    @Override
    public void registerNewConnectEvent(@NotNull ConnectionAgreement connectionAgreement) {

    }

    @Override
    public void registerServerLoadEvent() {
        Log.clog("Example Plugin加载完了");
    }

}
