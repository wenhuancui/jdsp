/*
 * Copyright (c) 2020 Sambit Paul
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.psambit9791.jdsp;

import com.github.psambit9791.jdsp.misc.Plotting;
import com.github.psambit9791.jdsp.misc.UtilMethods;
import com.github.psambit9791.jdsp.signal.Generate;
import com.github.psambit9791.jdsp.signal.Smooth;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestGenerate {

    final private int Fs = 100;
    private final Generate gp = new Generate(0, 1, this.Fs);
    private final Generate gpn = new Generate(-1, 1, 2*this.Fs);

    @Test
    @Order(1)
    public void createTestOutputDirectory() {
        String dirName = "./test_outputs/";
        File directory = new File(dirName);
        if (! directory.exists()){
            directory.mkdir();
        }
    }

    @Test
    public void TestSine() throws IOException{
        int f1 = 10;
        double[] out1 = this.gp.generateSineWave(f1);
        double[] result1 = {0., 0.593, 0.955, 0.945, 0.567, -0.032, -0.618, -0.964, -0.934, -0.541, 0.063,
                0.643, 0.972, 0.922, 0.514, -0.095, -0.667, -0.979, -0.91, -0.486, 0.127, 0.69, 0.985, 0.896,
                0.458, -0.158, -0.713, -0.99, -0.881, -0.43, 0.189, 0.735, 0.994, 0.866, 0.401, -0.22, -0.756,
                -0.997, -0.85, -0.372, 0.251, 0.776, 0.999, 0.833, 0.342, -0.282, -0.796, -1., -0.815, -0.312,
                0.312, 0.815, 1., 0.796, 0.282, -0.342, -0.833, -0.999, -0.776, -0.251, 0.372, 0.85, 0.997,
                0.756, 0.22, -0.401, -0.866, -0.994, -0.735, -0.189, 0.43, 0.881, 0.99, 0.713, 0.158, -0.458,
                -0.896, -0.985, -0.69, -0.127, 0.486, 0.91, 0.979, 0.667, 0.095, -0.514, -0.922, -0.972, -0.643,
                -0.063, 0.541, 0.934, 0.964, 0.618, 0.032, -0.567, -0.945, -0.955, -0.593, -0.};
        Assertions.assertArrayEquals(result1, out1, 0.001);
        double[] t = this.gp.getTimeArray();
        this.plot_now("Sine Wave", t, new double[][]{out1}, "test_outputs/sine.png");

        int f2 = 20;
        double[] out2 = this.gp.generateSineWave(f2);
        double[] result2 = {0.   ,  0.955,  0.567, -0.618, -0.934,  0.063,  0.972,  0.514, -0.667, -0.91 ,  0.127,
                0.985,  0.458, -0.713, -0.881,  0.189, 0.994,  0.401, -0.756, -0.85 ,  0.251,  0.999,  0.342, -0.796,
                -0.815,  0.312,  1.   ,  0.282, -0.833, -0.776,  0.372,  0.997, 0.22 , -0.866, -0.735,  0.43 ,  0.99 ,
                0.158, -0.896, -0.69 , 0.486,  0.979,  0.095, -0.922, -0.643,  0.541,  0.964,  0.032, -0.945, -0.593,
                0.593,  0.945, -0.032, -0.964, -0.541,  0.643, 0.922, -0.095, -0.979, -0.486,  0.69 ,  0.896, -0.158,
                -0.99 , -0.43 ,  0.735,  0.866, -0.22 , -0.997, -0.372,  0.776,  0.833, -0.282, -1.   , -0.312,  0.815,
                0.796, -0.342, -0.999, -0.251, 0.85 ,  0.756, -0.401, -0.994, -0.189,  0.881,  0.713, -0.458,
                -0.985, -0.127,  0.91 ,  0.667, -0.514, -0.972, -0.063,  0.934, 0.618, -0.567, -0.955, -0.};
        Assertions.assertArrayEquals(result2, out2, 0.001);
    }

    @Test
    public void TestCosine() throws IOException{
        int f1 = 10;
        double[] out1 = this.gp.generateCosineWave(f1);
        double[] result1 = {1., 0.805, 0.297, -0.327, -0.824, -0.999, -0.786, -0.266, 0.357, 0.841, 0.998, 0.766,
                0.236, -0.386, -0.858, -0.995, -0.745, -0.205, 0.415, 0.874, 0.992, 0.724, 0.174, -0.444, -0.889,
                -0.987, -0.701, -0.142, 0.472, 0.903, 0.982, 0.679, 0.111, -0.5, -0.916, -0.975, -0.655, -0.079,
                0.527, 0.928, 0.968, 0.631, 0.048, -0.554, -0.94, -0.959, -0.606, -0.016, 0.58, 0.95, 0.95,
                0.58, -0.016, -0.606, -0.959, -0.94, -0.554, 0.048, 0.631, 0.968, 0.928, 0.527, -0.079, -0.655,
                -0.975, -0.916, -0.5, 0.111, 0.679, 0.982, 0.903, 0.472, -0.142, -0.701, -0.987, -0.889, -0.444,
                0.174, 0.724, 0.992, 0.874, 0.415, -0.205, -0.745, -0.995, -0.858, -0.386, 0.236, 0.766, 0.998,
                0.841, 0.357, -0.266, -0.786, -0.999, -0.824, -0.327, 0.297, 0.805, 1.};
        Assertions.assertArrayEquals(result1, out1, 0.001);
        double[] t = this.gp.getTimeArray();
        this.plot_now("Cosine Wave", t, new double[][]{out1}, "test_outputs/cosine.png");

        int f2 = 20;
        double[] out2 = this.gp.generateCosineWave(f2);
        double[] result2 = {1.   ,  0.297, -0.824, -0.786,  0.357,  0.998,  0.236, -0.858, -0.745,  0.415,  0.992,
                0.174, -0.889, -0.701,  0.472,  0.982, 0.111, -0.916, -0.655,  0.527,  0.968,  0.048, -0.94 , -0.606,
                0.58 ,  0.95 , -0.016, -0.959, -0.554,  0.631,  0.928, -0.079, -0.975, -0.5  ,  0.679,  0.903, -0.142,
                -0.987, -0.444,  0.724, 0.874, -0.205, -0.995, -0.386,  0.766,  0.841, -0.266, -0.999, -0.327,  0.805,
                0.805, -0.327, -0.999, -0.266,  0.841,  0.766, -0.386, -0.995, -0.205,  0.874,  0.724, -0.444, -0.987,
                -0.142, 0.903,  0.679, -0.5  , -0.975, -0.079,  0.928,  0.631, -0.554, -0.959, -0.016,  0.95 ,  0.58 ,
                -0.606, -0.94 ,  0.048,  0.968, 0.527, -0.655, -0.916,  0.111,  0.982,  0.472, -0.701, -0.889,
                0.174,  0.992,  0.415, -0.745, -0.858,  0.236,  0.998,  0.357, -0.786, -0.824,  0.297,  1.};
        Assertions.assertArrayEquals(result2, out2, 0.001);
    }

    @Test
    public void TestSquare() throws IOException {
        int f1 = 10;
        double[] out1 = this.gp.generateSquareWave(f1);
        double[] result1 = {1., 1., 1., 1., 1., -1., -1., -1., -1., -1., 1., 1., 1., 1., 1., -1., -1., -1., -1.,
                -1., 1., 1., 1., 1., 1., -1., -1., -1., -1., -1., 1., 1., 1., 1., 1., -1., -1., -1., -1., -1.,
                1., 1., 1., 1., 1., -1., -1., -1., -1., -1., 1., 1., 1., 1., 1., -1., -1., -1., -1., -1., 1.,
                1., 1., 1., 1., -1., -1., -1., -1., -1., 1., 1., 1., 1., 1., -1., -1., -1., -1., -1., 1., 1.,
                1., 1., 1., -1., -1., -1., -1., -1., 1., 1., 1., 1., 1., -1., -1., -1., -1., 1.};
        Assertions.assertArrayEquals(result1, out1, 0.001);
        double[] t = this.gp.getTimeArray();
        this.plot_now("Square Wave", t, new double[][]{out1}, "test_outputs/square.png");

        int f2 = 20;
        double[] out2 = this.gp.generateSquareWave(f2);
        double[] result2 = {1.,  1.,  1., -1., -1.,  1.,  1.,  1., -1., -1.,  1.,  1.,  1., -1., -1.,  1.,  1.,  1.,
                -1., -1.,  1.,  1.,  1., -1., -1.,  1., 1.,  1., -1., -1.,  1.,  1.,  1., -1., -1.,  1.,  1.,  1., -1.,
                -1.,  1.,  1.,  1., -1., -1.,  1.,  1.,  1., -1., -1.,  1.,  1., -1., -1., -1.,  1.,  1., -1., -1., -1.,
                1.,  1., -1., -1., -1., 1.,  1., -1., -1., -1.,  1.,  1., -1., -1., -1.,  1.,  1., -1., -1., -1.,  1.,
                1., -1., -1., -1.,  1.,  1., -1., -1., -1.,  1., 1., -1., -1., -1.,  1.,  1., -1., -1.,  1.};
        Assertions.assertArrayEquals(result2, out2, 0.001);
    }

    @Test
    public void TestGaussPulse() throws IOException {
        int f1 = 10;
        double[][] out1 = this.gpn.generateGaussianPulse(f1);
        double[] result1_signal = {0.0, 0.0, 0.0, -0.0, -0.0, -0.0, -0.0, -0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.0, -0.0, -0.0, -0.0, -0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.0, -0.0, -0.0, -0.0, -0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.0, -0.0, -0.0, -0.0, -0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.0, -0.0, -0.0, -0.0, -0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.0, -0.0, -0.0, -0.0, -0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.0, -0.0, -0.0, -0.0, -0.0, 0.0001, 0.0002, 0.0004, 0.0004, 0.0001, -0.0009, -0.0026, -0.0043, -0.0044, -0.0007, 0.0082, 0.021, 0.0314, 0.0289, 0.0035, -0.0468, -0.1071, -0.1447, -0.1201, -0.0096, 0.1693, 0.3478, 0.4253, 0.3175, 0.0143, -0.3901, -0.7198, -0.7959, -0.5343, -0.0075, 0.5722, 0.9484, 0.9484, 0.5722, -0.0075, -0.5343, -0.7959, -0.7198, -0.3901, 0.0143, 0.3175, 0.4253, 0.3478, 0.1693, -0.0096, -0.1201, -0.1447, -0.1071, -0.0468, 0.0035, 0.0289, 0.0314, 0.021, 0.0082, -0.0007, -0.0044, -0.0043, -0.0026, -0.0009, 0.0001, 0.0004, 0.0004, 0.0002, 0.0001, -0.0, -0.0, -0.0, -0.0, -0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.0, -0.0, -0.0, -0.0, -0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.0, -0.0, -0.0, -0.0, -0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.0, -0.0, -0.0, -0.0, -0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.0, -0.0, -0.0, -0.0, -0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.0, -0.0, -0.0, -0.0, -0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.0, -0.0, -0.0, -0.0, -0.0, 0.0, 0.0, 0.0};
        double[] result1_envelope = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0001, 0.0001, 0.0002, 0.0004, 0.0007, 0.0011, 0.0018, 0.0028, 0.0045, 0.0069, 0.0104, 0.0155, 0.0226, 0.0324, 0.0456, 0.0631, 0.0858, 0.1145, 0.1501, 0.1932, 0.2443, 0.3034, 0.3699, 0.4431, 0.5212, 0.6021, 0.6831, 0.7612, 0.8331, 0.8954, 0.9452, 0.9799, 0.9977, 0.9977, 0.9799, 0.9452, 0.8954, 0.8331, 0.7612, 0.6831, 0.6021, 0.5212, 0.4431, 0.3699, 0.3034, 0.2443, 0.1932, 0.1501, 0.1145, 0.0858, 0.0631, 0.0456, 0.0324, 0.0226, 0.0155, 0.0104, 0.0069, 0.0045, 0.0028, 0.0018, 0.0011, 0.0007, 0.0004, 0.0002, 0.0001, 0.0001, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        Assertions.assertArrayEquals(result1_signal, out1[0], 0.001);
        Assertions.assertArrayEquals(result1_envelope, out1[1], 0.001);
        double[] t = this.gpn.getTimeArray();
        this.plot_now("Gaussian Pulse", t, out1, "test_outputs/gauss_pulse.png");

        int f2 = 25;
        double[][] out2 = this.gpn.generateGaussianPulse(f2);
        double[] result2_signal = {0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0001, 0.0004, -0.0013, -0.0047, 0.0112, 0.0314, -0.0619, -0.1339, 0.2176, 0.3641, -0.4872, -0.6302, 0.6945, 0.6945, -0.6302, -0.4872, 0.3641, 0.2176, -0.1339, -0.0619, 0.0314, 0.0112, -0.0047, -0.0013, 0.0004, 0.0001, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0, 0.0, -0.0, -0.0, 0.0};
        double[] result2_envelope = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0001, 0.0006, 0.002, 0.0062, 0.017, 0.042, 0.0924, 0.1817, 0.3193, 0.5013, 0.703, 0.8809, 0.986, 0.986, 0.8809, 0.703, 0.5013, 0.3193, 0.1817, 0.0924, 0.042, 0.017, 0.0062, 0.002, 0.0006, 0.0001, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        Assertions.assertArrayEquals(result2_signal, out2[0], 0.001);
        Assertions.assertArrayEquals(result2_envelope, out2[1], 0.001);
    }

    @Test
    public void TestSawtooth() throws IOException {
        int f1 = 5;
        double[] out1 = this.gp.generateSawtooth(f1, 0);
        double[] result1 = {1.   ,  0.899,  0.798,  0.697,  0.596,  0.495,  0.394,  0.293,
                0.192,  0.091, -0.01 , -0.111, -0.212, -0.313, -0.414, -0.515,
                -0.616, -0.717, -0.818, -0.919,  0.98 ,  0.879,  0.778,  0.677,
                0.576,  0.475,  0.374,  0.273,  0.172,  0.071, -0.03 , -0.131,
                -0.232, -0.333, -0.434, -0.535, -0.636, -0.737, -0.838, -0.939,
                0.96 ,  0.859,  0.758,  0.657,  0.556,  0.455,  0.354,  0.253,
                0.152,  0.051, -0.051, -0.152, -0.253, -0.354, -0.455, -0.556,
                -0.657, -0.758, -0.859, -0.96 ,  0.939,  0.838,  0.737,  0.636,
                0.535,  0.434,  0.333,  0.232,  0.131,  0.03 , -0.071, -0.172,
                -0.273, -0.374, -0.475, -0.576, -0.677, -0.778, -0.879, -0.98 ,
                0.919,  0.818,  0.717,  0.616,  0.515,  0.414,  0.313,  0.212,
                0.111,  0.01 , -0.091, -0.192, -0.293, -0.394, -0.495, -0.596,
                -0.697, -0.798, -0.899,  1.};
//        System.out.println(Arrays.toString(out1));
//        Assertions.assertArrayEquals(result1, out1, 0.001);
        double[] out2 = this.gp.generateSawtooth(f1, 0.5);
        double[] result2 = {-1.   , -0.798, -0.596, -0.394, -0.192,  0.01 ,  0.212,  0.414,
                0.616,  0.818,  0.98 ,  0.778,  0.576,  0.374,  0.172, -0.03 ,
                -0.232, -0.434, -0.636, -0.838, -0.96 , -0.758, -0.556, -0.354,
                -0.152,  0.051,  0.253,  0.455,  0.657,  0.859,  0.939,  0.737,
                0.535,  0.333,  0.131, -0.071, -0.273, -0.475, -0.677, -0.879,
                -0.919, -0.717, -0.515, -0.313, -0.111,  0.091,  0.293,  0.495,
                0.697,  0.899,  0.899,  0.697,  0.495,  0.293,  0.091, -0.111,
                -0.313, -0.515, -0.717, -0.919, -0.879, -0.677, -0.475, -0.273,
                -0.071,  0.131,  0.333,  0.535,  0.737,  0.939,  0.859,  0.657,
                0.455,  0.253,  0.051, -0.152, -0.354, -0.556, -0.758, -0.96 ,
                -0.838, -0.636, -0.434, -0.232, -0.03 ,  0.172,  0.374,  0.576,
                0.778,  0.98 ,  0.818,  0.616,  0.414,  0.212,  0.01 , -0.192,
                -0.394, -0.596, -0.798, -1.};
//        System.out.println(Arrays.toString(out2));
        Assertions.assertArrayEquals(result2, out2, 0.001);
        double[] out3 = this.gp.generateSawtooth(f1, 1);
        double[] result3 = {-1.   , -0.899, -0.798, -0.697, -0.596, -0.495, -0.394, -0.293,
                -0.192, -0.091,  0.01 ,  0.111,  0.212,  0.313,  0.414,  0.515,
                0.616,  0.717,  0.818,  0.919, -0.98 , -0.879, -0.778, -0.677,
                -0.576, -0.475, -0.374, -0.273, -0.172, -0.071,  0.03 ,  0.131,
                0.232,  0.333,  0.434,  0.535,  0.636,  0.737,  0.838,  0.939,
                -0.96 , -0.859, -0.758, -0.657, -0.556, -0.455, -0.354, -0.253,
                -0.152, -0.051,  0.051,  0.152,  0.253,  0.354,  0.455,  0.556,
                0.657,  0.758,  0.859,  0.96 , -0.939, -0.838, -0.737, -0.636,
                -0.535, -0.434, -0.333, -0.232, -0.131, -0.03 ,  0.071,  0.172,
                0.273,  0.374,  0.475,  0.576,  0.677,  0.778,  0.879,  0.98 ,
                -0.919, -0.818, -0.717, -0.616, -0.515, -0.414, -0.313, -0.212,
                -0.111, -0.01 ,  0.091,  0.192,  0.293,  0.394,  0.495,  0.596,
                0.697,  0.798,  0.899, -1.};
//        System.out.println(Arrays.toString(out3));
        Assertions.assertArrayEquals(result3, out3, 0.001);
        double[] t = this.gp.getTimeArray();
        this.plot_now("Inverse Sawtooth", t, new double[][]{out1}, "test_outputs/sawtooth_inverse.png");
        this.plot_now("Half Sawtooth", t, new double[][]{out2}, "test_outputs/sawtooth_half.png");
        this.plot_now("Sawtooth", t, new double[][]{out3}, "test_outputs/sawtooth.png");
    }

    @Test
    public void TestUnitImpulse() throws IOException {
        double[] out1 = this.gp.generateUnitImpulse(0.5);
        double[] result1 = {0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
                0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
                0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 1.,
                0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
                0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.,
                0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.};
        Assertions.assertArrayEquals(result1, out1, 0.001);
        double[] t = this.gp.getTimeArray();
        this.plot_now("Unit Impulse", t, new double[][]{out1}, "test_outputs/unit_impulse.png");
    }

    @Test
    public void TestECG() throws IOException {
        final double[] highResSignal = {0.228, 0.257, 0.287, 0.318, 0.349, 0.378, 0.407, 0.436, 0.434, 0.43, 0.423, 0.417, 0.413, 0.406, 0.399, 0.389, 0.378, 0.362, 0.345, 0.326, 0.304, 0.276, 0.242, 0.215, 0.197, 0.186, 0.18, 0.174, 0.168, 0.163, 0.16, 0.158, 0.158, 0.159, 0.161, 0.169, 0.186, 0.209, 0.226, 0.236, 0.241, 0.246, 0.25, 0.254, 0.256, 0.257, 0.258, 0.259, 0.263, 0.265, 0.265, 0.261, 0.256, 0.251, 0.246, 0.239, 0.226, 0.211, 0.195, 0.18, 0.168, 0.155, 0.141, 0.125, 0.108, 0.093, 0.082, 0.071, 0.06, 0.048, 0.038, 0.032, 0.027, 0.023, 0.014, -0.0, -0.016, -0.028, -0.034, -0.03, -0.017, 0.005, 0.045, 0.103, 0.182, 0.273, 0.362, 0.432, 0.482,0.517, 0.54, 0.554, 0.561, 0.564, 0.556, 0.54, 0.515, 0.473, 0.413, 0.33, 0.234, 0.141, 0.066, 0.012, -0.024, -0.046, -0.057, -0.062, -0.066, -0.067, -0.069, -0.075, -0.08, -0.086, -0.087, -0.087, -0.087, -0.091, -0.094, -0.096, -0.094, -0.091, -0.089, -0.09, -0.091, -0.092, -0.09, -0.088, -0.086, -0.086, -0.086, -0.086, -0.081, -0.076, -0.071, -0.068, -0.065, -0.06, -0.054, -0.047, -0.04, -0.035, -0.032, -0.028, -0.023, -0.017, -0.01, -0.006, -0.004, 0.0, 0.007, 0.012, 0.016, 0.019, 0.02, 0.023, 0.029, 0.036, 0.043, 0.05, 0.056, 0.061, 0.069, 0.079, 0.089, 0.094, 0.099, 0.105, 0.114, 0.122, 0.129, 0.134, 0.137,0.14, 0.142, 0.144, 0.144, 0.139, 0.13, 0.119, 0.112, 0.105, 0.097, 0.086, 0.073, 0.061, 0.049, 0.037, 0.026, 0.015, 0.003, -0.007, -0.013, -0.015, -0.016, -0.019, -0.021, -0.021, -0.018, -0.012, -0.008, -0.004, -0.002, 0.002, 0.01, 0.02, 0.029, 0.034, 0.04, 0.045, 0.051, 0.056, 0.058, 0.059, 0.056, 0.053, 0.048, 0.045, 0.038, 0.027, 0.013, -0.001, -0.011, -0.023, -0.037, -0.055, -0.073, -0.09, -0.105, -0.118, -0.129, -0.138, -0.147, -0.155, -0.161, -0.166, -0.172, -0.184, -0.198, -0.212, -0.221, -0.227, -0.23, -0.226, -0.209, -0.175, -0.12, -0.046, 0.042, 0.133, 0.214, 0.278, 0.326, 0.358, 0.377, 0.388, 0.395, 0.399, 0.395, 0.378, 0.345, 0.29, 0.216, 0.13, 0.036, -0.047, -0.113, -0.162, -0.194, -0.211, -0.22, -0.227, -0.233, -0.239, -0.248, -0.258, -0.267, -0.274, -0.279, -0.283, -0.289, -0.292, -0.291, -0.285, -0.278, -0.276, -0.277, -0.282, -0.288, -0.291, -0.293, -0.295, -0.299, -0.304, -0.307, -0.309, -0.312, -0.317, -0.324, -0.332, -0.337, -0.338, -0.333, -0.326, -0.319, -0.315, -0.309, -0.303, -0.299, -0.296, -0.292, -0.284, -0.277, -0.27, -0.264, -0.258, -0.251, -0.245, -0.238, -0.229, -0.217, -0.208, -0.199, -0.191, -0.181, -0.17, -0.162, -0.156, -0.151, -0.143, -0.135, -0.128, -0.124, -0.123, -0.125, -0.13, -0.133, -0.137, -0.139, -0.143, -0.149, -0.155, -0.159, -0.165, -0.173, -0.182, -0.193, -0.204, -0.216, -0.226, -0.237, -0.251, -0.266, -0.284, -0.299, -0.311, -0.324, -0.338, -0.35, -0.359, -0.365, -0.37, -0.375, -0.378, -0.383, -0.386, -0.384, -0.379, -0.37, -0.363, -0.358, -0.353, -0.344, -0.335, -0.33, -0.333, -0.339, -0.343, -0.347, -0.35, -0.358, -0.37, -0.385, -0.404, -0.422, -0.437, -0.454, -0.476, -0.497, -0.513, -0.525, -0.535, -0.546, -0.559, -0.572, -0.582, -0.594, -0.609, -0.626, -0.643, -0.653, -0.647, -0.623, -0.583, -0.529, -0.459, -0.372, -0.277, -0.191, -0.126, -0.083, -0.056, -0.039, -0.024, -0.01, -0.002, -0.01, -0.036, -0.079, -0.138, -0.213, -0.303, -0.399, -0.489, -0.559, -0.605, -0.63, -0.64, -0.647, -0.655, -0.664, -0.672, -0.678, -0.682, -0.684, -0.688, -0.695, -0.702, -0.704, -0.704, -0.704, -0.704, -0.705, -0.705, -0.702, -0.698, -0.695, -0.694, -0.693, -0.691, -0.685, -0.678, -0.673, -0.671, -0.669, -0.667, -0.661, -0.652, -0.644, -0.639, -0.634, -0.627, -0.616, -0.605, -0.598, -0.594, -0.59, -0.583, -0.572, -0.56, -0.549, -0.54, -0.536, -0.531, -0.523, -0.515, -0.507, -0.5, -0.497, -0.494, -0.488, -0.48, -0.469, -0.459, -0.456, -0.454, -0.449, -0.44, -0.429, -0.422, -0.417, -0.41, -0.403, -0.394, -0.386, -0.382, -0.382, -0.385, -0.389, -0.388, -0.385, -0.388, -0.399, -0.415, -0.429, -0.441, -0.454, -0.469, -0.486, -0.5, -0.51, -0.519, -0.529, -0.541, -0.554, -0.567, -0.573, -0.574, -0.572, -0.57, -0.57, -0.568, -0.562, -0.554, -0.546, -0.54, -0.534, -0.526, -0.516, -0.507, -0.501, -0.5, -0.501, -0.501, -0.499, -0.498, -0.499, -0.507, -0.518, -0.53, -0.541, -0.551, -0.564, -0.579, -0.595, -0.608, -0.617, -0.626, -0.637, -0.652, -0.667, -0.679, -0.685, -0.688, -0.691, -0.694, -0.697, -0.702, -0.709, -0.718, -0.727, -0.735, -0.741, -0.741, -0.73, -0.704, -0.664, -0.607, -0.53, -0.44, -0.352, -0.281, -0.232, -0.203, -0.187, -0.183, -0.183, -0.184, -0.186, -0.194, -0.217, -0.258, -0.316, -0.394, -0.486, -0.578, -0.652, -0.702, -0.73, -0.741, -0.74, -0.735, -0.731, -0.731, -0.73, -0.729, -0.729, -0.731, -0.734, -0.736, -0.735, -0.733, -0.731, -0.729, -0.728, -0.726, -0.723, -0.718, -0.715, -0.716, -0.718, -0.719, -0.715, -0.709, -0.705, -0.703, -0.702, -0.699, -0.691, -0.684, -0.678, -0.675, -0.674, -0.67, -0.661, -0.649, -0.637, -0.628, -0.622, -0.616, -0.608, -0.599, -0.591, -0.586, -0.582, -0.577, -0.568, -0.559, -0.551, -0.545, -0.542, -0.54, -0.535, -0.525, -0.512, -0.503, -0.497, -0.493, -0.486, -0.477, -0.468, -0.461, -0.455, -0.448, -0.442, -0.434, -0.427, -0.422, -0.42, -0.419, -0.416, -0.413, -0.41, -0.408, -0.408, -0.409, -0.411, -0.414, -0.419, -0.424, -0.432, -0.44, -0.449, -0.457, -0.467, -0.48, -0.493, -0.505, -0.515, -0.525, -0.536, -0.548, -0.556, -0.561, -0.562, -0.559, -0.554, -0.549, -0.546, -0.545,-0.541, -0.536, -0.527, -0.519, -0.508, -0.498, -0.487, -0.479, -0.474, -0.472, -0.473, -0.475, -0.474, -0.472, -0.47, -0.469, -0.47, -0.473, -0.479, -0.487, -0.496, -0.508, -0.52, -0.532, -0.541, -0.548, -0.556, -0.569, -0.583, -0.593, -0.6, -0.605, -0.611, -0.618, -0.625, -0.63, -0.631, -0.629, -0.627, -0.628, -0.636, -0.647, -0.654, -0.656, -0.654, -0.646, -0.626, -0.593, -0.544, -0.479, -0.401, -0.315, -0.232, -0.162, -0.109, -0.073, -0.054, -0.052, -0.06,-0.075, -0.095, -0.124, -0.165, -0.219, -0.289, -0.373, -0.466, -0.556, -0.635, -0.697, -0.738, -0.76, -0.765, -0.764, -0.761, -0.76, -0.762, -0.764, -0.763, -0.76, -0.758, -0.757, -0.76, -0.763, -0.761, -0.757, -0.749, -0.742, -0.735, -0.728, -0.721, -0.715, -0.711, -0.709, -0.709, -0.709, -0.705, -0.697, -0.689, -0.684, -0.681, -0.679, -0.675, -0.668, -0.659, -0.651, -0.641, -0.632, -0.623, -0.613, -0.604, -0.595, -0.587, -0.577, -0.566, -0.556, -0.547, -0.542, -0.538, -0.533, -0.527, -0.521, -0.513, -0.508, -0.502, -0.497, -0.492, -0.486, -0.48, -0.474, -0.47, -0.465, -0.459, -0.453, -0.449, -0.447, -0.445, -0.442, -0.436, -0.429, -0.423, -0.419, -0.416, -0.416, -0.415, -0.413, -0.41, -0.41, -0.408, -0.406, -0.404, -0.403, -0.406, -0.415, -0.428, -0.438, -0.447, -0.455, -0.463, -0.472, -0.483, -0.495, -0.506, -0.517, -0.529, -0.542, -0.556, -0.568, -0.575, -0.579, -0.584, -0.59, -0.596, -0.601, -0.603, -0.602, -0.602, -0.601, -0.601, -0.601, -0.596, -0.587, -0.578, -0.568, -0.56, -0.553, -0.546, -0.538, -0.53, -0.525, -0.525, -0.524, -0.522, -0.519, -0.517, -0.52, -0.527, -0.535, -0.544, -0.55, -0.558, -0.568, -0.583, -0.596, -0.607, -0.613, -0.618, -0.626, -0.638, -0.651, -0.661, -0.667, -0.674, -0.681, -0.689, -0.695, -0.698, -0.699, -0.7, -0.705, -0.715, -0.726, -0.736, -0.737, -0.732, -0.72, -0.701, -0.669, -0.618, -0.547, -0.46, -0.362, -0.258, -0.157, -0.068, 0.006, 0.062, 0.098, 0.109, 0.104, 0.09, 0.068, 0.035, -0.015, -0.086, -0.172, -0.269, -0.373, -0.474, -0.562, -0.631, -0.68, -0.708, -0.717, -0.716, -0.714, -0.714, -0.716, -0.717, -0.718, -0.72, -0.722, -0.723, -0.724, -0.725, -0.725, -0.724, -0.724, -0.727, -0.728, -0.729, -0.728, -0.726, -0.725, -0.724, -0.722, -0.723, -0.724, -0.723, -0.722, -0.721, -0.72, -0.717, -0.71, -0.703, -0.698, -0.695, -0.691, -0.684, -0.679, -0.672, -0.666, -0.66, -0.654, -0.647, -0.639, -0.631, -0.625, -0.62, -0.614, -0.606, -0.597, -0.589, -0.547, -0.505, -0.465, -0.422, -0.38, -0.337, -0.297};
        double[] data = UtilMethods.splitByIndex(UtilMethods.electrocardiogram(), 3200, 4200);
        Smooth sObj = new Smooth(data, 15, "rectangular");
        double[] ecgSignal = sObj.smoothSignal("same");
        double[] t = UtilMethods.arange(0, (double)ecgSignal.length, 1);
        this.plot_now("ECG Wave", t, new double[][]{ecgSignal}, "test_outputs/ecg.png");
        Assertions.assertArrayEquals(highResSignal, ecgSignal, 0.001);
    }

    private void plot_now(String title, double[] t, double[][] signal, String fname) throws IOException {
        Plotting fig = new Plotting(600, 300, title, "Time", "Signal");
        fig.initialisePlot();
        for (int i=0; i< signal.length; i++) {
            fig.addSignal(title+" "+(i+1), t, signal[i], false);
        }
        fig.saveAsPNG(fname);
    }
}
