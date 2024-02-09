package eu.fr.indyli.formation.tdd.junit.dbunit.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.util.StringUtils;

/**
 * Classe de gestion des dates
 * 
 * @author Christophe.ZOME
 *
 */
public final class DateUtils {

  /**
   * Formate une chaine en String
   * 
   * @param strDate
   * @param format
   * @return
   * @throws ParseException
   */
  @SuppressWarnings("deprecation")
public static Date formatStringToDate(String strDate, String format) throws ParseException {

    if (!StringUtils.isEmpty(strDate) && !StringUtils.isEmpty(format)) {
      return new SimpleDateFormat(format).parse(strDate);
    }
    return null;
  }

  /**
   * Convert Date to String
   * 
   * @param dateToParse : Date to convert
   * @param format : date Format
   * @return
   */
  public static String dateToString(Date dateToParse, String format) {
    if (dateToParse == null)
      return "";
    SimpleDateFormat formatter = new SimpleDateFormat(format);
    return formatter.format(dateToParse);
  }

}
