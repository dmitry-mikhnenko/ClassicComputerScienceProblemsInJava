package utils;

import java.util.concurrent.TimeUnit;

public final class TimeWatch {

  private long starts;

  private TimeWatch() {
    reset();
  }

  public static TimeWatch start() {
    return new TimeWatch();
  }

  public TimeWatch reset() {
    starts = System.currentTimeMillis();
    return this;
  }

  public long time() {
    long ends = System.currentTimeMillis();
    return ends - starts;
  }

  public long time(TimeUnit unit) {
    return unit.convert(time(), TimeUnit.MILLISECONDS);
  }

  public String format() {
    return format(time());
  }

  public String format(long interval) {
    final long sec = interval / 1000;
    final long ms = interval % 1000;
    return String.format("%d.%03ds", sec, ms);
  }
}
