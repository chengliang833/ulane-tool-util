package top.ulane.toolutil.script;

import java.util.Arrays;
import java.util.List;

import wang.ulane.image.ImageUtil;

public class CustomAll {
	private static List<String> noLogCommand = Arrays.asList("long_to_hex", "long_to_bina", "image_to_base64");
	
	public static void main(String[] args) throws Exception {
//		args = new String[]{"bc4_reset", "C:\\Users\\eshonulane\\Desktop\\temp\\Beyond Compare 4"};
//		args = new String[]{"file_changewrap_n", "C:\\Users\\eshonulane\\Desktop\\temp\\test"};
//		args = new String[]{"eclipse_terminal", "C:\\Users\\eshonulane\\Desktop\\temp\\test2\\workbench.xmi"};
//		args = new String[]{"file_list_copy"};
		
		if(args == null || args.length < 1){
			throw new RuntimeException("要入参的");
		}
		
		String param1 = args.length>1?args[1]:null;
		String param2 = args.length>2?args[2]:null;
		String param3 = args.length>3?args[3]:null;
		
		if(!noLogCommand.contains(args[0])){
			System.out.println("start...");
		}
		
		switch(args[0]){
			case "eclipse_terminal":
				EclipseTerminalStr.execute(param1, false);
				break;
			case "eclipse_terminal_workspace":
				EclipseTerminalStr.execute(param1, true);
				break;
			case "eclipse_svnname":
				EclipseSvnName.execute(param1);
				break;
			case "bc4_reset":
				BeyondCompare4Reset.execute(param1);
				break;
			case "file_changewrap_n":
				new FileChangeWrap("n", param1, param2).execute();
				break;
			case "file_changewrap_n_any":
				new FileChangeWrap("n", param1, param2, true).execute();
				break;
			case "file_changewrap_rn":
				new FileChangeWrap("rn", param1, param2).execute();
				break;
			case "file_list":
				new FileListAll(param1, param2, param3).execute();
				break;
			case "file_list_copy":
				FileListCopy.execute(param1);
				break;
			case "long_to_hex":
				System.out.print(NumericalConversion.longToHex(Long.parseLong(param1)));;
				break;
			case "long_to_bina":
				System.out.print(NumericalConversion.longToBina(Long.parseLong(param1)));
				break;
			case "image_to_base64":
				System.out.print("data:image/jpg;base64,"+ImageUtil.getImgStr(param1));
				break;
			default:
				System.out.println("no selected...");
		}
		if(!noLogCommand.contains(args[0])){
			System.out.println("finish...");
		}
	}
}
