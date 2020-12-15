/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.org.objectweb.asm;

/**
 * A parser to make a {@link ClassVisitor} visit a ClassFile structure, as defined in the Java
 * Virtual Machine Specification (JVMS). This class parses the ClassFile content and calls the
 * appropriate visit methods of a given {@link ClassVisitor} for each field, method and bytecode
 * instruction encountered.
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-4.html">JVMS 4</a>
 * @author Eric Bruneton
 * @author Eugene Kuleshov
 */
public class MyClassReader extends ClassReader {

    /**
     * The String objects corresponding to the CONSTANT_Utf8 constant pool items. This cache avoids
     * multiple parsing of a given CONSTANT_Utf8 constant pool item.
     */
    private final String[] constantUtf8Values;

    // -----------------------------------------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------------------------------------

    /**
     * Constructs a new {@link ClassReader} object.
     *
     * @param classFile the JVMS ClassFile structure to be read.
     */
    public MyClassReader(final byte[] classFile) {
        super(classFile);
        constantUtf8Values = new String[getItemCount()];
    }

    /**
     * Reads a CONSTANT_Utf8 constant pool entry in this {@link ClassReader}. <i>This method is
     * intended for {@link Attribute} sub classes, and is normally not needed by class generators or
     * adapters.</i>
     *
     * @param offset the start offset of an unsigned short value in this {@link ClassReader}, whose
     *     value is the index of a CONSTANT_Utf8 entry in the class's constant pool table.
     * @param charBuffer the buffer to be used to read the string. This buffer must be sufficiently
     *     large. It is not automatically resized.
     * @return the String corresponding to the specified CONSTANT_Utf8 entry.
     */
    // DontCheck(AbbreviationAsWordInName): can't be renamed (for backward binary compatibility).
    @Override
    public String readUTF8(final int offset, final char[] charBuffer) {
        int constantPoolEntryIndex = readUnsignedShort(offset);
        if (offset == 0 || constantPoolEntryIndex == 0) {
            return null;
        }
        String value = constantUtf8Values[constantPoolEntryIndex];
        if (value != null) {
            return value;
        }
        int cpInfoOffset = getItem(constantPoolEntryIndex);
        return constantUtf8Values[constantPoolEntryIndex] = readUtf(cpInfoOffset + 2, readUnsignedShort(cpInfoOffset), charBuffer);
    }

    /**
     * Reads an UTF8 string in {@link #classFileBuffer}.
     *
     * @param utfOffset the start offset of the UTF8 string to be read.
     * @param utfLength the length of the UTF8 string to be read.
     * @param charBuffer the buffer to be used to read the string. This buffer must be sufficiently
     *     large. It is not automatically resized.
     * @return the String corresponding to the specified UTF8 string.
     */
    private String readUtf(final int utfOffset, final int utfLength, final char[] charBuffer) {
        int currentOffset = utfOffset;
        int endOffset = currentOffset + utfLength;
        int strLength = 0;
        byte[] classBuffer = classFileBuffer;
        while (currentOffset < endOffset) {
            int currentByte = classBuffer[currentOffset++];
            if ((currentByte & 0x80) == 0) {
                charBuffer[strLength++] = (char) currentByte;
            } else { // else if ((currentByte & 0xE0) == 0xC0) {
                charBuffer[strLength++] =
                        (char) ((currentByte << 6) + classBuffer[currentOffset++] + 0x1080);
            //} else {
            //    System.out.println("3: " + currentByte);
            //    charBuffer[strLength++] =
            //            (char)
            //                    (((currentByte & 0xF) << 12)
            //                     + ((classBuffer[currentOffset++] & 0x3F) << 6)
            //                     + (classBuffer[currentOffset++] & 0x3F));
            }
        }
        return new String(charBuffer, 0, strLength);
    }
}
