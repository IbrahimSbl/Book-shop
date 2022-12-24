package Product;

import java.util.ArrayList;

public class Politics extends Product{
	ArrayList<String> Topics; 
	public Politics(String preview) {
		super(preview);
		Topics = new ArrayList <String>();
	}
	public void addTopic(String topic) {
		Topics.add(topic);
	}
	public String ShowTopics(){
		String st = "";
		for(int i=0;i<Topics.size();i++){
			st+= (Topics.get(i))+ " ";
		}
		return st;
	}
	public ArrayList <String> getTopics() {
		return Topics;
	}
	public void setTopics(ArrayList <String> topics) {
		Topics = topics;
	}

}