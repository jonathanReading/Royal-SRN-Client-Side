import java.io.Serializable;
import java.time.LocalTime;

public class BoatEvents implements Serializable{
	private String GpsLatStart;
	private String GpsLongStart;
	private String GpsLatEnd;
	private String GpsLongEnd;

	private String recordState;
	private LocalTime TimeStart;
	private LocalTime TimeEnd;
	private LocalTime RecordDuration;
	private int recordID = 0;
	
	public BoatEvents(String gpsLatitude, String GpsLongitude, LocalTime changeTime, boolean currentlySailing,String RecordState ) {
		this.GpsLatStart = gpsLatitude;
		this.GpsLongStart = GpsLongitude;
		this.TimeStart =  changeTime;

	}

	public void AddEndEvents(String GpsLatEnd, String GpsLongEnd, LocalTime TimeEnd, boolean currentlySailing, String RecordState){
		this.GpsLatEnd = GpsLatEnd;
		this.GpsLongEnd = GpsLongEnd;
		this.TimeEnd = TimeEnd;
		this.recordState = RecordState;
		}
	

	public String toString() {

		//IncrementRecord();
		return "Record State: " + recordState +"  RecordDuration: "+ RecordDuration
		+ "\n [GpsLatStart=" + GpsLatStart + ", GpsLongStart=" + GpsLongStart + ", GpsLatEnd=" + GpsLatEnd + ", GpsLongEnd=" + GpsLongEnd + ", TimeStart=" + TimeStart + ", TimeEnd=" + TimeEnd + "]\n";
	}
	
	public int IncrementRecord(){
		recordID = recordID +1;
		return recordID;
	}
	
}
