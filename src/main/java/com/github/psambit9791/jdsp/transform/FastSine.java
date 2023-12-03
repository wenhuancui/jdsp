/*
 *
 *  * Copyright (c) 2023 Sambit Paul
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.github.psambit9791.jdsp.transform;

import com.github.psambit9791.jdsp.misc.UtilMethods;
import org.apache.commons.math3.transform.*;

/**
 * <h1>Fast Sine Transform</h1>
 * The FastSine class decomposes a finite sequence of data points in terms of a sum of sine functions of different frequencies.
 * This class implements only Type 1 transform.
 * This can be considered a wrapper on top of the Apache Math3 FastSineTransformer [FORWARD] which pre-processes the signal before
 * the operation.
 * <p>
 *
 * @author  Sambit Paul
 * @version 1.0
 */
public class FastSine implements _SineCosine{

    private double[] signal;
    private double[] output;

    private FastSineTransformer fst;

    /**
     * Extends the length of the signal to the nearest power of 2.
     */
    private void extendSignal() {
        double power = Math.log(this.signal.length)/Math.log(2);
        double raised_power = Math.ceil(power);
        int new_length = (int)(Math.pow(2, raised_power));
        if (new_length != this.signal.length) {
            this.signal = UtilMethods.zeroPadSignal(this.signal, new_length-this.signal.length);
        }
    }

    /**
     * This constructor initialises the prerequisites required to use FastSine.
     *
     * @param signal The input signal to be transformed.
     */
    public FastSine(double[] signal) {
        this.signal = signal;
        this.extendSignal();
        this.fst = new FastSineTransformer(DstNormalization.STANDARD_DST_I);
    }

    /**
     * This constructor initialises the prerequisites required to use FastSine.
     *
     * @param signal The input signal to be transformed.
     * @param norm   The normalization option (STANDARD or ORTHOGONAL).
     */
    public FastSine(double[] signal, Normalization norm) {
        this.signal = signal;
        this.extendSignal();
        if (norm == Normalization.ORTHOGONAL) {
            this.fst = new FastSineTransformer(DstNormalization.ORTHOGONAL_DST_I);
        }
        else {
            this.fst = new FastSineTransformer(DstNormalization.STANDARD_DST_I);
        }
    }

    /**
     * Performs the sine transformation on the input signal.
     */
    public void transform() {
        this.output = this.fst.transform(this.signal, TransformType.FORWARD);
    }

    /**
     * Returns the output of the transformation.
     *
     * @throws java.lang.ExceptionInInitializerError if called before executing transform() method
     * @return double[] The transformed signal.
     */
    public double[] getMagnitude() throws ExceptionInInitializerError {
        if (this.output == null) {
            throw new ExceptionInInitializerError("Execute transform() function before returning result");
        }
        return this.output;
    }

    /**
     * Gets the length of the input signal after preprocessing for FastSine (padded to nearest power of 2).
     *
     * @return int The updated length of the input signal.
     */
    public int getSignalLength() {
        return this.signal.length;
    }
}
