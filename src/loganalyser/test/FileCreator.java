/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Klaus
 */
public class FileCreator {

  public static void main(String[] args) {
    FileWriter writer = null;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    Date date = new GregorianCalendar(2013, 2, 13, 10, 0, 0).getTime();
    try {
      File file = new File("C:\\Users\\Klaus\\Desktop\\testLog.txt");
      writer = new FileWriter(file, false);

      String text = "";
      int klo = -1;
      Date dauer = null;;
      //for (int i = 0; i < 10000; i++) {
      int i = 0;
      while (true) {
        if (Math.ceil(Math.random() * 10) == 10 && klo == -1) {
          klo = i % 4 + 1;
          text = " Klopause " + klo + " = true";
          dauer = new Date(date.getTime() + (int) Math.ceil(Math.random() * 90000 + 30000));
        } else {
          text = " Spieler " + (i % 4 + 1) + ": " + ((int) Math.ceil(Math.random() * 6));
        }

        date = new Date(date.getTime() + ((int) Math.ceil(Math.random() * 30000 + 10000)));

        if (dauer != null && klo != -1 && dauer.before(date)) {
          writer.write(df.format(dauer) + " Klopause " + klo + " = false");
          writer.write(System.getProperty("line.separator"));
          klo = -1;
        }

        text = df.format(date) + text;

        writer.write(text);
        writer.write(System.getProperty("line.separator"));
        writer.flush();
        System.out.println(text);
        i++;
        Thread.sleep(250);
      }

    } catch (IOException ex) {
      Logger.getLogger(FileCreator.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InterruptedException ex) {
      Logger.getLogger(FileCreator.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        writer.close();
      } catch (IOException ex) {
        Logger.getLogger(FileCreator.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
