package interfaces;
import com.googler.Translator;


public class GooglerExample {
	
	public static void main(String[] args){
		//Create a new translator for translating to russian
		//	Translator impl = new Translator("ru"); IF YOU DONT HAVE ACCT INFO
		Translator impl = new Translator("thorrism", "03.315902810:cfeda0c0b0a70c46080a34ffa9e3cea5", "ru");
		
		//Alternatively could set language this way:
		//translator.setLanguage("ru");
		
		//Translate a String english => russian
		String input = "hello!";
		String translation = impl.getTranslation(input);
		System.out.println(translation);
		
		//Translator a String russian => english (again must set language to a new one)
		translation = impl.getTranslation(translation, "en");
		System.out.println(translation);
		//Alternatively could set language this way:
		//translator.setLanguage("en");
		
		//Let's find the Yandex query results: CHANGE "thorrism" to YOUR ACCOUNT
		int query = impl.getQuery(translation);
		System.out.println(translation + " has: " + query
				+ " results on Yandex!");
	}
}
