package org.dromara.common.core.utils;

import cn.hutool.core.codec.Base64;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.*;

/**
 * 文本压缩工具
 *
 * @author chanbeiyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompressUtils {

    /**
     * 将字符串进行 gzip 压缩
     *
     * @param data 源字符串
     * @return 压缩后的字符串
     */
    public static String gzip(String data) throws IOException {
        if (StringUtils.isBlank(data)) {
            return null;
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(out)) {
            gzip.write(data.getBytes(StandardCharsets.UTF_8));
            return Base64.encode(out.toByteArray());
        }
    }

    /**
     * 将字符串进行 gzip 解压缩
     *
     * @param data 源字符串
     * @return 解压缩后的字符串
     */
    public static String unGzip(String data) throws IOException {
        if (StringUtils.isBlank(data)) {
            return null;
        }
        byte[] decode = Base64.decode(data);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ByteArrayInputStream in = new ByteArrayInputStream(decode);
             GZIPInputStream gzipStream = new GZIPInputStream(in)) {
            byte[] buffer = new byte[256];
            int n;
            while ((n = gzipStream.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString(StandardCharsets.UTF_8);
        }
    }


    /**
     * 将字符串进行 zip 压缩
     * <a href="https://www.yiibai.com/javazip/javazip_deflater.html#article-start">...</a>
     * 0 ~ 9 压缩等级 低到高
     * public static final int BEST_COMPRESSION = 9;            最佳压缩的压缩级别。
     * public static final int BEST_SPEED = 1;                  压缩级别最快的压缩。
     * public static final int DEFAULT_COMPRESSION = -1;        默认压缩级别。
     * public static final int DEFAULT_STRATEGY = 0;            默认压缩策略。
     * public static final int DEFLATED = 8;                    压缩算法的压缩方法(目前唯一支持的压缩方法)。
     * public static final int FILTERED = 1;                    压缩策略最适用于大部分数值较小且数据分布随机分布的数据。
     * public static final int FULL_FLUSH = 3;                  压缩刷新模式，用于清除所有待处理的输出并重置拆卸器。
     * public static final int HUFFMAN_ONLY = 2;                仅用于霍夫曼编码的压缩策略。
     * public static final int NO_COMPRESSION = 0;              不压缩的压缩级别。
     * public static final int NO_FLUSH = 0;                    用于实现最佳压缩结果的压缩刷新模式。
     * public static final int SYNC_FLUSH = 2;                  用于清除所有未决输出的压缩刷新模式; 可能会降低某些压缩算法的压缩率。
     *
     * @param data 源字符串
     * @return 压缩后的字符串
     */
    public static String zip(String data) throws IOException {
        final byte[] bytes = new byte[256];
        //使用指定的压缩级别创建一个新的压缩器。
        Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(256)) {
            //设置压缩输入数据。
            deflater.setInput(data.getBytes());
            //当被调用时，表示压缩应该以输入缓冲区的当前内容结束。
            deflater.finish();
            while (!deflater.finished()) {
                //压缩输入数据并用压缩数据填充指定的缓冲区。
                int length = deflater.deflate(bytes);
                outputStream.write(bytes, 0, length);
            }
            return Base64.encode(outputStream.toByteArray());
        } finally {
            //关闭压缩器并丢弃任何未处理的输入。
            deflater.end();
        }
    }

    /**
     * 将字符串进行 zip 解压缩
     *
     * @param data 源字符串
     * @return 解压缩后的字符串
     */
    public static String unZip(String data) throws IOException, DataFormatException {
        final byte[] bytes = new byte[256];
        Inflater inflater = new Inflater();
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(256)) {
            //设置解压缩的输入数据。
            inflater.setInput(Base64.decode(data));
            //finished() 如果已到达压缩数据流的末尾，则返回true。
            while (!inflater.finished()) {
                //将字节解压缩到指定的缓冲区中。
                int length = inflater.inflate(bytes);
                outputStream.write(bytes, 0, length);
            }
            return outputStream.toString();
        } finally {
            inflater.end();
        }
    }
}
