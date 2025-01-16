package xgf.main;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xavi
 */
public class Channel {

	private String id;
	private List<User> users;
	
	public Channel(String id) {
		super();
		this.id = id;
		this.users = new ArrayList<User>();
	}
	
	/**
	 * Checks if the provided channel id is from an existing channel
	 * @param channelID ID of the channel to check for
	 * @return If the channel exists
	 */
	public static boolean channelExists(String channelID) {
		
		for (Channel channel : Server.channelList) {
			
			if (channel.id.equals(channelID)) return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if a provided user exists in the provided channel
	 * @param userName Name of the user to check if it exists inside the channel
	 * @param channelID Channel id to get it's related channel object of type Channel
	 * @return If the provided user exists in the provided channel id
	 */
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

	/**
	 * Given the provided channel id, return a channel object
	 * @param channelID channel id to get it's channel
	 * @return object of type Channel
	 */
	public static Channel getServerChannel(String channelID) {
		
		for (Channel channel : Server.channelList) {
			
			if (channel.id.equals(channelID)) {
				
				return channel;
			}
		}
		
		return null;
	}
	
	/**
	 * Given the provided channel id, return a list of users connected to that channel
	 * @param channelID channel id to get it's available users
	 * @return Text with a list of channels
	 */
	public static String getChannelUsersToString(String channelID) {
		
		Channel channel = getServerChannel(channelID);
		List<String> userList = new ArrayList<String>();
		
		for (User user : channel.getUsers()) {
			
			userList.add(user.getName());
		}
		
		return Server.getTimestamp() + "Usuarios activos canal " + channelID + ": " + userList.toString();
	}
	
	/**
	 * @return Text with a list of available channels
	 */
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
