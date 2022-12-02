/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.farmingdale.csc325_project;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author AlexH
 */
public class CalendarController extends HomePageController implements Initializable {
    
    @FXML
    private VBox VBox_navBar;

    @FXML
    private VBox VBox_navButtons;
    
    private Calendar cal = new Calendar("Cal");
    private CalendarSource calSource = new CalendarSource("Source");
    @FXML
    private CalendarView calendarView_cal = new CalendarView();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateMenu();
        
        Interval inter = new Interval(LocalDate.now(), LocalTime.now(), LocalDate.now(), LocalTime.now());
        Entry entry = new Entry("test", inter);
        cal.addEntry(entry);
        calSource.getCalendars().add(cal);
        calendarView_cal.getCalendarSources().set(0, calSource);
        
        readEntriesIntoCalendar();
    }
    
//    public Entry readEntry(QueryDocumentSnapshot document) {
//        
//        Entry thisEntry = new Entry(title, interval);
//        return thisEntry;
//    }
    

    public void readEntriesIntoCalendar() {
        
    }
    
}
