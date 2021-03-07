package parser;
 
import java.util.Arrays; 
import java.util.List;
 
public class CommandParser {  
   public String[] parse(String command) {  
	   List<String> commandsList = Arrays.asList(new String[]{"login","logout","connect","publish","disconnect","config","showtopics"});
	   String[] keywords = new String[3]; 
	   if(command.startsWith("imq ")) {  
		   command=command.replace("imq ",""); 
		   keywords = command.split(" -");    
		   if(commandsList.contains(keywords[0])){ 
			if(validateCommand(keywords)) {
			  return keywords; }
			else {
				System.out.println("Please check your command and try again, Enter imq help for help");
			   keywords[0]="restart";
			   return keywords;
			}
		   }
		   else if(keywords[0].equals("help")) {
			   help();
			   keywords[0]="restart";
			   return keywords;
		   }
		   else {
			   System.out.println("Invalid Command name, Enter imq help for help");
			   keywords[0]="restart";
			   return keywords;
		   }
	   }else {
		   System.out.println("Invalid Command, Enter imq help for help"); 
		   keywords[0]="restart";
		   return keywords;
	   } 
   }
   
   private void help() {
	   System.out.println("Usage: imq [command] -[attributes] \n"
	   		+ "eg: imq login -username \n"
	   		+ "imq login -username -password     Login or Signup\n"
	   		+ "imq connect -topicname            Connect to a Topic\n"
	   		+ "imq disconnect -topicname         Disconnect a Topic\n"
	   		+ "imq publish -topicname -message   Publish a message in Topic\n"
	   		+ "imq config -topicname             See messages in a Topic\n"
	   		+ "imq showtopics                    To see all the topics to connect\n" 
	   		+ "imq logout                        Logout");
   }
   
   private boolean validateCommand(String[] keywords) {  
	   List<String> commandsList = Arrays.asList(new String[]{"login","connect","disconnect","config"});
	   
	 if(keywords[0].equals("publish") && keywords.length!=3) {
		 return false;
	 }else if(keywords.length!=1 && (keywords[0].equals("logout") || keywords[0].equals("showtopics"))) {
		 return false;
	 }else if(keywords.length!=2 && commandsList.contains(keywords[0])) {
		 return false;
	 }
      return true;
   }
   }