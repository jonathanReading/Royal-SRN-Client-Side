import java.time.LocalTime;
import java.util.ArrayList;

public class BoatRaceDetails {
//private	boolean SpinnakerUsed;
@SuppressWarnings("unused")
private	int ElapsedMotorTime;
@SuppressWarnings("unused")
private	int ElapsedSailTime;
@SuppressWarnings("unused")
private	int MotoringAllowanceRemaining;
boolean currentlySailing;
public String buttonName;
protected String RecordState = "sailing";

ArrayList<BoatEvents> BoatEventsArrayList = new ArrayList<BoatEvents>();
public static ArrayList<BoatEvents> BoatEventsArrayListClone = new ArrayList<BoatEvents>();


	public BoatRaceDetails(int i, int j, int k){
		this.ElapsedMotorTime = i;
		this.ElapsedSailTime = j;
		this.MotoringAllowanceRemaining = k;
		this.currentlySailing = true;
		this.buttonName = "Start Motoring";
	}

	public void handleBoatEvent(String gpsLatitude, String gpsLongitude, LocalTime localDateTime) {
		if(BoatEventsArrayList.size()!=0){
			modifyExistingBoatEvent(gpsLatitude, gpsLongitude, localDateTime);
		}	
		addNewBoatEvent(gpsLatitude, gpsLongitude, localDateTime);
		
	}

	void modifyExistingBoatEvent(String gpsLatitude, String gpsLongitude, LocalTime localTime) { 
		BoatEvents currentEvent = BoatEventsArrayList.get(BoatEventsArrayList.size()-1);
		
		currentEvent.AddEndEvents(gpsLatitude, gpsLongitude, localTime, currentlySailing, RecordState);
		System.out.println(currentEvent.toString());
		changeEngineState();
	}

	private void addNewBoatEvent(String gpsLatitude, String gpsLongitude, LocalTime changeTime) {
		BoatEvents boat = new BoatEvents(gpsLatitude, gpsLongitude, changeTime, currentlySailing, RecordState);
		BoatEventsArrayList.add(boat);
	}
	
	public boolean changeEngineState(){
		if (currentlySailing == false){
			currentlySailing = true;
			buttonName = ("Start Motoring");
			RecordState = ("Sailing ");
		}else{
			currentlySailing = false;
			buttonName = ("Start Sailing");
			RecordState = ("Motoring ");
		}
		return currentlySailing;

	}

	public String getButtonName() {
		return buttonName;
	}

	public boolean getState() {
		return currentlySailing;
		
	}
	
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}	
	
	@SuppressWarnings("unchecked")
	public ArrayList<BoatEvents> GetCloneOfBoatEventsArrayList(){
		
		BoatEventsArrayListClone = (ArrayList<BoatEvents>)BoatEventsArrayList.clone();
		    return BoatEventsArrayListClone;				
	}
}
