package top.ulane.toolutil.script;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import wang.ulane.util.ClipboardUtil;

public class FileListAll {
	
	private List<FileInfo> fileInfos = new ArrayList<>();
	private String rootPath;
	private String pathType;
	private String orderBy;
	
	public FileListAll(String rootPath, String pathType) {
		init(rootPath, pathType, null);
	}
	public FileListAll(String rootPath, String pathType, String orderBy) {
		init(rootPath, pathType, orderBy);
	}
	public void init(String rootPath, String pathType, String orderBy){
		if(rootPath == null || pathType == null){
			throw new RuntimeException("路径和类型不可为空");
		}
		this.rootPath = rootPath;
		this.pathType = pathType;
		this.orderBy = orderBy;
	}


	/**
	 * 
	 * @param filePath
	 * @param pathType absolute relate filename
	 */
	public void execute(){
		File file = new File(rootPath);
		if(file.isFile()){
			addPath(file);
		}else{
			traverse(file);
		}
		DecimalFormat df = new DecimalFormat("###,###");
		String text;
		if("size".equals(orderBy)){
			fileInfos.sort((o1,o2)->o2.getSize().compareTo(o1.getSize()));
			text = fileInfos.stream().map(o->o.getPath()+"\t"+df.format(o.getSize())).collect(Collectors.joining("\r\n"));
		}else{
			text = fileInfos.stream().map(FileInfo::getPath).collect(Collectors.joining("\r\n"));
		}
		ClipboardUtil.setSysClipboardText(text);
	}

    public void traverse(File file){
    	if(file.isDirectory()){
    		for(File f:file.listFiles()){
    			traverse(f);
    		}
    	}else{
    		addPath(file);
    	}
    }
    
    public void addPath(File file){
    	String path = null;
    	switch(pathType){
    		case "absolute":
    			path = file.getAbsolutePath();
    			break;
    		case "filename":
    			path = file.getName();
    			break;
    		case "relate":
			default:
				path = file.getAbsolutePath().replace(rootPath, "");
				if(path.startsWith(File.separator)){
					path = path.substring(1);
				}
    	}
    	fileInfos.add(new FileInfo(path.replace("\\", "/"), file.length()));
    }
    
    public static void main(String[] args) {
		new FileListAll("D:\\My_Hire\\temp\\dist2", "absolute", "size").execute();;
		System.out.println(ClipboardUtil.getSysClipboardText());
	}

	public static class FileInfo{
		private String path;
		private Long size;

		public FileInfo(String path, Long size) {
			this.path = path;
			this.size = size;
		}

		public String getPath() {
			return path;
		}

		public Long getSize() {
			return size;
		}
	}

}
