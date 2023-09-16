package cs211.project.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateTimeService {
    public static String getCurrentDate() {
        LocalDate now = LocalDate.now(ZoneId.of("Asia/Bangkok"));

        int buddhistEraYear = now.getYear() + 543;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);

        String formattedDate = String.format("%02d", now.getDayOfMonth()) + "/" +
                String.format("%02d", now.getMonthValue()) + "/" +
                String.format("%04d", buddhistEraYear);

        return formattedDate;
    }

    public static String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String formattedTime = currentTime.format(timeFormatter);

        return formattedTime;
    }

    public static String toString(String inputDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date date = inputFormat.parse(inputDate);

            int gregorianYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));

            SimpleDateFormat thaiDateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("th", "TH"));
            String thaiDate = thaiDateFormat.format(date);

            return thaiDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid Date";
        }
    }

    public static String getCurrentDateTime() {
        return getCurrentDate() + " " + getCurrentTime();
    }
}
