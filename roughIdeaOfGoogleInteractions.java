//this is PURELY PSEUDOCODE

Connection conn = new Connection("www.google.com", 80, "username", "password");

HTTPRequest req = {
	inputText: "i want a hamburger",
	translateInto: "russian"
};

String path = "/cooltranslateservice.php";

HTTPResponse response = conn.sendRequest(req, path);

/*HTTPResponse h = {
	translationOutput: "я хочу гамбургер",

};*/

HTTPRequest backToEnglish = {
	inputText: response.translationOutput,
	translateInto: "english"
};

HTTPResponse engTranslation = conn.sendRequest(backToEnglish, path);


System.out.println("We just translated " + req.inputText + " into the " + req.translateInto + " phrase:\t" + response.translationOutput + "\nIt Came back in " + backToEnglish.translateInto + " as \t" + engTranslation.translationOutput);