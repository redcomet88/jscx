package jssvc.base.util;

import jssvc.base.exception.BusinessException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {

    /**
     * 获得服务器文件名
     * @param 文件名
     * @return 服务器文件名
     */
    public static String getNewFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index <= 0) {
            return "" + new Date().getTime();
        }
        String str2 = fileName.substring(index);
        String result = new Date().getTime() + str2;
        return result;
    }

    /**
     * 获得文件名后缀部分以外的内容
     * @param 文件名
     * @return 文件名后缀部分以外
     */
    public static String getFileNameWithoutDot(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index <= 0) {
            return fileName;
        }
        String result = fileName.substring(0, index);
        return result;
    }

    /**
     * 获得文件名后缀的内容
     * @param 文件名
     * @return 文件名后缀
     */
    public static String getFileNameAfterDot(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index <= 0) {
            return "";
        }
        String result = fileName.substring(index);
        return result;
    }

    /**
     * 删除单个文件
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除单个文件
     * @param file 文件对象
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(File file) {
        boolean flag = false;
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 读取文件
     * @param 文件路径
     * @return 文件内容
     */
    public static ArrayList<String> readFile(String path) throws IOException {
        File file = new File(path);
        ArrayList<String> contents = readFilePath(file);
        return contents;

    }

    /**
     * 读取文件
     * @param 文件路径
     * @return 文件内容
     */
    public static ArrayList<String> readFilePath(File file) throws IOException {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        switch (p) {
        case 0xefbb:
            code = "UTF-8";
            break;
        case 0xfffe:
            code = "Unicode";
            break;
        case 0xfeff:
            code = "UTF-16BE";
            break;
        default:
            code = "GBK";
        }
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), code);
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        ArrayList<String> contents = new ArrayList<String>();
        while ((lineTxt = bufferedReader.readLine()) != null) {
            contents.add(lineTxt);
        }
        bufferedReader.close();
        read.close();
        return contents;

    }

    /**
     * 导出excel
     * @param request
     * @param response
     * @param wb
     * @param filename
     */
    public static void download(HttpServletRequest request, HttpServletResponse response, HSSFWorkbook wb, String filename) {
        try {
            OutputStream out = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1") + ".xls");
            // 定义输出类型
            response.setContentType("application/ms-excel");
            wb.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件
     * @param request
     * @param response
     * @param file
     * @param filename
     */
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file, String filename) {
        try {
            OutputStream out = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1"));
            // 定义输出类型
            response.setContentType("application/octet-stream");
            InputStream is = new FileInputStream(file);
            int b;
            while ((b = is.read()) != -1) {
                out.write(b);
            }
            is.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文档模板
     * 
     * @param filename
     * @param 文件信息返回值
     */
    public static HSSFWorkbook getTemplate(String filename) {
        File file = new File(filename);
        HSSFWorkbook wb = null;
        try {
            if (file.exists()) {
                POIFSFileSystem fs = null;
                fs = new POIFSFileSystem(new FileInputStream(filename));
                wb = new HSSFWorkbook(fs);
            } else {
                throw new IOException("no such file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static void upFileToSCP(String address, String fileName, String serverDirName) throws Exception {
        String scp_address = ResourceUtil.getText(address);
        if (scp_address.split("@").length < 2) {
            throw new Exception("文件传输服务地址配置错误，格式为“用户名@服务器地址”！");
        }
        Runtime.getRuntime().exec("scp ./file/" + fileName + " " + scp_address + ":./" + serverDirName);
    }

    /**
     * @Description 获取文件或文件夹中所有的文件
     * @param file 文件对象
     * @param typeSet 文件后缀类型
     * @return listFiles 文件集合
     * @throws BusinessException 文件不存在时
     * @throws IOException
     */
    public static List<File> getFiles(File file) throws IOException {
        List<File> files = new ArrayList<>();
        if (file.exists()) {
            if (file.isDirectory()) {
                getFiles0(file, files);
            } else {
                files.add(file);
            }
        } else {
            throw new IOException("no such file");
        }

        return files;
    }

    private static void getFiles0(File dir, List<File> list) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                // 递归获取文件夹下所有文件
                getFiles0(file, list);
            } else {
                list.add(file);
            }
        }
    }
    
    
    /**
     * 批量下载文件
     * @param request
     * @param response
     * @param fileMap <fileName, filePath>
     */
    public static void downloadFileMulti(HttpServletRequest request, HttpServletResponse response,
                                         Map<String, String> fileMap, String zipName) {
        try {
            OutputStream out = response.getOutputStream();
            ZipOutputStream zipOut = new ZipOutputStream(out);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(zipName.getBytes("gb2312"), "ISO8859-1"));
            // 定义输出类型
            response.setContentType("application/octet-stream");
            // 遍历hashmap
            Iterator iter = fileMap.entrySet().iterator();
            List<File> files = new ArrayList<File>();
            List<String> fileNames = new ArrayList<String>();
            while (iter.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
                String fileName = entry.getKey(); // 文件名
                String filePath = entry.getValue();
                File file = new File(filePath);
                if (!file.isFile()) {
                    continue;
                }
                fileNames.add(fileName);
                files.add(file);
            }
            zipFile(files, fileNames, zipOut);
            zipOut.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zipFile(List<File> files, List<String> fileNames, ZipOutputStream zipOut) {
        int size = files.size();
        for (int i = 0; i < size; i++) {
            File file = files.get(i);
            String fileName = fileNames.get(i);
            zipFile(file, fileName, zipOut);
        }
    }
    
    public static void zipFile(File file, String fileName, ZipOutputStream zipOut) {
        try {
            if (file.exists()) {
                /** 如果是目录的话，这里不采取操作 **/
                if (file.isFile()) {
                    byte[] buffer = new byte[1024];
                    FileInputStream fis = new FileInputStream(file);
                    zipOut.putNextEntry(new ZipEntry(fileName));
                    int len = 0;
                    // 读取文件的内容，打包到zip文件
                    while ((len = fis.read(buffer)) > 0) {
                        zipOut.write(buffer, 0, len);
                    }
                    zipOut.flush();
                    zipOut.closeEntry();
                    fis.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    /**
     * 将多个二进制文件打包成zip文件
     * @param request
     * @param response
     * @param fileMap <fileName, filePath>
     * @throws IOException 
     */
    public static void downloadZipByteDataFiles(HttpServletRequest request, HttpServletResponse response,
                                                Map<String,byte[]>fileDataMap, String zipName) throws IOException{
        try {
            OutputStream out = response.getOutputStream();
            ZipOutputStream zipOut = new ZipOutputStream(out);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(zipName.getBytes("gb2312"), "ISO8859-1"));
            // 定义输出类型
            response.setContentType("application/octet-stream");
            // 遍历hashmap
            Iterator<String> iter = fileDataMap.keySet().iterator();
            String fileName  = "";
            byte[] byteData =null;
            while (iter.hasNext()) {
               fileName = iter.next();
               zipOut.putNextEntry(new ZipEntry(fileName));
               byteData =fileDataMap.get(fileName);
               zipOut.write(byteData, 0, byteData.length);
            }
            zipOut.flush();
            zipOut.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e ;
        }
    }
    
    /**
     * BASE64编码函数
     * @param bt
     */
    public static String base64Encode(byte[] bt) {
        if (bt != null) {
            return new sun.misc.BASE64Encoder().encode(bt).replace("\n", "").replace("\r", "");
        }
        return "";
    }

    /**
     * BASE64解码函数
     * @param bt
     */
    public static byte[] base64Decode(byte[] bt) {
        if (bt != null) {
            try {
                return new sun.misc.BASE64Decoder().decodeBuffer(new ByteArrayInputStream(bt));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    /**
     * base64还原成文件放在指定目录
     * @param base64编码文件内容
     * @param 文件路径
     * @param 文件名
     * @return true
     * @throws IOException
     */
    public static boolean uploadFileByBase64(String fileContent, String filePath, String fileName) {
        try {
            byte[] contentByte = base64Decode(fileContent.getBytes());
            File path = new File(filePath);
            if (!path.exists()) {
                path.mkdirs();
            }
            File file = new File(path, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream out = new FileOutputStream(file);
            out.write(contentByte);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除文件夹
     * @param path 文件夹绝对路径
     * @return
     */
    public static boolean deleteAllFile(String path){
        boolean flag = true;
        if(null == path || "".equals(path)){
            return  false;
        }
        File file = new File(path);
        if(file.exists()){
            if(file.isDirectory()){
                String[] tempList = file.list();
                File file1 = null;
                for(String temp : tempList){
                  if(!path.endsWith(File.separator)){
                      path = path + File.separator;
                  }
                  file1 = new File(path + temp);
                  if(file1.isFile()){
                      flag = flag && file1.delete();
                  }else if(file1.isDirectory()){
                      flag = flag && deleteAllFile(file1.getPath());
                  }
                }
                flag = flag && file.delete();
                return flag;
            } else if(file.isFile()){
                return file.delete();
            }
        }
        return false;
    }

    /**
     * @author 蔡江蘇
     * @description 生成指定名称的zip文件到指定的目录
     * @create 2018/8/7 9:27
     * @param sourceFilesPath
     * @param zipFilePath
     * @param zipFileName
     * @param zipFileCatalog
     * @return String
     * @throws
     */
    public static String generateZipFile(List<String> sourceFilesPath, String zipFilePath, String zipFileName, String zipFileCatalog) {

        String zipFileLocation = null;
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        FileOutputStream fileOutputStream = null;
        ZipOutputStream zipOutputStream = null;

        try {

            File zipFile = new File(zipFilePath, zipFileName);

            if (zipFile.exists()) {

                System.out.println(zipFilePath+"目录下存在名字为："+zipFileName+"的压缩包，即将删除原有的文件");
                zipFile.delete();
            }

            fileOutputStream = new FileOutputStream(zipFile);
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
            byte[] buffers = new byte[1024*10];
            int successCount = 0;

            for (String sourceFilePath: sourceFilesPath) {

                File sourceFile = new File(sourceFilePath);

                if (!sourceFile.exists()) {

                    System.out.println("待压缩的文件："+sourceFilePath+" 不存在");
                    continue;
                }
                else {

                    //创建ZIP实体，并加入压缩包
                    String sourceFileName = sourceFile.getName();
                    if (!StringUtil.isEmpty(zipFileCatalog)) sourceFileName = zipFileCatalog+File.separator+sourceFileName;
                    ZipEntry zipEntry = new ZipEntry(sourceFileName);
                    zipOutputStream.putNextEntry(zipEntry);

                    fileInputStream = new FileInputStream(sourceFile);
                    bufferedInputStream = new BufferedInputStream(fileInputStream, 1024*10);
                    int length = 0;
                    while ((length=bufferedInputStream.read(buffers, 0, 1024*10)) != -1) zipOutputStream.write(buffers, 0, length);
                    successCount += 1;
                    bufferedInputStream.close();
                    fileInputStream.close();
                }
            }

            if (successCount == sourceFilesPath.size()) zipFileLocation = zipFile.getPath();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (zipOutputStream != null) zipOutputStream.close();
                if (fileOutputStream != null) fileOutputStream.close();

            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        return zipFileLocation;
    }
}
