import com.googler.Translator;


public class GooglerExample {
	
	public static void main(String[] args){
		//Create a new translator for translating to russian
		Translator translator = new Translator("ru");
		
		//Alternatively could set language this way:
		//translator.setLanguage("ru");
		
		//Translate a String english => russian
		String input = "hello!";
		String translation = translator.getTranslation(input);
		System.out.println(translation);
		
		//Translator a String russian => english (again must set language to a new one)
		translation = translator.getTranslation(translation, "en");
		System.out.println(translation);
		//Alternatively could set language this way:
		//translator.setLanguage("en");
		
		//Let's find the Yandex query results: CHANGE "thorrism" to YOUR ACCOUNT
		int query = translator.getQuery(translation, "thorrism");
		System.out.println(translation + " has: " + query
				+ " results on Yandex!");
	}
}
