import java.util.Date;

public class DateRecord extends Date
{
  private long   userJoined;
  private String dayOfWeek;
  private String month;
  private String day;
  private String year;
  private long   lastLogin;

  DateRecord(long _userJoined)
  {
    super(_userJoined);
    userJoined = _userJoined;
  }

  public void setUserJoined()                 { userJoined = System.currentTimeMillis(); }
  public void setDayOfWeek(String _dayOfWeek) { dayOfWeek  = _dayOfWeek; }
  public void setUserMonth(String _month)     { month      = _month;     }
  public void setUserDay(String _day)         { day        = _day;       }
  public void setUserYear(String _year)       { year       = _year;      }
  public void setLastLogin(long _lastLogin)   { lastLogin  = _lastLogin; }

  public long   getUserJoined() { return userJoined; }
  public String getDayOfWeek()  { return dayOfWeek;  }
  public String getUserMonth()  { return month;      }
  public String getUserDay()    { return day;        }
  public String getUserYear()   { return year;       }
  public long   getLastLogin()  { return lastLogin;  }
}
