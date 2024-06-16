package top.ulane.toolutil.script;

import wang.ulane.util.ClipboardUtil;
import wang.ulane.word.WordUtil;

import java.io.File;

public class DocToString {

    public static void execute(String filePath) throws Exception{
        String str = WordUtil.readDocxToString(new File(filePath));
        ClipboardUtil.setSysClipboardText(str);
    }

}
