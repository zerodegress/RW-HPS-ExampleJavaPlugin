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
import com.github.dr.rwserver.util.log.Log;

import java.util.Arrays;

/**
 * @author Dr
 */
public class Main extends Plugin {
	/**
	 * 这里主要做初始化
	 */
	@Override
	public void init(){
		//监听玩家进入
		Events.on(EventType.PlayerJoinEvent.class, event -> {
			event.getPlayer().sendSystemMessage("Plugin测试 这是进入的时间 "+ Time.getUtcMilliFormat(1));
		});

		//过滤消息
		Data.core.admin.addChatFilter((player, text) -> text.replace("heck", "h*ck"));

		//动作过滤正在进行中

		/**
		 * java.io.EOFException
		 *         at java.base/java.util.zip.GZIPInputStream.readUByte(GZIPInputStream.java:268)
		 *         at java.base/java.util.zip.GZIPInputStream.readUShort(GZIPInputStream.java:258)
		 *         at java.base/java.util.zip.GZIPInputStream.readHeader(GZIPInputStream.java:164)
		 *         at java.base/java.util.zip.GZIPInputStream.<init>(GZIPInputStream.java:79)
		 *         at java.base/java.util.zip.GZIPInputStream.<init>(GZIPInputStream.java:91)
		 *         at com.github.dr.rwserver.util.zip.gzip.GzipDecoder.getGzipInputStream(GzipDecoder.java:23)
		 *         at com.github.dr.rwserver.data.plugin.AbstractPluginData.read(AbstractPluginData.java:84)
		 *         at com.github.dr.rwserver.data.plugin.AutoSavePluginData.read(AutoSavePluginData.java:6)
		 *         at com.github.dr.rwserver.plugin.PluginsLoad$PluginLoadData.<init>(PluginsLoad.java:107)
		 *         at com.github.dr.rwserver.plugin.PluginsLoad.loadJar(PluginsLoad.java:52)
		 *         at com.github.dr.rwserver.plugin.PluginsLoad.resultPluginData(PluginsLoad.java:29)
		 *         at com.github.dr.rwserver.data.plugin.PluginManage.init(PluginManage.java:27)
		 *         at com.github.dr.rwserver.Main.main(Main.java:79)
		 *         这个是正常的 正在想办法解决
		 */
		//读取数据
		long lastStartTime = this.pluginData.getData("lastStartTime",Time.concurrentMillis());
		String lastStartTimeString = this.pluginData.getData("lastStartTimeString",Time.getUtcMilliFormat(1));
		Log.info("lastStartTime",lastStartTime);
		Log.info("lastStartTimeString",lastStartTimeString);
		this.pluginData.setData("lastStartTime",Time.concurrentMillis());
		this.pluginData.setData("lastStartTimeString",Time.getUtcMilliFormat(1));
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

	@Override
	public void onDisable() {
		super.onDisable();
		//Custom
	}
}
