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
package org.apfloat.jscience;

import javolution.text.Text;

import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;
import org.jscience.mathematics.number.Number;
import org.jscience.mathematics.structure.Field;

/**
 * This class represents an arbitrary precision field object.
 *
 * @param <T> The type of the field.
 * @param <V> The type of the underlying value.
 *
 * @since 1.8.0
 * @version 1.8.0
 * @author Mikko Tommila
 */

public abstract class AbstractField<T extends AbstractField<T, V>, V extends Apcomplex>
    extends Number<T>
    implements Field<T>
{
    /**
     * Constructs a new field object with the specified value.
     *
     * @param value The value.
     */

    protected AbstractField(V value)
    {
        if (value == null)
        {
            throw new NullPointerException("Value can't be null");
        }
        this.value = value;
    }

    /**
     * Returns the sum of this object with the one specified.
     *
     * @param that The addend.
     *
     * @return <code>this + that</code>
     */

    @Override
    public abstract T plus(T that);

    /**
     * Returns the additive inverse of this object.
     *
     * @return <code>-this</code>
     */

    @Override
    public abstract T opposite();

    /**
     * Returns the product of this object with the one specified.
     *
     * @param that The multiplicand.
     *
     * @return <code>this * that</code>
     */

    @Override
    public abstract T times(T that);


    /**
     * Returns the multiplicative inverse of this object.
     *
     * @return <code>1 / this</code>
     *
     * @exception java.lang.ArithmeticException If the divisor is zero.
     */

    @Override
    public abstract T inverse()
        throws ArithmeticException;

    /**
     * Returns a copy of this object.
     *
     * @return A copy of this object.
     */

    @Override
    public abstract T copy();

    /**
     * Compares the absolute value of this number
     * with the absolute value of the number specified.
     *
     * @param that The number to be compared with.
     *
     * @return <code>|this| &gt; |that|</code>
     */

    @Override
    public boolean isLargerThan(T that)
    {
        return ApcomplexMath.abs(value()).compareTo(ApcomplexMath.abs(that.value())) > 0;
    }

    /**
     * Returns the value of this number as the underlying type.
     *
     * @return The value.
     */

    public V value()
    {
        return this.value;
    }

    /**
     * Returns the value of this number as a <code>double</code>.
     *
     * @return The value.
     */

    @Override
    public double doubleValue()
    {
        return value().doubleValue();
    }

    /**
     * Returns the value of this number as a <code>long</code>.
     *
     * @return The value.
     */

    @Override
    public long longValue()
    {
        return value().longValue();
    }

    /**
     * Returns the text representation of this number.
     *
     * @return The string representation of this number as a <code>Text</code>.
     */

    @Override
    public Text toText()
    {
        return Text.valueOf(value().toString());
    }

    /**
     * Compares this number to another number.
     *
     * @param that The number to be compared with.
     *
     * @return -1, 0, or 1 depending on the ordering. 
     */

    @Override
    public int compareTo(T that)
    {
        int result = value().real().compareTo(that.value().real());
        if (result == 0)
        {
            result = value().imag().compareTo(that.value().imag());
        }
        return result;
    }

    /**
     * Returns the hash code for this number.
     *
     * @return The hash code value.
     */

    @Override
    public int hashCode()
    {
        return value().hashCode();
    }

    /**
     * Compares for equality.
     * 
     * @return If the objects are equal.
     */

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof AbstractField)
        {
            AbstractField<?, ?> that = (AbstractField<?, ?>) obj;
            return value().equals(that.value());
        }
        return false;
    }

    private static final long serialVersionUID = -7725271295007354895L;

    private V value;
}
