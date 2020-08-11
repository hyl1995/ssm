package com.hyl.core.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 根据id生成邀请码
 */
public class InvitationCodeUtil {
    /**
     * 自定义进制(0,1没有加入,容易与o,l混淆)，数组顺序可进行调整增加反推难度，A用来补位因此此数组不包含A，共31个字符。
     */
    private static final char[] BASE = new char[]{'L', 'S', '9', 'T', 'V', 'X', 'Y', 'E', 'Z', 'H',
            'F', '5', 'G', 'I', 'B', 'N', 'W', '7', 'C', '8',
            '3', 'J', '2', '6', 'R', '4', 'K', 'M', 'D', 'P', 'U'};

    /**
     * A补位字符，不能与自定义重复
     */
    private static final char SUFFIX_CHAR = 'A';

    /**
     * 进制长度
     */
    private static final int BIN_LEN = BASE.length;

    /**
     * 生成邀请码最小长度
     */
    private static final int CODE_LEN = 6;

    /**
     * ID转换为邀请码
     *
     * @param id
     * @return
     */
    public static String idToCode(Long id) {
        if (id <= 0) {
            return null;
        }
        char[] buf = new char[BIN_LEN];
        int charPos = BIN_LEN;

        // 当id除以数组长度结果大于0，则进行取模操作，并以取模的值作为数组的坐标获得对应的字符
        while (id / BIN_LEN > 0) {
            int index = (int) (id % BIN_LEN);
            buf[--charPos] = BASE[index];
            id /= BIN_LEN;
        }

        buf[--charPos] = BASE[(int) (id % BIN_LEN)];
        // 将字符数组转化为字符串
        String result = new String(buf, charPos, BIN_LEN - charPos);

        // 长度不足指定长度则随机补全
        int len = result.length();
        if (len < CODE_LEN) {
            StringBuilder sb = new StringBuilder();
            sb.append(SUFFIX_CHAR);//补 ‘A’
            int remainder = CODE_LEN - len - 1;//剩余位
            if (remainder > 0) {
                char[] charArray = result.toCharArray();
                //用前几位的ascii码补位
                for (int i = 0; i < charArray.length; i++) {
                    int asciiNum = charArray[i];
                    sb.append(asciiNum);
                }
                if (sb.length() < remainder) {
                    sb.append((int)SUFFIX_CHAR);
                }
                sb.delete(remainder + 1, sb.length());//删除多余位
            }
            result += sb.toString();
        }

        return result;
    }

    /**
     * 邀请码解析出ID<br/>
     * 基本操作思路恰好与idToCode反向操作。
     *
     * @param code
     * @return
     */
    public static Long codeToId(String code) {
        if (code.length() < 6) {
            return null;
        }
        char[] charArray = code.toCharArray();
        long result = 0L;
        for (int i = 0; i < charArray.length; i++) {
            int index = 0;
            for (int j = 0; j < BIN_LEN; j++) {
                if (charArray[i] == BASE[j]) {
                    index = j;
                    break;
                }
            }

            if (charArray[i] == SUFFIX_CHAR) {
                break;
            }

            if (i > 0) {
                result = result * BIN_LEN + index;
            } else {
                result = index;
            }
        }

        return result;

    }

    public static void main(String[] args) {
        String code = idToCode(11606L);
        System.out.println(code);
        System.out.println(codeToId(code));
    }

    /**
     * 重新随机生成字符数组
     */
    public static void randomChar(){
        List<Character> index = new ArrayList<>();
        for(int i = 0;i<31;i++){
            index.add(BASE[i]);
        }
        index.parallelStream().forEach(i -> System.out.print(String.format("'%s', ", i)));
    }

    /**
     * 一百万的id测试邀请码
     */
    public static void threadPoolTest(){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("InvitationCode-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 100,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
        AtomicLong al = new AtomicLong(0);
        ConcurrentSkipListSet set = new ConcurrentSkipListSet<>();
        for (int j = 0; j<10000000; j++){
            singleThreadPool.execute(()-> {
                Long i = al.incrementAndGet();
                String invite = idToCode(i);
                set.add(invite);
                Long decode  = codeToId(invite);
                Boolean flag = i.equals(decode);
                if (!flag) {
                    System.out.println(i+"   "+invite+"   " + decode);
                }
            });
        }

        singleThreadPool.shutdown();
        System.out.println("结束，集合大小应为10000000："+ set.size());
    }
}
