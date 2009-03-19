import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

public class Autogen {
	public static void main(String[] arg) throws Exception {
		String packageName = arg[0];
		String className = arg[1];
		Scanner header = new Scanner(new File(arg[2]));
		Scanner footer = new Scanner(new File(arg[3]));
		Scanner in = new Scanner(System.in);
		
		StringBuilder code = new StringBuilder();
		Formatter codeFormatter = new Formatter(code);
		StringBuilder parse = new StringBuilder();
		Formatter parseFormatter = new Formatter(parse);
		
		System.out.printf("package %s;\n", packageName);
		while(header.hasNextLine())
			System.out.println(header.nextLine());
		System.out.printf("public class %s {\n\tpublic %s() {}\n", className, className);
		System.out.printf("\n\tpublic %s(String[] arg) {\n\t\tfor(String s: arg) {\n", className);
		boolean first = true;
		while(in.hasNextLine()) {
			String beforeIf = "else ";
			if(first) {
				beforeIf = "";
				first = false;
			}
			String[] token = in.nextLine().split(" ");
			String type = token[0];
			String name = token[1];
			String defaultValue = token[2];
			codeFormatter.format("\n\tprivate %s %s = %s;\n", type, name, defaultValue);
			codeFormatter.format("\tpublic %s get%s() {\n", type, capitalize(name));
			codeFormatter.format("\t\treturn %s;\n", name);
			codeFormatter.format("\t}\n");
			parseFormatter.format("\t\t\t%sif(s.matches(\"--%s=.+\"))\n\t\t\t\t%s = parse%s(s);\n", beforeIf, name, name, capitalize(type));
		}
		System.out.print(parse);
		System.out.printf("\t\t}\n\t}\n", className);
		System.out.print(code);
		while(footer.hasNextLine())
			System.out.println(footer.nextLine());
	}
	
	private static String capitalize(String s) {
		return (s.charAt(0) + "").toUpperCase() + s.substring(1);
	}
}