import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectConstants {

	public static Date formatDate(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

		String dateToBeUsedInQuery = sdf.format(new Date());

		Date dateToReturn = null;
		try {
			dateToReturn = sdf.parse(dateToBeUsedInQuery);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateToReturn;

	}

}
