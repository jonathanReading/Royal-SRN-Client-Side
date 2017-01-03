import java.util.TimerTask;

public class IncrementationofTimer extends TimerTask {
	
		public int UnknownTimerDifferenceInSeconds;
        
        public void run() {
      
        		System.out.println("timeString");
        		UnknownTimerDifferenceInSeconds = UnknownTimerDifferenceInSeconds +1;
        }

		public IncrementationofTimer(int i) {
		}

		public int getUnknownTimerDifferenceInSeconds() {
			return UnknownTimerDifferenceInSeconds;
		}
		
}