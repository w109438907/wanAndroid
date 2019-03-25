package com.yuan.learnproject.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * @author yuan
 * @date 2019/3/8
 **/
public class CommonUtil {
    public static int randomColor() {
        int red, green, blue;
        int bound = 190;
        Random random = new Random();
        red = random.nextInt(bound);
        green = random.nextInt(bound);
        blue = random.nextInt(bound);
        return Color.rgb(red, green, blue);
    }
}
