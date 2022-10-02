// Fib5Stream.java
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

import java.util.stream.LongStream;
import utils.TimeWatch;

public class Fib5Stream {

  private long last = 0;
  private long next = 1; // fib(0), fib(1)

  public LongStream stream() {
    return LongStream.generate(this::fib5);
  }

  private long fib5() {
    var oldLast = last;
    last = next;
    next = oldLast + next;
    return oldLast;
  }

  public static void main(String[] args) {
    var watch = TimeWatch.start();
    Fib5Stream fib5Stream = new Fib5Stream();
    fib5Stream.stream().limit(50).forEachOrdered(System.out::println);
    System.out.println(watch.format());
  }
}
