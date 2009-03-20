import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

public class Codegen {
	private static StringBuilder method = new StringBuilder();
	private static Formatter methodFormatter = new Formatter(method);
	
	private static StringBuilder parse = new StringBuilder();
	private static Formatter parseFormatter = new Formatter(parse);
	
	public static void main(String[] arg) throws Exception {
		String packageName = arg[0];
		String className = arg[1];
		Scanner constants = new Scanner(new File(arg[2]));
		Scanner opts = new Scanner(new File(arg[3]));
		Scanner header = new Scanner(new File(arg[4]));
		Scanner footer = new Scanner(new File(arg[5]));
		
		System.out.printf("package %s;\n", packageName);
		while(header.hasNextLine())
			System.out.println(header.nextLine());
		System.out.printf("public class %s {\n\tpublic %s() {}\n", className, className);
		System.out.printf("\n\tpublic %s(String[] arg) {\n\t\tLogManager lm = LogManager.getInstance();\n\t\tfor(String s: arg) {\n\t\t\ttry {\n", className);
		while(opts.hasNextLine()) {
			String[] token = readProperty(opts.nextLine());
			generateOption(token[0], token[1], token[2]);
		}
		while(constants.hasNextLine()) {
			String[] token = readProperty(opts.nextLine());
			generateOption(token[0], token[1], token[2]);
		}
		System.out.print(parse);
		System.out.printf("\t\t\t}\n\t\t\tcatch(Exception e) {\n\t\t\t\tlm.log(\"Error parsing argument: \" + s, \"PARSER\", Message.Type.ERROR);\n\t\t\t}\n");
		System.out.printf("\t\t}\n\t}\n", className);
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
		else
			parseFormatter.format("\t\t\t\t%s(s.matches(\"--%s=.+\"))\n\t\t\t\t\t%s = parse%s(s);\n", ifString(), optName, name, capitalize(type));
	}
	
	private static void generateConstant(String name, String type, String defaultValue) {
		methodFormatter.format("\n\tprivate %s %s = %s;\n", type, name, defaultValue);
		methodFormatter.format("\tpublic %s get%s() {\n", type, capitalize(name));
		methodFormatter.format("\t\treturn %s;\n", name);
		methodFormatter.format("\t}\n");		
	}
	
	private static String[] readProperty(String line) {
		String[] arr;
		String[] ret = new String[3];
		arr = line.split("=", 2);
		ret[0] = arr[0];
		arr = arr[1].split(":", 2);
		ret[1] = arr[0];
		ret[2] = arr[1];
		return ret;
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