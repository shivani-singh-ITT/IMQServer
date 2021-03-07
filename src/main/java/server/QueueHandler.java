package server;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import model.IMQMessage;

public class QueueHandler {
	private static QueueHandler queueHandler = null;
	private static LinkedList<IMQMessage> messageQueue = null;
	private static List<IMQMessage> deadLetterQueue = null;

	private QueueHandler() {

	}

	public static QueueHandler getInstance() {
		if (queueHandler == null)
			queueHandler = new QueueHandler();

		return queueHandler;
	}

	public LinkedList<IMQMessage> getQueue() {
		if (messageQueue == null) {
			return new LinkedList<IMQMessage>();
		}
		return messageQueue;
	}

	public List<IMQMessage> getDeadLetterQueue() {
		if (deadLetterQueue == null) {
			return new LinkedList<IMQMessage>();
		}
		return deadLetterQueue;
	}

	public void addMessageInDeadLetterQueue() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

			@Override
			public void run() {
				if (messageQueue.size() > 0)
					for (IMQMessage message : messageQueue) {
						if (message.getExpiredDate().equals(LocalDateTime.now().format(myFormatObj))) {
							deadLetterQueue.add(message);
							messageQueue.remove(message);
						}
					}
			}
		}, 0, 60000);
	}

	public String addMessageInQueue(IMQMessage message, LinkedList<IMQMessage> messagingQueue) {
		messagingQueue.add(message);
		return "Messgae Published!!";
	}

	public String getMessageFromQueue(LinkedList<IMQMessage> messagingQueue) {
		StringBuilder stringBuilder = new StringBuilder();
		for (IMQMessage singleMessage : messagingQueue) {
			stringBuilder.append(singleMessage);
			stringBuilder.append("-n");
		}
		if (stringBuilder.length() > 0)
			return stringBuilder.toString();
		else
			return "You have checked all messages";
	}
}