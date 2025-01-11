package xgf.main;

import java.util.ArrayList;
import java.util.List;

public class Channel {

	private String id;
	private String name;
	private List<Thread> users;
	
	public Channel(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.users = new ArrayList<Thread>();
	}
	
	public static boolean channelExists(String channelID) {
		
		for (Channel channel : Server.channelList) {
			
			if (channel.id.equals(channelID)) return true;
		}
		
		return false;
	}
	
	public static boolean userExistsInChannel(String channelID, String userName) {
		
		Channel channel = getServerChannel(channelID);
		
		if (channel != null) {
			
			for (Thread user : channel.users) {
				
				if (user.getName().equals(userName.toLowerCase())) {
					
					return true;
				}
			}
		}
		
		return false;
	}

	public static Channel getServerChannel(String channelID) {
		
		for (Channel channel : Server.channelList) {
			
			if (channel.id.equals(channelID)) {
				
				return channel;
			}
		}
		
		return null;
	}

	public List<Thread> getUsers() {
		return users;
	}

	public String getId() {
		return id;
	}
}
