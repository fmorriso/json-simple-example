import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeFormatterBuilder;
//import java.time.temporal.ChronoUnit;
//import java.time.temporal.TemporalAccessor;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

public class DateTimeUtilities {

    /**
     * Return a ZoneDateTime instance from any reasonably formatted ISO 8601 date or date/time
     * @param value - a string represenation of an ISO 8601 date, date/time or reasonable facsimile.
     * @return - an instance of ZonedDateTime without tht eim
     */
    public static ZonedDateTime getZonedDateTime(String value) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(ISO_LOCAL_DATE)
                .optionalStart()           // time made optional
                .appendLiteral('T')
                .append(ISO_LOCAL_TIME)
                .optionalStart()           // zone and offset made optional
                .appendOffsetId()
                .optionalStart()
                .appendLiteral('[')
                .parseCaseSensitive()
                .appendZoneRegionId()
                .appendLiteral(']')
                .optionalEnd()
                .optionalEnd()
                .optionalEnd()
                .toFormatter();

        ZonedDateTime zdt = null;

        TemporalAccessor ta = formatter.parseBest(value, ZonedDateTime::from, LocalDateTime::from, LocalDate::from );

        // this is the most likely case, a pure, date-only, ISO 8601 string in the form yyyy-mm-dd
        if (ta instanceof LocalDate) {
            var date =  (LocalDate) ta;
            var time = LocalTime.MIDNIGHT;
            var zid = ZoneOffset.UTC;
            zdt = ZonedDateTime.of(date, time, zid);
        }

        else if (ta instanceof ZonedDateTime) {
            zdt = (ZonedDateTime) ta;
        }

        else if (ta instanceof LocalDateTime) {
            var dt = (LocalDateTime) ta;
            zdt = ZonedDateTime.of(dt, ZoneOffset.UTC);
        }

        return zdt;

    }
}
