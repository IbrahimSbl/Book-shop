package Product;

import java.util.ArrayList;

public class Fiction extends Product{
	ArrayList<String> Characters;
	private String Place;
	private String Time;
	private String Problem;
	public Fiction(String preview,String place,String time,String problem) {
		super(preview);
		this.setPlace(place);
		this.setTime(time);
		this.setProblem(problem);
		Characters = new ArrayList<String>();
	}
	public void addCharacter(String Character) {
		Characters.add(Character);
	}
	public String ShowCharacters(){
		String characters="";
		for(int i=0;i<Characters.size();i++){
			characters += (Characters.get(i))+" ";
		}
		return characters;
	}
	public String getPlace() {
		return Place;
	}
	public void setPlace(String place) {
		Place = place;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getProblem() {
		return Problem;
	}
	public void setProblem(String problem) {
		Problem = problem;
	}
	
}

