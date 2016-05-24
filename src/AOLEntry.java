import java.io.BufferedReader;
import java.io.Serializable;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liukaichi on 5/21/2016.
 */
public class AOLEntry implements Serializable
{
    String query;
    Date time;
    int userID;


    public AOLEntry(String entry)
    {

    }

    public String getQuery()
    {
        return query;
    }

    public void setQuery(String query)
    {
        this.query = query;
    }

    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }

    public int getUserID()
    {
        return userID;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
    }
}
