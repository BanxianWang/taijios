package com.sghy1801.util;
import com.alibaba.nls.client.protocol.NlsClient;
import com.alibaba.nls.client.protocol.OutputFormatEnum;
import com.alibaba.nls.client.protocol.SampleRateEnum;
import com.alibaba.nls.client.protocol.tts.SpeechSynthesizer;
import com.alibaba.nls.client.protocol.tts.SpeechSynthesizerListener;
import com.alibaba.nls.client.protocol.tts.SpeechSynthesizerResponse;

import java.io.*;
import java.nio.ByteBuffer;

    /**
     * 语音合成（TTS）Demo
     */
    public class TTS {

        private String appKey;
        static NlsClient  client;

        public TTS(String appKey) {
            this.appKey = appKey;
        }
        static{
            client= new NlsClient("40a0a741bd2140c99704aeb4e141c384");
        }
        public static SpeechSynthesizerListener getSynthesizerListener() {
            SpeechSynthesizerListener listener = null;//语音合成监听类，监听返回结果。非线程安全
            try {
                listener = new SpeechSynthesizerListener() {

                    String rom = (int)(Math.random()*1000)+"";
                    String path ="F:\\TomCat\\apache-tomcat-9.0.14\\webapps\\statics\\tts"+rom+".wav";
                    AA aa =new AA(rom);
                    File f =new File(path);
                    FileOutputStream fout = new FileOutputStream(f);

                    byte[] bytesArray=null;
                    @Override// 语音合成结束
                    public void onComplete(SpeechSynthesizerResponse response) {

                        try {
                            System.out.println(f.delete());
                            fout.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // 事件名称 SynthesisCompleted
                        System.out.println("name: " + response.getName() +
                                // 状态码 20000000 表示识别成功
                                ", status: " + response.getStatus() +
                                // 语音合成文件路径
                                ", output file :"+ f .getAbsolutePath()
                        );



                    }


                    @Override// 语音合成的语音二进制数据
                    public void onMessage(ByteBuffer message) {
                        try {

                            //message.remaining()此方法返回剩余的可用长度，此长度为实际读取的数据长度
                            bytesArray = new byte[message.remaining()];
                            message.get(bytesArray, 0, bytesArray.length);
                            System.out.println("write array:" + bytesArray.length);
                            fout.write(bytesArray);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listener;
        }

        public void process(String text) {
            SpeechSynthesizer synthesizer = null;
            try {
                // Step1 创建实例,建立连接,语音合成处理类，设置请求参数，发送请求。非线程安全。
                synthesizer = new SpeechSynthesizer(client, getSynthesizerListener());
                synthesizer.setAppKey(appKey);

                // 设置返回音频的编码格式
                synthesizer.setFormat(OutputFormatEnum.WAV);
                // 设置返回音频的采样率
                synthesizer.setSampleRate(SampleRateEnum.SAMPLE_RATE_16K);
                // 设置用于语音合成的文本
                System.err.println(text);
                String s= new String(text.getBytes("gbk"),"UTF-8");
                // System.out.println(s);
                synthesizer.setText(s);

                // Step2 此方法将以上参数设置序列化为json发送给服务端,并等待服务端确认
                synthesizer.start();

                // Step3 等待语音合成结束
                synthesizer.waitForComplete();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } finally {
                // Step4 关闭连接
                if (null != synthesizer) {
                    synthesizer.close();
                }
            }
        }

        public void shutdown() {
            client.shutdown();
        }



    }


