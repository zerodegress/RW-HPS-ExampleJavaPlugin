package example;

import com.github.dr.rwserver.net.core.ConnectionAgreement;
import com.github.dr.rwserver.plugin.event.AbstractGlobalEvent;
import com.github.dr.rwserver.util.log.Log;
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
