package com.test.springboot.demo.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.junit.Test;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.AudioInfo;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.VideoAttributes;
import ws.schild.jave.VideoInfo;
import ws.schild.jave.VideoSize;

public class FileTest {

    /**
     * 下载文件
     */
    @Test
    public void download() {
        try {
            String webUrl = "web.jpg"; // web地址
            String localPath = "local.jpg"; // 本地地址
            URL url = new URL(webUrl);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(localPath));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 按创建时间排序：升序
     */
    @Test
    public void filterByCreateTime() {
        File file = new File("d:\\aaa");
        File[] files = file.listFiles();
        Arrays.sort(files, (File f1, File f2) -> {
            long diff = f1.lastModified() - f2.lastModified();
            if (diff > 0) {
                return 1;
            } else if (diff == 0) {
                return 0;
            } else {
                return -1;
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (File f : files) {
            System.out.println(f.getName() + ":" + sdf.format(f.lastModified()));
        }
    }

    /**
     * 根据正则匹配
     */
    @Test
    public void matchesFile() {
        File file = new File("d:\\aaa");
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().matches("^1\\d{1}\\.jpg$"); // 匹配上的读取
            }
        });

        for (File f : files) {
            System.out.println(f.getName());
        }
    }

    /**
     * 视频转码
     * @throws Exception
     */
    @Test
    public void transcod() throws Exception {
        File source = new File("d:/source.mp4");
        File target = new File("d:/target.mp4");

        System.out.println("源视频信息");
        MultimediaObject object = new MultimediaObject(source);
        MultimediaInfo info = object.getInfo();
        System.out.println("格式format：" + info.getFormat());
        System.out.println("时长duration：" + info.getDuration());
        VideoInfo videoInfo = info.getVideo();
        System.out.println("编码decoder：" + videoInfo.getDecoder());
        System.out.println("码率bitRate：" + videoInfo.getBitRate());
        System.out.println("帧率frameRate：" + videoInfo.getFrameRate());
        VideoSize videoSize = videoInfo.getSize();
        System.out.println("分辨率：" + videoSize.getWidth() + " x " + videoSize.getHeight());
        AudioInfo audioInfo = info.getAudio();
        System.out.println("编码decoder：" + audioInfo.getDecoder());
        System.out.println("码率bitRate：" + audioInfo.getBitRate());
        System.out.println("声道channels：" + audioInfo.getChannels());
        System.out.println("采样率samplingRate：" + audioInfo.getSamplingRate());

        // 视频属性
        VideoAttributes video = new VideoAttributes();
        video.setCodec("libx264");
        video.setBitRate(256000); // 码率：256kbps
        video.setFrameRate(25); // 帧率：25fps
        int width = 640; // 固定宽度
        int height = width * videoSize.getHeight() / videoSize.getWidth();
        height = height % 2 == 0 ? height : height + 1; // 视频宽高必须是偶数
        video.setSize(new VideoSize(width, height));
        // 音频属性
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("aac");
        audio.setBitRate(64000); // 码率：64kbps
        audio.setSamplingRate(24000); // 采样率：24kHz
        // 转码属性
        EncodingAttributes attributes = new EncodingAttributes();
        attributes.setVideoAttributes(video);
        attributes.setAudioAttributes(audio);
        attributes.setFormat("mp4");

        Encoder encoder = new Encoder();
        encoder.encode(object, target, attributes);

        System.out.println("转码完成：新分辨率：" + width + " x " + height);
    }
    
    /**
     * @param imgMap 图片地址
     * @param mp4SavePath 视频地址
     * @param width 视频宽
     * @param height 视频高
     * 
     * @return int 0成功，-1失败
     */
    @SuppressWarnings("resource")
    public static int pic2Video(final String imgPath, final String mp4Path, final int width, final int height) {
        //读取所有图片
        File file = new File(imgPath);
        File[] files = file.listFiles();
        if(files == null || files.length < 1){
            return -1;
        }
        // 根据创建时间升序排序
        Arrays.sort(files, (File f1, File f2) -> {
            long diff = f1.lastModified() - f2.lastModified();
            if (diff > 0) {
                return 1;
            } else if (diff == 0) {
                return 0;
            } else {
                return -1;
            }
        });
        Map<Integer, File> imgMap = new HashMap<Integer, File>();
        int num = 0;
        for (File imgFile : files) {
            imgMap.put(num, imgFile);
            num++;
        }
        //视频宽高最好是按照常见的视频的宽高 16:9 或者 9:16
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(mp4Path, width, height);
        //设置视频编码层模式
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        //设置视频为25帧每秒
        recorder.setFrameRate(25);
        //设置视频图像数据格式
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        recorder.setFormat("mp4");
        try {
            recorder.start();
            Java2DFrameConverter converter = new Java2DFrameConverter();
            //录制一个和图片个数相同秒的视频
            for (int i = 0; i < files.length; i++) {
                BufferedImage read = ImageIO.read(imgMap.get(i));
                //一秒是25帧,所以要记录25次
                for (int j = 0; j < 25; j++) {
                    recorder.record(converter.getFrame(read));
                }
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                recorder.stop();
                recorder.release();
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

}
