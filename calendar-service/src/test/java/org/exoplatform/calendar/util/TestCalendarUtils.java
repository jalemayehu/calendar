package org.exoplatform.calendar.util;


import java.util.Locale;

import org.exoplatform.calendar.util.CalendarUtils;

import junit.framework.TestCase;

public class TestCalendarUtils extends TestCase {
  
  //mvn test -Dtest=TestCalendarUtils#testGetLocationDisplayString
  public void testGetLocationDisplayString() {
    assertEquals("", CalendarUtils.getLocationDisplayString(""));
    assertEquals("Viet nam", CalendarUtils.getLocationDisplayString("Viet nam"));
    Locale locale = Locale.FRANCE;
    String vietnamLocation = locale.getDisplayCountry() + "(" + locale.getDisplayLanguage() + ")";
    String countryname = locale.getISO3Country();
    assertEquals(vietnamLocation, CalendarUtils.getLocationDisplayString(countryname));
  }
  
  public void testGenerateTimeZoneLabel() {
    assertEquals("", CalendarUtils.generateTimeZoneLabel(""));
    
    assertEquals("Viet nam", CalendarUtils.generateTimeZoneLabel("Viet nam"));
    
    String vietnamTimezone = "Asia/Ho_Chi_Minh";
    String display = "(GMT +07:00) Asia/Ho_Chi_Minh";
    
    assertEquals(display, CalendarUtils.generateTimeZoneLabel(vietnamTimezone));
  }
  
  public void testCleanValue() throws Exception {
    String values = "";
    assertEquals("", CalendarUtils.cleanValue(values));
    values = "root,";
    assertEquals("root", CalendarUtils.cleanValue(values));
    values = "root, ";
    assertEquals("root", CalendarUtils.cleanValue(values));
    values = " root     ,root ";
    assertEquals("root", CalendarUtils.cleanValue(values));
    values = " root, root , root ";
    assertEquals("root", CalendarUtils.cleanValue(values));
    values = ",root,";
    assertEquals("root", CalendarUtils.cleanValue(values));
    values = " , root , ";
    assertEquals("root", CalendarUtils.cleanValue(values));
    values = " , root , root abc ";
    assertEquals("root,root abc", CalendarUtils.cleanValue(values));
    values = "demo, root, exo/test  ,   platform/user ";
    assertEquals("demo,root,exo/test,platform/user", CalendarUtils.cleanValue(values));
  }
  
  public void testConvertURLsAsLinks() {
    String text = "a\rb\nc  http://github.com/exodev\nawww.github.com/exodev\nhttps://github.com/exodev\n bbac";
    String result = "a\rb\nc  <a href=\"http://github.com/exodev\" target=\"_blank\">http://github.com/exodev</a>\na<a href=\"http://www.github.com/exodev\" target=\"_blank\">www.github.com/exodev</a>\n<a href=\"https://github.com/exodev\" target=\"_blank\">https://github.com/exodev</a>\n bbac";
    assertEquals(result, CalendarUtils.convertURLsAsLinks(text));
  }
}

