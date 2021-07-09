package example;

import com.github.dr.rwserver.data.Player;
import com.github.dr.rwserver.data.global.Data;
import com.github.dr.rwserver.func.StrCons;
import com.github.dr.rwserver.game.EventType;
import com.github.dr.rwserver.plugin.Plugin;
import com.github.dr.rwserver.plugin.event.AbstractEvent;
import com.github.dr.rwserver.util.Time;
import com.github.dr.rwserver.util.game.CommandHandler;
import com.github.dr.rwserver.util.game.Events;

import java.util.Arrays;

/**
 * @author Dr
 */
public class Main extends Plugin {
	@Override
	public void init(){
		//监听玩家进入
		Events.on(EventType.PlayerJoinEvent.class, event -> {
			event.player.sendSystemMessage("Plugin测试 这是进入的时间 "+ Time.getUtcMilliFormat(1));
		});

		//过滤消息
		Data.core.admin.addChatFilter((player, text) -> text.replace("heck", "h*ck"));

		//动作过滤正在进行中
	}

	@Override
	public AbstractEvent registerEvents(){
		return new Event();
	}

	//注册服务器命令
	@Override
	public void registerServerCommands(CommandHandler handler){
		handler.<StrCons>register("hi", "#这是Server命令简介", (arg, log) -> {
			log.get("hi");
		});

		handler.<StrCons>register("arg","<这是必填> [这是选填]", "#这是Server命令简介", (arg, log) -> {
			log.get(Arrays.toString(arg));
		});

		handler.<StrCons>register("args","<这是必填...>", "#这是Server命令简介", (arg, log) -> {
			log.get(arg[0]);
		});
	}

	//注册客户端命令
	@Override
	public void registerClientCommands(CommandHandler handler){
		//向自己回复消息
		handler.<Player>register("reply", "<text...>", "#只取第一个回复.", (args, player) -> {
			player.sendSystemMessage("你发的是: " + args[0]);
		});

		//向玩家发送
		handler.<Player>register("whisper", "<player> <text...>", "#向另一个玩家发消息.", (args, player) -> {
			//查找玩家
			Player other = Data.playerGroup.find(p -> p.name.equalsIgnoreCase(args[0]));

			if(other == null){
				player.sendSystemMessage("找不到这个玩家!");
				return;
			}

			//向玩家发消息
			other.sendSystemMessage("玩家: " + player.name + " 向你发送: " + args[1]);
		});
	}
}
