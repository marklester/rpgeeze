import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

public class Codegen {
	private static StringBuilder method = new StringBuilder();
	private static Formatter methodFormatter = new Formatter(method);
	
	private static StringBuilder parse = new StringBuilder();
	private static Formatter parseFormatter = new Formatter(parse);
	
	public static void main(String[] arg) throws Exception {
		String packageName = arg[0];
		HashMap<String, String> opts = readProperties(new FileInputStream(arg[1]));
		Scanner header = new Scanner(new File(arg[2]));
		Scanner footer = new Scanner(new File(arg[3]));
		
		System.out.printf("package %s;\n", packageName);
		while(header.hasNextLine())
			System.out.println(header.nextLine());
		for(String s: opts.keySet()) {
			String[] token = readProperty(s, opts.get(s));
			generateOption(token[0], token[1], token[2]);
		}
/*		for(String s: constants.keySet()) {
			String[] token = readProperty(s, constants.get(s));
			generateConstant(token[0], token[1], token[2]);
		}*/
		System.out.print(parse);
		System.out.printf("\t\t\t}\n\t\t\tcatch(Exception e) {\n\t\t\t\tlm.log(\"Error parsing argument: \" + s, \"PARSER\", Message.Type.ERROR);\n\t\t\t}\n");
		System.out.printf("\t\t}\n\t}\n");
		System.out.print(method);
		while(footer.hasNextLine())
			System.out.println(footer.nextLine());
	}
	
	private static void generateOption(String name, String type, String defaultValue) {
		generateConstant(name, type, defaultValue);
		String optName = name.replaceAll("([A-Z]+)", "-$1").toLowerCase();
		if(type.equals("boolean")) {
			parseFormatter.format("\t\t\t\t%s(s.matches(\"--%s\"))\n\t\t\t\t\t%s = true;\n", ifString(), optName, name);
			parseFormatter.format("\t\t\t\t%s(s.matches(\"--no-%s\"))\n\t\t\t\t\t%s = false;\n", ifString(), optName, name);
		}
//		else if(type.matches(".+\\[\\]$")) {
//			parseFormatter.format("\t\t\t\t%s(s.matches(\"--%s=.+\"))\n\t\t\t\t\t%s = parse%sArray(s);\n", ifString(), optName, name, capitalize(type.replaceAll("\\[\\]$", "")));
//		}
		else {
			parseFormatter.format("\t\t\t\t%s(s.matches(\"--%s=.+\"))\n\t\t\t\t\t%s = parse%s(s);\n", ifString(), optName, name, capitalize(type));
		}
	}
	
	private static void generateConstant(String name, String type, String defaultValue) {
		methodFormatter.format("\n\tprivate %s %s = %s;\n", type, name, defaultValue);
		methodFormatter.format("\tpublic %s get%s() {\n", type, capitalize(name));
		methodFormatter.format("\t\treturn %s;\n", name);
		methodFormatter.format("\t}\n");		
	}
	
	private static HashMap<String, String> readProperties(InputStream stream) throws IOException {
		Properties prop = new Properties();
		prop.load(stream);
		Enumeration<?> en = prop.propertyNames();
		HashMap<String, String> ret = new HashMap<String, String>();
		while(en.hasMoreElements()) {
			String key = en.nextElement().toString();
			ret.put(key, prop.getProperty(key));
		}
		return ret;
	}
	
	private static String[] readProperty(String key, String value) {
		String[] arr = value.split(":", 2);
		return new String[] {key, arr[0], arr[1]};
	}
	
	private static String capitalize(String s) {
		return (s.charAt(0) + "").toUpperCase() + s.substring(1);
	}
	
	private static String ifString = "if";
	private static String ifString() {
		String ret = ifString;
		ifString = "else if";
		return ret;
	}
}