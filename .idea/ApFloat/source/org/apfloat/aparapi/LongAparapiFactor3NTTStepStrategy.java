/*
 * MIT License
 *
 * Copyright (c) 2002-2021 Mikko Tommila
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.apfloat.aparapi;

import org.apfloat.ApfloatRuntimeException;
import org.apfloat.spi.ArrayAccess;
import org.apfloat.spi.DataStorage;
import org.apfloat.internal.ApfloatInternalException;
import org.apfloat.internal.LongFactor3NTTStepStrategy;
import static org.apfloat.internal.LongModConstants.*;

/**
 * Steps for the factor-3 NTT using the GPU, for the <code>long</code> element type.<p>
 *
 * @since 1.8.3
 * @version 1.8.3
 * @author Mikko Tommila
 */

public class LongAparapiFactor3NTTStepStrategy
    extends LongFactor3NTTStepStrategy
{
    @Override
    public void transformColumns(DataStorage dataStorage0, DataStorage dataStorage1, DataStorage dataStorage2, long startColumn, long columns, long power2length, long length, boolean isInverse, int modulus)
        throws ApfloatRuntimeException
    {
        // Transform length is three times a power of two
        assert (length == 3 * power2length);

        // Check that the data storages use consecutive sections of the same memory array
        if (!dataStorage0.isCached() || !dataStorage1.isCached() || !dataStorage2.isCached() ||
            startColumn > Integer.MAX_VALUE || columns > Integer.MAX_VALUE)
        {
            throw new ApfloatInternalException("Data must be stored in memory");
        }

        ArrayAccess arrayAccess0 = dataStorage0.getArray(DataStorage.READ_WRITE, startColumn, (int) columns),
                    arrayAccess1 = dataStorage1.getArray(DataStorage.READ_WRITE, startColumn, (int) columns),
                    arrayAccess2 = dataStorage2.getArray(DataStorage.READ_WRITE, startColumn, (int) columns);

        if (arrayAccess0.getLongData() != arrayAccess1.getLongData() || arrayAccess1.getLongData() != arrayAccess2.getLongData() ||
            arrayAccess1.getOffset() != arrayAccess0.getOffset() + columns || arrayAccess2.getOffset() != arrayAccess1.getOffset() + columns)
        {
            throw new ApfloatInternalException("Data must be stored consecutively in memory");
        }

        setModulus(MODULUS[modulus]);                                   // Modulus
        long w = (isInverse ?
                  getInverseNthRoot(PRIMITIVE_ROOT[modulus], length) :
                  getForwardNthRoot(PRIMITIVE_ROOT[modulus], length)),  // Forward/inverse n:th root
             w3 = modPow(w, (long) power2length),                       // Forward/inverse 3rd root
             ww = modMultiply(w, w),
             w1 = negate(modDivide((long) 3, (long) 2)),
             w2 = modAdd(w3, modDivide((long) 1, (long) 2));

        LongKernel kernel = LongKernel.getInstance();
        kernel.setOp(isInverse ? LongKernel.INVERSE_TRANSFORM_COLUMNS : LongKernel.TRANSFORM_COLUMNS);
        kernel.setArrayAccess(arrayAccess0);
        kernel.setStartColumn((int) startColumn);
        kernel.setColumns((int) columns); 
        kernel.setW(w);
        kernel.setWw(ww);
        kernel.setW1(w1);
        kernel.setW2(w2);
        kernel.setModulus(MODULUS[modulus]);

        kernel.execute((int) columns);
    }
}
