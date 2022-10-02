// Fib4Iteration.java
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

import java.util.Locale;
import utils.TimeWatch;

public class Fib4Iteration {

	private static long fib4(int n) {
		var last = 0L; // fib(0)
		var next = 1L; // fib(1)
		for (var i = 0; i < n; i++) {
			var oldLast = last;
			last = next;
			next = oldLast + next;
		}
		return last;
	}

	public static void main(String[] args) {
		TimeWatch watch = TimeWatch.start();
		System.out.printf("result: %d, time: %s%n", fib4(100), watch.format());
	}
}
