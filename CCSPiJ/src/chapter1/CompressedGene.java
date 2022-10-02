// CompressedGene.java
// From Classic Computer Science Problems in Java Chapter 1
// Copyright 2020 David Kopec
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package chapter1;

import java.util.BitSet;

public class CompressedGene {

  private BitSet bitSet;
  private int length;

  public CompressedGene(String gene) {
    compress(gene);
  }

  private BitSet bitSet() {
    return bitSet;
  }

  private void compress(String gene) {
    length = gene.length();
    // reserve enough capacity for all of the bits
    bitSet = new BitSet(length * 2);
    // convert to upper case for consistency
    var upperGene = gene.toUpperCase();
    // convert String to bit representation
    for (var i = 0; i < length; i++) {
      var firstLocation = 2 * i;
      var secondLocation = 2 * i + 1;
      var charAt = upperGene.charAt(i);
      switch (charAt) {
        case 'A' -> { // 00 are next two bits
          bitSet.set(firstLocation, false);
          bitSet.set(secondLocation, false);
        }
        case 'C' -> { // 01 are next two bits
          bitSet.set(firstLocation, false);
          bitSet.set(secondLocation, true);
        }
        case 'G' -> { // 10 are next two bits
          bitSet.set(firstLocation, true);
          bitSet.set(secondLocation, false);
        }
        case 'T' -> { // 11 are next two bits
          bitSet.set(firstLocation, true);
          bitSet.set(secondLocation, true);
        }
        default -> throw new IllegalArgumentException("Illegal character other than ACGT, position: " + i + ", char: " + charAt);
      }
    }
  }

  public String decompress() {
    if (bitSet == null) {
      return "";
    }
    // create a mutable place for characters with right capacity
    var builder = new StringBuilder(length);
    for (var i = 0; i < (length * 2); i += 2) {
      var firstBit = (bitSet.get(i) ? 1 : 0);
      var secondBit = (bitSet.get(i + 1) ? 1 : 0);
      var lastBits = firstBit << 1 | secondBit;
      switch (lastBits) {
        case 0b00 -> builder.append('A'); // 00 is 'A'
        case 0b01 -> builder.append('C'); // 01 is 'C'
        case 0b10 -> builder.append('G'); // 10 is 'G'
        case 0b11 -> builder.append('T'); // 11 is 'T'
        default -> throw new IllegalArgumentException("Unknown last bits " + lastBits);
      }
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    var original = "TABGGGATTAACCGTTATATATATATAGCCATGGATCGATTATATAGGGATTAACCGTTATATATATATAGCCATGGATCGATTATA";
    System.out.println(original.length());
    var compressed = new CompressedGene(original);
    System.out.println(compressed.bitSet().length());
    var decompressed = compressed.decompress();
    System.out.println("Original is the same as decompressed: " + original.equals(decompressed));
  }
}
