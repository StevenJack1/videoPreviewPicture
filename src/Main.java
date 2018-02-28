import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Main {

    public static boolean processImg(String INPUT_PATH,String IMG_PATH,String time) {
        System.out.println("IMG_PATH="+IMG_PATH);
        if(new File(IMG_PATH).exists()) return true;
        List<String> commend = new java.util.ArrayList<String>();
        //ffmpeg地址，不需要带.exe后缀
        commend.add("E:\\ffmpeg\\bin\\ffmpeg");
        commend.add("-i");
        commend.add(INPUT_PATH);
        commend.add("-y");
        commend.add("-f");
        commend.add("image2");
        commend.add("-ss");
        commend.add(time);
        commend.add("-t");
        commend.add("0.001");
        commend.add("-s");
        commend.add("320x240");
        commend.add(IMG_PATH);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            builder.redirectErrorStream(true);
            System.out.println("视频截图开始...");

            Process process = builder.start();
            InputStream in =process.getInputStream();
            byte[] re = new byte[1024];
            System.out.print("正在进行截图，请稍候");
            while (in.read(re) != -1) {
                System.out.print(".");
            }
            System.out.println("");
            in.close();
            System.out.println("视频截图完成...");


            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    public static String encodeImgageToBase64(URL imageUrl) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
//        ByteArrayOutputStream outputStream = null;
//        try {
//            BufferedImage bufferedImage = ImageIO.read(imageUrl);
//            outputStream = new ByteArrayOutputStream();
//            ImageIO.write(bufferedImage, "jpg", outputStream);
//        } catch (MalformedURLException e1) {
//            e1.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // 对字节数组Base64编码
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
//    }

    public static String encodeImgageToBase64(File imageFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(imageFile));
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
    }

    public static void main(String[] args){
        String imagePath = "F://d.jpg";
        boolean img = processImg("E:\\考研项目\\04-基础班18版本【張宇】（正在看）\\2018高数新版\\导数\\01导数的定义（第二讲 一元函数微分学）.mp4", imagePath, "60");
        System.out.println(img);
        if (img){
            System.out.println(encodeImgageToBase64(new File(imagePath)));
        }


    }



}
