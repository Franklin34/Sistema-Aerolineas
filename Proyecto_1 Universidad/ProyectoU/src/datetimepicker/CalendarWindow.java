/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datetimepicker;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.ThemeType;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Franklin y sofia
 */
public class CalendarWindow extends JFrame{
    private static final long serialVersionUID = 1L;
    java.util.Calendar selectedDate = java.util.Calendar.getInstance();
    protected PropertyChangeSupport changeSupport;
    Calendar calendar = new Calendar();
    
    public CalendarWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(345,230);
        calendar.setTheme(ThemeType.Standard);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(calendar,BorderLayout.CENTER);

        changeSupport = new PropertyChangeSupport(this);
        MouseListener mouse = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if(me.getClickCount() == 2){
                    
                    calendar.getSelection().reset();
                    DateTime pointedDate = calendar.getDateAt(me.getX(),me.getY());
                    java.util.Calendar cal = java.util.Calendar.getInstance();
                    cal.set(pointedDate.getYear(),pointedDate.getMonth() - 1, pointedDate.getDay());
                    setSelectedDate(cal);
                    dispose();
                    
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
                //  throw new UnsupportedOperationException("Not supported yetS."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent me) {
               // throw new UnsupportedOperationException("Not supported yetS."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yetS."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent me) {
               // throw new UnsupportedOperationException("Not supported yetS."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        calendar.addMouseListener(mouse);
        
        
   
    }
    
    public Calendar gecalendario(){
    return calendar;
    }
    
    public void resetSelection(Date date){
        calendar.getSelection().reset();
        calendar.getSelection().set(new DateTime(date));
        calendar.setDate(new DateTime(date));
    }
    
    public java.util.Calendar getSelectedDate(){
    return selectedDate;
    }
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener){
       changeSupport.addPropertyChangeListener(listener);
    }
    
    public void setSelectedDate(java.util.Calendar newDate){
        java.util.Calendar oldDate = (java.util.Calendar)selectedDate.clone();
        selectedDate = newDate;
        changeSupport.firePropertyChange("Date",oldDate,selectedDate);
    }
    
    public void init(){
        setVisible(true);
    }

}
