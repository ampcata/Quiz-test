package examples.hello;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Student extends Agent {

	protected void setup() {
		
		//Must find a way to answer questions, this will return always true
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
		addBehaviour(new CyclicBehaviour() {
			@Override
			public void action() {
				
				//so blockingReceive method blocks all activities until the message is received. receive(), will return the message, or null if no message was received, thus allowing it to do other jobs like collecting data from sensors???
				ACLMessage msg = this.getAgent().receive();
				if (msg != null) {
					System.out.println("\n***New Message from Teacher***");
					System.out.println("Message sender AID :" + msg.getSender().getLocalName());
					System.out.println("Message content:" + msg.getContent());
					
					String replyAnswer = "";
					for(int i =0; i < list.size()-1;i++) {
						if(list.get(i).getQuestion().equalsIgnoreCase(msg.getContent())) {
							replyAnswer = String.valueOf(list.get(i).getAnswer());
						}
					}
					
					
					
					
					//createReply() already takes the sender AID. By adding another receiver, the message will be sent twice. spent 1 hour on this, I, Dobre Mihai am a fool
//					ACLMessage toSendmsg = msg.createReply();
//					toSendmsg.addReceiver(new AID("teacher",AID.ISLOCALNAME));
//					toSendmsg.setPerformative(ACLMessage.INFORM);
//					toSendmsg.setContent("MERSIII" );
//					send(toSendmsg);
					
					String cont = msg.getContent();
					ACLMessage toSendmsg = msg.createReply();
					toSendmsg.setPerformative(ACLMessage.INFORM);
					toSendmsg.setContent(replyAnswer);
					send(toSendmsg);
				} else {
					block();
				}
			}
			
		});
		
	}
	
}
