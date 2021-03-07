package services;

public class Selector {

	public String connectNewTopic() {
		return "insert into usertopicmapping(topicname,username)  values(?,?);";
	}

	public String getTopics() {
		return "select topicname from alltopics;";
	}

	public String getUsers() {
		return "select username from user;";
	}

	public String connectTopic() {
		return "update usertopicmapping set connected=true where topicname=? and username=?;";
	}

	public String disconnectTopic() {
		return "update usertopicmapping set connected=false where topicname=? and username=?;";
	}

	public String receiveMessage() {
		return "insert into receivedmessages (username,messageid) values(?,?);";
	}

	public String getMessage() {
		return "select messageid,data from Message where topicname=? and messageid not in(select messageid from receivedmessages where username=?);";
	}

	public String insertMessage() {
		return "insert into message(data,topicname,username) values(?,?,?);";
	}

	public String getTopicsWithUsername() {
		return "select topicname from usertopicmapping where username=?;";
	}

	public String insertUser() {
		return "insert into user(username) values (?);";
	}

	public String getConnectedTopic() {
		return "select connected from usertopicmapping where topicname=? and username=?";
	}
}
