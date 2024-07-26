package examples.hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


public class Teacher extends Agent{
	
	protected void setup() {
		
		List<Question> list = new ArrayList<>();
		
		list.add(new Question("What is 1 + 1", 2));
		list.add(new Question("What is 2 * 2",4));
		list.add(new Question("What is 20-3", 17));
		list.add(new Question("What is 15 + 5", 20));
		list.add(new Question("What is 18/3", 6));
		list.add(new Question("What is 24-3", 21));
		list.add(new Question("What is 10 * 0", 0));
		list.add(new Question("What is 19 + 2", 21));
		list.add(new Question("What is 1 + 7", 8));
		
		Integer score = 0;
		
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		
		//me stupid, the receiver should be the name of the agent, not the class FML
		 msg.addReceiver(new AID("student",AID.ISLOCALNAME));
		 for(int i = 0; i < list.size();i++) {
			 System.out.println("This is the " + i + " question");
			 //Choose the question to be sent, and send it
			 msg.setContent(list.get(i).getQuestion());
			 send(msg);
			 
			 //Wait for reply from student agent
			 ACLMessage msgReply = blockingReceive();
			 System.out.println("\n***New Message from Student***");
			 System.out.println("Message sender AID :" + msgReply.getSender().getLocalName());
			 System.out.println("Message content:" + msgReply.getContent());
			 
			 try {
				 Integer replyAnsw = Integer.valueOf(msgReply.getContent());
				 if (replyAnsw == list.get(i).getAnswer()) {
					 score++;
				 }
				 System.out.println("Score is:" + score);
			 } catch (Exception ex) {
				 System.out.println("Cannot convert to Integer");
			 }
			 
		 }
		 msg.setContent("The test score is:" + score);
		 send(msg);
	}

}
