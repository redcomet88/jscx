package jssvc.base.util;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * doc docx格式转换
 */
public class DocConverter {
    private static final int environment = 1;// 环境 1：windows 2:linux
    private String fileString;// (只涉及pdf2swf路径问题)
    private String outputPath = "";// 输入路径 ，如果不设置就输出在默认的位置
    private String pdfOutputPath;// PDF输出路径 ，如果不设置就输出在默认的位置，需要设置文件前设置
    private String pdfPath;//PDF文件绝对路径
    private String fileName;
    private static String[] docFileLayouts = {".txt",".doc",".docx",".wps",".xls",".xlsx",".et",".ppt",".pptx",".dps"};//office办公软件格式
    private static String[] imgFileLayouts = {".jpg",".gif",".jpeg",".png",".bmp"};//图片格式
    private static String[] pdfFileLayouts = {".pdf"};//pdf格式
    private File imgFile;
    private File oldFile;//原文件
    private File pdfFile;
    private File swfFile;
    private File docFile;

    private String pdf2swfPath;

    /**
     * 可预览的文件格式
     * @param fileName
     */
    public static String  getPreviewFileExt() {
        List list = new ArrayList(Arrays.asList(docFileLayouts));
        list.addAll(Arrays.asList(imgFileLayouts));
        list.addAll(Arrays.asList(pdfFileLayouts));
        Object[] c = list.toArray();
        //System.out.println(Arrays.toString(c));
        return Arrays.toString(c);
    }
    public DocConverter(String fileName) {
        ini(fileName);
    }

    public DocConverter() {
    }
    /**
     * 重新设置file
     * 
     * @param fileString
     */
    public void setFile(String fileName) {
        docFile=null;
        oldFile=null;
        imgFile=null;
        ini(fileName);
    }
    
    
    public void setPdfOutputPath(String pdfOutputPath){
        this.pdfOutputPath=pdfOutputPath;
        File file = new File(pdfOutputPath);
        if(!file.exists() && !file.isDirectory()){
            file.mkdirs();
        }
    }
    
    public String getPdfPath() {
        return pdfPath;
    }

    /**
     * 初始化
     * 
     * @param fileString
     */
    private void ini(String fileName) {
        this.fileString = fileName.replaceAll("\\\\", "/");
        fileName = fileString.substring(0, fileString.lastIndexOf("."));
        int type=fileString.lastIndexOf(".");
        String typeStr=fileString.substring(type).toLowerCase();
        if(Arrays.toString(docFileLayouts).contains(typeStr)){
            docFile = new File(fileString);
        }else if(Arrays.toString(imgFileLayouts).contains(typeStr)){
            imgFile = new File(fileString);
        }else if(Arrays.toString(pdfFileLayouts).contains(typeStr)){
            oldFile = new File(fileString);
        }
        if(null == pdfOutputPath){
            pdfPath=fileName + ".pdf";
        }else{
            pdfPath=pdfOutputPath+fileName.substring(fileName.lastIndexOf("/")) + ".pdf";
        }
        pdfFile = new File(pdfPath);
        //swfFile = new File(fileName.getAttachUrl()+"/"+fileName.getAdditionId() + ".swf");
    }

    /**
     * 转为PDF
     * 
     * @param file
     */
    private void doc2pdf() throws Exception {
        if (docFile!=null&&docFile.exists()) {
            if (!pdfFile.exists()) {
                OpenOfficeConnection connection = new SocketOpenOfficeConnection(
                        8100);
                try {
                    connection.connect();
                    DocumentConverter converter = new OpenOfficeDocumentConverter(
                            connection);
                    converter.convert(docFile, pdfFile);
                    // close the connection
                    connection.disconnect();
                    System.out.println("****pdf转换成功，PDF输出：" + pdfFile.getPath()
                            + "****");
                } catch (java.net.ConnectException e) {
                    e.printStackTrace();
                    System.out.println("****swf转换器异常，openoffice服务未启动！****");
                    throw e;
                } catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
                    e.printStackTrace();
                    System.out.println("****swf转换器异常，读取转换文件失败****");
                    throw e;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }finally{
                    connection.disconnect();
                }
            } else {
                System.out.println("****已经转换为pdf，不需要再进行转化****");
            }
        } else {
            System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
        }
    }
    
    /**
     * 将图片转换成pdf文件 imgFilePath 需要被转换的img所存放的位置。
     * 例如imgFilePath="D:\\projectPath\\55555.jpg"; pdfFilePath 转换后的pdf所存放的位置
     * 例如pdfFilePath="D:\\projectPath\\test.pdf";
     * 
     * @param image
     * @return
     * @throws IOException
     */

    private void imgToPdf() throws IOException {
        if (imgFile!=null&&imgFile.exists()) {
            if (!pdfFile.exists()) {
                Document document = new Document();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(pdfFile.getPath());
                    PdfWriter.getInstance(document, fos);

                    // 添加PDF文档的某些信息，比如作者，主题等等
                    //document.addAuthor("arui");
                    //document.addSubject("test pdf.");
                    // 设置文档的大小
                    document.setPageSize(PageSize.A4);
                    // 打开文档
                    document.open();
                    // 写入一段文字
                    // document.add(new Paragraph("JUST TEST ..."));
                    // 读取一个图片
                    Image image = Image.getInstance(imgFile.getPath());
                    float imageHeight = image.getScaledHeight();
                    float imageWidth = image.getScaledWidth();
                    int i = 0;
                    while (imageHeight > 500 || imageWidth > 500) {
                        image.scalePercent(100 - i);
                        i++;
                        imageHeight = image.getScaledHeight();
                        imageWidth = image.getScaledWidth();
                        //System.out.println("imageHeight->" + imageHeight);
                        //System.out.println("imageWidth->" + imageWidth);
                    }

                    image.setAlignment(Image.ALIGN_CENTER);
                    // //设置图片的绝对位置
                    // image.setAbsolutePosition(0, 0);
                    // image.scaleAbsolute(500, 400);
                    // 插入一个图片
                    document.add(image);
                } catch (DocumentException de) {
                    System.out.println(de.getMessage());
                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                }
                document.close();
                fos.flush();
                fos.close();
            }
        }
    }
    
    /**
     * 转换成 pdf
     */
    @SuppressWarnings("unused")
    private void pdfTopdf() throws Exception {
        Runtime r = Runtime.getRuntime();
        if (!pdfFile.exists()&&null !=oldFile && oldFile.exists()) {
            if (environment == 1) {// windows环境处理
                try { 
                    int bytesum = 0;
                    int byteread = 0;
                    File oldfile = new File(oldFile.getPath());
                    if (oldfile.exists()) { // 文件存在时
                        InputStream inStream = new FileInputStream(oldFile.getPath()); // 读入原文件
                        FileOutputStream fs = new FileOutputStream(pdfFile.getPath());
                        byte[] buffer = new byte[1444];
                        int length;
                        while ((byteread = inStream.read(buffer)) != -1) {
                            bytesum += byteread; // 字节数 文件大小
                            //System.out.println(bytesum);
                            fs.write(buffer, 0, byteread);
                        }
                        inStream.close();
                        fs.close();
                    }
                } catch (Exception e) {
                    System.out.println("复制单个文件操作出错");
                    e.printStackTrace();

                }
            } else if (environment == 2) {// linux环境处理
                
            }
        } 
    }

    /**
     * 转换成 swf
     */
    @SuppressWarnings("unused")
    private void pdf2swf() throws Exception {
        Runtime r = Runtime.getRuntime();
        if (!swfFile.exists()) {
            if (pdfFile.exists()) {
                if (environment == 1) {// windows环境处理
                    try {
                        // 从配置文件获取swfFile.exe 安装路径
                        InputStream in = DocConverter.class
                                .getClassLoader()
                                .getResourceAsStream(
                                        "parameters/flow/pdf2swfPath.properties");
                        Properties config = new Properties();
                        try {
                            config.load(in);
                            in.close();
                        } catch (IOException e) {
                            System.err
                                    .println("No AreaPhone.properties defined error");
                        }

                        if (config != null
                                && config.getProperty("pdf2swfPath") != null) {
                            pdf2swfPath = config.getProperty("pdf2swfPath")
                                    .toString();
                        }

                        Process p = r.exec(pdf2swfPath + " "+ pdfFile.getPath() + " -o "+ swfFile.getPath() + " -T 9");
                        swfFile = new File(swfFile.getPath());
                        //System.out.print(loadStream(p.getInputStream()));
                        //System.err.print(loadStream(p.getErrorStream()));
                        //System.out.print(loadStream(p.getInputStream()));
                        System.err.println("****swf转换成功，文件输出："+ swfFile.getPath() + "****");
                        /*if (pdfFile.exists()) {
                            pdfFile.delete();
                        }*/

                    } catch (IOException e) {
                        e.printStackTrace();
                        throw e;
                    }
                } else if (environment == 2) {// linux环境处理
                    try {
                        Process p = r.exec("pdf2swf " + pdfFile.getPath()
                                + " -o " + swfFile.getPath() + " -T 9");
                        //System.out.print(loadStream(p.getInputStream()));
                        //System.err.print(loadStream(p.getErrorStream()));
                        System.err.println("****swf转换成功，文件输出："
                                + swfFile.getPath() + "****");
                        /*if (pdfFile.exists()) {
                            pdfFile.delete();
                        }*/
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw e;
                    }
                }
            } else {
                System.out.println("****pdf不存在,无法转换****");
            }
        } else {
            System.out.println("****swf已经存在不需要转换****");
        }
    }

    

    static String loadStream(InputStream in) throws IOException {

        int ptr = 0;
        in = new BufferedInputStream(in);
        StringBuffer buffer = new StringBuffer();

        while ((ptr = in.read()) != -1) {
            buffer.append((char) ptr);
        }

        return buffer.toString();
    }

    /**
     * 转换主方法
     */
    @SuppressWarnings("unused")
    public boolean conver() {

        if (pdfFile.exists()) {
            System.out.println("****pdf转换器开始工作，该文件已经转换为pdf****");
            return true;
        }

        /*if (environment == 1) {
            System.out.println("****swf转换器开始工作，当前设置运行环境windows****");
        } else {
            System.out.println("****swf转换器开始工作，当前设置运行环境linux****");
        }*/
        try {
            pdfTopdf();
            doc2pdf();
            imgToPdf();
            //pdf2swf();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (pdfFile.exists()) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void main(String[] args){
        // 调用转换类DocConverter,并将需要转换的文件传递给该类的构造方法
        /*DocConverter d = new DocConverter("C:/Users/Administrator/Desktop/工作动态第19期.pdf");
        // 调用conver方法开始转换，先执行doc2pdf()将office文件转换为pdf;再执行pdf2swf()将pdf转换为swf;
        d.conver();
        // 调用getswfPath()方法，打印转换后的swf文件路径
        System.out.println(d.getswfPath());*/
     }
    

    /**
     * 返回文件路径
     * 
     * @param s
     */
    public String getPdfName() {
        //if (swfFile.exists()) {
            String tempString = pdfFile.getName();
            //tempString = tempString.replaceAll("\\\\", "/");
            return tempString;
        /*} else {
            return "";
        }*/

    }

    /**
     * 设置输出路径
     */
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
        if (!outputPath.equals("")) {
            String realName = fileName.substring(fileName.lastIndexOf("/"),
                    fileName.lastIndexOf("."));
            if (outputPath.charAt(outputPath.length()) == '/') {
                swfFile = new File(outputPath + realName + ".swf");
            } else {
                swfFile = new File(outputPath + realName + ".swf");
            }
        }
    }

}