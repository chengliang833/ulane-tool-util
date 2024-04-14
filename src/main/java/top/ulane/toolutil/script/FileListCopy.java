package top.ulane.toolutil.script;

import wang.ulane.file.FileDeepCopy;
import wang.ulane.util.ClipboardUtil;
import wang.ulane.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FileListCopy {

    private static String toPath = System.getProperties().getProperty("user.home") + "/Desktop/temp/fileCopy/";

    public static void execute(String copyType) throws IOException {
        File toDir = new File(toPath);
        if(!toDir.exists()){
            toDir.mkdirs();
        }
        String filePathStr = ClipboardUtil.getSysClipboardText();
        String[] filePaths = filePathStr.split("[,\r\n]+");
        FileDeepCopy fileDeepCopy = new FileDeepCopy();
        File samePathDir = null;
        if("relate".equals(copyType)){
            String samePrePath = StringUtils.samePreStr(Arrays.asList(filePaths)).replace("\\", "/");
            if(!samePrePath.endsWith("/")){
                int lastPathIdx = samePrePath.lastIndexOf("/");
                if(lastPathIdx > -1){
                    samePrePath = samePrePath.substring(0, lastPathIdx);
                }else{
                    samePrePath = null;
                }
            }
            if (!StringUtils.isEmpty(samePrePath)) {
                samePathDir = new File(samePrePath);
            }
        }
        for(String filePath:filePaths){
            if(filePath != null && !filePath.equals("")){
                if(samePathDir == null){
                    fileDeepCopy.traverseWithOwn(filePath, toPath);
                }else{
                    fileDeepCopy.traverse(new File(filePath), samePathDir, toPath);
                }
            }
        }

    }

}
