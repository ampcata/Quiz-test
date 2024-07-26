package examples.hello;

public class Question {
	
	private String question;
	private Integer answer;
	
	public Question(String question, Integer answer) {
		this.question = question;
		this.answer = answer;
	}
	
	
	public String getQuestion() {
		return this.question;
	}
	
	public Integer getAnswer() {
		return this.answer;
	}

}
