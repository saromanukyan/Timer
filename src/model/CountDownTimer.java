package model;

public class CountDownTimer {
    private int minute = 99;
    private int second = 59;

    public CountDownTimer(int minute, int second) {
        setMinute(minute);
        setSecond(second);
    }

    private void setMinute(int minute) {
        if (minute <= 99) {
            this.minute = minute;
        }
    }

    public int getMinute() {
        return minute;
    }

    private void setSecond(int second) {
        if (second <= 59) {
            this.second = second;
        }
    }

    public int getSecond() {
        return second;
    }
}
