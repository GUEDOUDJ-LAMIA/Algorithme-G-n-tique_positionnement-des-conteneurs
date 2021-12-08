package PFE;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DATE  {

	// *************************ATTRIBUTS****************************//
	String date;

	// *********************** CONSTRUCERS **************************//
	public DATE() {
		// TODO Auto-generated constructor stub
	}
	public DATE(String date) {
		Date date0 = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		this.date = date;
		try {
			date0 = simpleDateFormat.parse(date);
		     } catch (ParseException e) {
		System.out.println("\n\t   foramt incorrect   \t\n");
			e.printStackTrace();} }
	

	// ************************** METHODES **************************//
	// ************************** AFFICHER **************************//
	boolean EG_Date(DATE d) throws ParseException {
		Date date = null, D2 = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date1 = this.date;
		String date2 = d.date;
			date = simpleDateFormat.parse(date1);
			D2 = simpleDateFormat.parse(date2);
		return (date.equals(D2));
	}
	void Afficher_DATE() throws ParseException {
		Date dat = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        dat = formatter.parse(this.date);
		DateFormat formatter1 = new SimpleDateFormat("EEEE, d MMM yyyy ");
		System.out.println(formatter1.format(dat));
	}
    String getDate() throws ParseException {
    	Date dat = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        dat = formatter.parse(this.date);
		DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy ");
		return formatter1.format(dat);
    	
    }
	// ************************** AVANT DATE **************************//
	boolean avant_Date(DATE d) throws ParseException {
		Date D1 = null, D2 = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date1 = this.date;
		String date2 = d.date;
		D1 = simpleDateFormat.parse(date1);
		D2 = simpleDateFormat.parse(date2);
		return (D1.before(D2));
	}

	// ************************** APRES DATE **************************//
	boolean Apres_Date(DATE d) throws ParseException {
		Date date = null, D2 = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date1 = this.date;
		String date2 = d.date;
			date = simpleDateFormat.parse(date1);
			D2 = simpleDateFormat.parse(date2);
		return (date.after(D2));
	}
	// ************************** Comparer DATE **************************//
     int compareTo(DATE d) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = sdf.parse(this.date);
		Date date2 = sdf.parse(d.date);
		//System.out.println("date1 : " + sdf.format(date1));
		//System.out.println("date2 : " + sdf.format(date2));
		if (date1.after(date2)) {
			return -1;
		} else if (date1.before(date2)) {
			return 1;
		} else
			return 0;

	}
}



	 
	

