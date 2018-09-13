package lab8.sounds;

import javax.sound.sampled.*;
import java.io.*;

public class Sound {
    private boolean released = false;
    private Clip clip = null;
    private FloatControl volumeC = null;
    private boolean playing = false;

    Sound(String soundName) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(soundName));
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.addLineListener(new Listener());
            volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            released = true;
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
            released = false;
        }
    }

    //true если звук успешно загружен, false если произошла ошибка
    boolean isReleased() {
        return released;
    }

    //проигрывается ли звук в данный момент
    boolean isPlaying() {
        return playing;
    }

    //Запуск
	/*
	  breakOld определяет поведение, если звук уже играется
	  Если reakOld==true, о звук будет прерван и запущен заново
	  Иначе ничего не произойдёт
	*/
    void play(boolean breakOld) {
        if (released) {
            if (breakOld) {
                clip.stop();
                clip.setFramePosition(0);
                clip.start();
                playing = true;
            } else if (!isPlaying()) {
                clip.setFramePosition(0);
                clip.start();
                playing = true;
            }
        }
    }

    public void playWithOverlay() {
        clip.setFramePosition(0);
        clip.start();
    }

    //То же самое, что и play(true)
    public void play() {
        if (!isPlaying()) {
            clip.setFramePosition(0);
            clip.start();
            playing = true;
        }
        //play(false);
    }

    //Останавливает воспроизведение
    public void stop() {
        if (playing) {
            clip.stop();
        }
    }

    //Установка громкости
	/*
	  x долже быть в пределах от 0 до 1 (от самого тихого к самому громкому)
	*/
    public void setVolume(float x) {
        if (x<0) x = 0;
        if (x>1) x = 1;
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        volumeC.setValue((max-min)*x+min);
    }

    //Возвращает текущую громкость (число от 0 до 1)
    public float getVolume() {
        float v = volumeC.getValue();
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        return (v-min)/(max-min);
    }

    //Дожидается окончания проигрывания звука
    void join() {
        if (!released) return;
        synchronized(clip) {
            try {
                while (playing) clip.wait();
            } catch (InterruptedException exc) {}
        }
    }

    //Статический метод, для удобства
    public static Sound playSound(String s) {
        Sound snd = new Sound(s);
        snd.play();
        return snd;
    }

    private class Listener implements LineListener {
        public void update(LineEvent ev) {
            if (ev.getType() == LineEvent.Type.STOP) {
                playing = false;
                synchronized(clip) {
                    clip.notify();
                }
            }
        }
    }
}
