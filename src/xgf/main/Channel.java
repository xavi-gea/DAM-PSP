package xgf.main;

import java.util.ArrayList;
import java.util.List;

public class Channel {

	private String id;
	private String name;
	private List<User> users;
	
	public Channel(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.users = new ArrayList<User>();
	}
	
	public static boolean channelExists(String channelID) {
		
		for (Channel channel : Server.channelList) {
			
			if (channel.id.equals(channelID)) return true;
		}
		
		return false;
	}
	
	public static boolean userExistsInChannel(String userName, String channelID) {
		
		Channel channel = getServerChannel(channelID);
		
		if (channel != null) {
			
			for (User user : channel.users) {
				
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
	
	public static String getChannelUsersToString(String channelID) {
		
		Channel channel = getServerChannel(channelID);
		List<String> userList = new ArrayList<String>();
		
		for (User user : channel.getUsers()) {
			
			userList.add(user.getName());
		}
		
		return Server.getTimestamp() + "Usuarios activos canal " + channelID + ": " + userList.toString();
	}
	
	public static String getServerChannelList() {
		
		return Server.getTimestamp() + "Canales disponibles: " + Server.channelNames.toString();
	}

	public List<User> getUsers() {
		return users;
	}

	public String getId() {
		return id;
	}
}
