import java.time.LocalTime;

public class BoatEvents{
	private String GpsLatStart;
	private String GpsLongStart;
	private String GpsLatEnd;
	private String GpsLongEnd;

	private String recordState;
	private LocalTime TimeStart;
	private LocalTime TimeEnd;
	private String RecordDuration;
	private int recordID = 0;
	private int hours;
	private int minutes;
	private int seconds;


	public BoatEvents(String gpsLatitude, String GpsLongitude, LocalTime TimeStart, boolean currentlySailing,String RecordState ) {
		this.GpsLatStart = gpsLatitude;
		this.GpsLongStart = GpsLongitude;
		this.TimeStart =  TimeStart;

	}

	public void AddEndEvents(String GpsLatEnd, String GpsLongEnd, LocalTime TimeEnd, boolean currentlySailing, String RecordState){
		this.GpsLatEnd = GpsLatEnd;
		this.GpsLongEnd = GpsLongEnd;
		this.TimeEnd = TimeEnd;
		this.recordState = RecordState;
		recordDuration();
	}


	@Override
	public String toString() {

		return " Record State: " + recordState +"  RecordDuration: "+ RecordDuration
				+ "\n [GpsLatStart=" + GpsLatStart + ", GpsLongStart=" + GpsLongStart + ", GpsLatEnd=" + GpsLatEnd + ", GpsLongEnd=" + GpsLongEnd + ", TimeStart=" + TimeStart + ", TimeEnd=" + TimeEnd + "]\n";
	}

	public int IncrementRecord(){
		recordID = recordID +1;
		return recordID;
	}
	public String recordDuration(){
		int l1Hour = this.TimeStart.getHour();
		int l2Hour = this.TimeEnd.getHour();
		int l1Minute = this.TimeStart.getMinute();
		int l2Minute = this.TimeEnd.getMinute();
		int l1Second = this.TimeStart.getSecond();
		int l2Second = this.TimeEnd.getSecond();

		seconds = l2Second - l1Second;
		while (seconds < 0){
			l2Minute = l2Minute -1;
			seconds = seconds + 60;
		}
		minutes = l2Minute - l1Minute;
		while (minutes < 0){
			l2Hour = l2Hour -1;
			minutes = minutes + 60;
		}
		hours = l2Hour- l1Hour;      
		RecordDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds);


		return RecordDuration;
	}	
}
