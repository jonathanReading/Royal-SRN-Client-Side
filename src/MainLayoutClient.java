import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MainLayoutClient {

	protected Shell shell;
	private Text txtGpsLatitude;
	private Text txtGpsLongitude;
	public static Text txtBoatID;
	public static Text txtRaceID;
	private Button btnFinishRace;
	private Label lblElapsedMotoringTime;
	private Label lblMotoringTimer;
	private Label lblMotorTimeAllowence;
	private Label lblMotorAllowanceTimer;
	private Label lblElapsedSailingTime;
	private Label lblSailingTimer;
	public boolean FinishedRace = false;
	public static BoatRaceDetails boatRaceDetails;
	public  boolean car;
	private boolean personalDetailsEntered = false;
	private Button btnSubmitDetails;
	private int hours;
	private int minutes;
	private int seconds;
	private int MotorTimeInSeconds = 0;
	private int SailTimeInSeconds = 0;
	private int MotorAllowanceInSeconds = 0;
	private boolean IsAllowanceNegative;
	Timer timer = null;
	private String SailTimeInHHMMSS = "00:00:00";
	private String MotorTimeInHHMMSS = "00:00:00";
	private String MotorAllowanceInHHMMSS = "00:00:00";

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		boatRaceDetails= new BoatRaceDetails(0, 0, 0);

		try {
			MainLayoutClient window = new MainLayoutClient();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		} 
	}
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		/* 		Code generated using SWT to GUI tools.
		 *		It has been kept as simple as possible to provide as user friendly experience as possible
		 *		without compromising functionality or security. There are constraints on what can and cannot
		 *		be done in order to prevent erroneous data in the output file. 
		 */
		shell = new Shell();
		shell.setMinimumSize(new Point(521, 355));

		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shell.setSize(521, 355);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());

		final Button btnStartSailing = new Button(shell, SWT.NONE);
		FormData fd_btnStartSailing = new FormData();
		fd_btnStartSailing.left = new FormAttachment(0, 22);
		btnStartSailing.setEnabled(personalDetailsEntered);
		btnStartSailing.setLayoutData(fd_btnStartSailing);
		btnStartSailing.setText("Start Race");
		btnStartSailing.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					ChangeState();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String buttonName = boatRaceDetails.getButtonName();
				btnStartSailing.setText(buttonName);
			}

		});


		txtGpsLatitude = new Text(shell, SWT.BORDER);
		fd_btnStartSailing.right = new FormAttachment(txtGpsLatitude, -38);
		FormData fd_txtGpsLatitude = new FormData();
		fd_txtGpsLatitude.bottom = new FormAttachment(btnStartSailing, 0, SWT.BOTTOM);
		fd_txtGpsLatitude.left = new FormAttachment(0, 186);
		fd_txtGpsLatitude.top = new FormAttachment(btnStartSailing, 0, SWT.TOP);
		txtGpsLatitude.setEnabled(personalDetailsEntered);
		txtGpsLatitude.setLayoutData(fd_txtGpsLatitude);
		txtGpsLatitude.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				txtGpsLatitude.setText("");

			}
		});
		txtGpsLatitude.setText("GPS Latitude");

		txtGpsLongitude = new Text(shell, SWT.BORDER);
		fd_txtGpsLatitude.right = new FormAttachment(100, -192);
		FormData fd_txtGpsLongitude = new FormData();
		fd_txtGpsLongitude.bottom = new FormAttachment(btnStartSailing, 0, SWT.BOTTOM);
		fd_txtGpsLongitude.right = new FormAttachment(100, -43);
		fd_txtGpsLongitude.left = new FormAttachment(txtGpsLatitude, 6);
		txtGpsLongitude.setEnabled(personalDetailsEntered);
		txtGpsLongitude.setLayoutData(fd_txtGpsLongitude);
		txtGpsLongitude.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				txtGpsLongitude.setText("");

			}
		});
		txtGpsLongitude.setText("GPS Longitude");

		txtBoatID = new Text(shell, SWT.BORDER);
		FormData fd_txtNearestBoatAhead = new FormData();
		fd_txtNearestBoatAhead.right = new FormAttachment(100, -43);
		txtBoatID.setEnabled(!personalDetailsEntered);
		txtBoatID.setLayoutData(fd_txtNearestBoatAhead);
		txtBoatID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				txtBoatID.setText("");

			}
		});	

		txtBoatID.setText("Boat ID");

		txtRaceID = new Text(shell, SWT.BORDER);
		fd_txtNearestBoatAhead.bottom = new FormAttachment(100, -246);
		FormData fd_txtNearestBoatAstern = new FormData();
		fd_txtNearestBoatAstern.right = new FormAttachment(100, -43);
		fd_txtNearestBoatAstern.top = new FormAttachment(txtBoatID, 6);
		txtRaceID.setEnabled(!personalDetailsEntered);
		txtRaceID.setLayoutData(fd_txtNearestBoatAstern);
		txtRaceID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				txtRaceID.setText("");

			}
		});


		txtRaceID.setText("Race ID");

		btnFinishRace = new Button(shell, SWT.NONE);
		btnFinishRace.setText("Finish Race");
		FormData fd_btnFinishRace = new FormData();
		fd_btnFinishRace.right = new FormAttachment(btnStartSailing, 0, SWT.RIGHT);
		fd_btnFinishRace.top = new FormAttachment(btnStartSailing, 6);
		fd_btnFinishRace.left = new FormAttachment(btnStartSailing, 0, SWT.LEFT);
		btnFinishRace.setEnabled(personalDetailsEntered);
		btnFinishRace.setLayoutData(fd_btnFinishRace);
		btnFinishRace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				finishRace();
				btnStartSailing.setEnabled(false);
				btnFinishRace.setEnabled(false);

			}
		});

		btnSubmitDetails = new Button(shell, SWT.NONE);
		fd_txtGpsLongitude.top = new FormAttachment(btnSubmitDetails, 50);
		fd_txtNearestBoatAstern.bottom = new FormAttachment(btnSubmitDetails, -14);
		FormData fd_btnSubmitDetails = new FormData();
		btnSubmitDetails.setLayoutData(fd_btnSubmitDetails);
		btnSubmitDetails.setText("Submit Details");
		btnSubmitDetails.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				personalDetailsEntered = true;
				btnStartSailing.setEnabled(personalDetailsEntered);
				submitDetails();
			}

		});	

		lblElapsedMotoringTime = new Label(shell, SWT.NONE);
		FormData fd_lblElapsedMotoringTime = new FormData();
		fd_lblElapsedMotoringTime.left = new FormAttachment(0, 22);
		fd_lblElapsedMotoringTime.top = new FormAttachment(0, 32);
		lblElapsedMotoringTime.setEnabled(personalDetailsEntered);
		lblElapsedMotoringTime.setLayoutData(fd_lblElapsedMotoringTime);
		lblElapsedMotoringTime.setText("Elapsed\nSailing \nTime");

		lblMotoringTimer = new Label(shell, SWT.NONE);
		FormData fd_lblMotoringTimer = new FormData();
		lblMotoringTimer.setEnabled(personalDetailsEntered);
		lblMotoringTimer.setLayoutData(fd_lblMotoringTimer);
		lblMotoringTimer.setText(SailTimeInHHMMSS);

		lblMotorTimeAllowence = new Label(shell, SWT.NONE);
		fd_btnStartSailing.top = new FormAttachment(lblMotorTimeAllowence, 70);
		FormData fd_lblMotorTimeAllowence = new FormData();
		fd_lblMotorTimeAllowence.bottom = new FormAttachment(100, -190);
		fd_lblMotorTimeAllowence.left = new FormAttachment(0, 22);
		lblMotorTimeAllowence.setEnabled(personalDetailsEntered);
		lblMotorTimeAllowence.setLayoutData(fd_lblMotorTimeAllowence);
		lblMotorTimeAllowence.setText("Motor \nTime\nAllowance");

		lblMotorAllowanceTimer = new Label(shell, SWT.NONE);
		fd_lblMotorTimeAllowence.right = new FormAttachment(lblMotorAllowanceTimer, -6);
		FormData fd_lblMotorAllowanceTimer = new FormData();
		fd_lblMotorAllowanceTimer.left = new FormAttachment(0, 87);
		fd_lblMotorAllowanceTimer.top = new FormAttachment(0, 121);
		lblMotorAllowanceTimer.setEnabled(personalDetailsEntered);
		lblMotorAllowanceTimer.setLayoutData(fd_lblMotorAllowanceTimer);
		lblMotorAllowanceTimer.setText(MotorAllowanceInHHMMSS );

		lblElapsedSailingTime = new Label(shell, SWT.NONE);
		fd_lblElapsedMotoringTime.bottom = new FormAttachment(lblElapsedSailingTime, 0, SWT.BOTTOM);
		fd_lblMotoringTimer.left = new FormAttachment(lblElapsedSailingTime, 6);
		FormData fd_lblElapsedSailingTime = new FormData();
		fd_lblElapsedSailingTime.top = new FormAttachment(0, 32);
		fd_lblElapsedSailingTime.right = new FormAttachment(100, -282);
		lblElapsedSailingTime.setEnabled(personalDetailsEntered);
		lblElapsedSailingTime.setLayoutData(fd_lblElapsedSailingTime);
		lblElapsedSailingTime.setText("Elapsed\nMotoring\nTime");

		lblSailingTimer = new Label(shell, SWT.NONE);
		fd_lblElapsedSailingTime.left = new FormAttachment(lblSailingTimer, 34);
		fd_lblElapsedMotoringTime.right = new FormAttachment(lblSailingTimer, -6);
		fd_lblMotoringTimer.top = new FormAttachment(lblSailingTimer, 0, SWT.TOP);
		FormData fd_lblSailingTimer = new FormData();
		fd_lblSailingTimer.right = new FormAttachment(100, -375);
		fd_lblSailingTimer.left = new FormAttachment(0, 87);
		fd_lblSailingTimer.top = new FormAttachment(0, 49);
		lblSailingTimer.setEnabled(personalDetailsEntered);
		lblSailingTimer.setLayoutData(fd_lblSailingTimer);
		lblSailingTimer.setText(MotorTimeInHHMMSS);

		Label Seperator = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		fd_lblMotorAllowanceTimer.right = new FormAttachment(Seperator, -183);
		fd_lblMotoringTimer.right = new FormAttachment(Seperator, -25);
		fd_btnSubmitDetails.left = new FormAttachment(Seperator, 6);
		fd_txtNearestBoatAstern.left = new FormAttachment(Seperator, 4);
		fd_txtNearestBoatAhead.left = new FormAttachment(Seperator, 4);
		FormData fd_Seperator = new FormData();
		fd_Seperator.top = new FormAttachment(0, 22);
		fd_Seperator.left = new FormAttachment(0, 329);
		Seperator.setLayoutData(fd_Seperator);

		final Button btnAutofillCoordinates = new Button(shell, SWT.NONE);
		FormData fd_btnAutofillCoordinates = new FormData();
		fd_btnAutofillCoordinates.left = new FormAttachment(btnFinishRace, 32);
		fd_btnAutofillCoordinates.top = new FormAttachment(btnFinishRace, 4, SWT.TOP);
		btnAutofillCoordinates.setLayoutData(fd_btnAutofillCoordinates);
		btnAutofillCoordinates.setText("Auto-Fill Coordinates");
		btnAutofillCoordinates.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				btnAutofillCoordinates.setEnabled(false);			
				txtGpsLatitude.setEnabled(false);
				//System.out.println("Autofilling Coordinates");
				txtGpsLongitude.setEnabled(false);


			}
		});

		Label Separator = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		fd_Seperator.bottom = new FormAttachment(Separator);
		fd_btnSubmitDetails.bottom = new FormAttachment(100, -170);
		FormData fd_Separator = new FormData();
		fd_Separator.top = new FormAttachment(btnSubmitDetails, 6);
		fd_Separator.bottom = new FormAttachment(btnSubmitDetails, 8, SWT.BOTTOM);
		fd_Separator.right = new FormAttachment(100, -9);
		fd_Separator.left = new FormAttachment(0, 329);
		Separator.setLayoutData(fd_Separator);
		btnAutofillCoordinates.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
			}
		});		
	}
	private void submitDetails() {

		String RaceIn = txtRaceID.getText();
		String BoatIn = txtBoatID.getText();

		SetRaceDetails(RaceIn);
		SetBoatDetails(BoatIn);
		writeDetails(RaceIn, BoatIn);
		btnSubmitDetails.setEnabled(false);
		txtGpsLatitude.setEnabled(personalDetailsEntered);
		txtGpsLongitude.setEnabled(personalDetailsEntered);
		btnFinishRace.setEnabled(personalDetailsEntered);

		txtBoatID.setEnabled(false);
		txtRaceID.setEnabled(false);

	}	
	private String SetRaceDetails(String RaceIn) {

		String raceID = new String(RaceIn);
		return raceID;
	}
	private String SetBoatDetails(String BoatIn) {

		String boatID = new String(BoatIn);
		return boatID;
	}	private void writeDetails(String raceID, String boatID){

		PrintStream out = null;
		String desktop = System.getProperty ("user.home") + "/Desktop/";
		try {
			out = new PrintStream(desktop +"Royal Southen Results");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}			
		out.println("Race ID: "+ raceID);
		out.println("Boat ID: "+ boatID+"\n");
	}
	private void WriteRecordsToFile(ArrayList<BoatEvents> BoatEventsArrayList){
		int i = 0;

		String desktop = System.getProperty ("user.home") + "/Desktop/";

		File out = new File(desktop + "Royal Southen Results");

		while (i != (BoatEventsArrayList.size())){

			try{
				if(!out.exists()){
					out.createNewFile();
				}

				FileWriter fileWriter = new FileWriter(out, true);

				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write("RecordID: " + (i+1) + BoatEventsArrayList.get(i) + "\n\n");
				bufferedWriter.close();
			} catch(IOException e) {
			}

			i++;
		}	
    	out.setReadOnly();
	}
	private void finishRace() {
		timer.cancel();
		boatRaceDetails.modifyExistingBoatEvent(txtGpsLatitude.getText(), txtGpsLongitude.getText(), LocalTime.now());
		boatRaceDetails.GetCloneOfBoatEventsArrayList();
		WriteRecordsToFile(BoatRaceDetails.BoatEventsArrayListClone);
	}
	private void ChangeState() throws InterruptedException{
		if (!(timer == null)){
			updateTimerLabels();
			timer.cancel();
		}
		boatRaceDetails.handleBoatEvent(txtGpsLatitude.getText(), txtGpsLongitude.getText(), LocalTime.now());
		//	IncrementRelaventTimer();
		//determine if we're sailing or motoring
		if (boatRaceDetails.isSailing() == true){ //we are now sailing
			// we need to increment SailTimeInSeconds
			timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					SailTimeInSeconds += 1;

					//Solution found here:
					// http://stackoverflow.com/questions/21774088/use-java-util-timer-in-swt
					/*
					 * Originally we were just calling updateTimerLabels(), but this doesn't work because the timer is a thread, and swt is another thread.
					 * You cannot update an SWT component from another thread, so we have to do it aynchronoysly to avoid the
					 * "org.eclipse.swt.SWTException: Invalid thread access" error.
					 */
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							updateTimerLabels();
						}
					});

				}}, 0, 1000);	

		}else{
			timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					MotorTimeInSeconds += 1;						
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							updateTimerLabels();
						}
					});	
				}}, 0, 1000);
		}
	}
	public void updateTimerLabels(){
		MotorAllowanceInSeconds = SailTimeInSeconds - MotorTimeInSeconds;
		if(MotorAllowanceInSeconds <0){
			IsAllowanceNegative = true;
		}else{
			IsAllowanceNegative = false;
		}
		hours = Math.abs(MotorAllowanceInSeconds) / 3600;
		minutes = ( Math.abs(MotorAllowanceInSeconds) % 3600) / 60;
		seconds =  Math.abs(MotorAllowanceInSeconds) % 60;


		if (IsAllowanceNegative == false){
			MotorAllowanceInHHMMSS = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		}else{
			MotorAllowanceInHHMMSS = String.format("-%02d:%02d:%02d", hours, minutes, seconds);
		}


		lblMotorAllowanceTimer.setText(MotorAllowanceInHHMMSS);


		if (boatRaceDetails.isSailing() == true){

			hours = SailTimeInSeconds / 3600;
			minutes = (SailTimeInSeconds % 3600) / 60;
			seconds = SailTimeInSeconds % 60;
			SailTimeInHHMMSS = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			lblSailingTimer.setText(SailTimeInHHMMSS);
		}else{
			hours = MotorTimeInSeconds / 3600;
			minutes = (MotorTimeInSeconds % 3600) / 60;
			seconds = MotorTimeInSeconds % 60;
			MotorTimeInHHMMSS = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			lblMotoringTimer.setText(MotorTimeInHHMMSS);
		}
	}
}