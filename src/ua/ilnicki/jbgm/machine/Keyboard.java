package ua.ilnicki.jbgm.machine;

import java.util.HashMap;

/**
 * Клавиатура игрового устройсятва.
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class Keyboard
{

    /**
     * Клавиши используемые для управления игрой.
     */
    public static enum CtrlKey
    {
        /**
         * Клавиша UP
         */
        UP,
        /**
         * Клавиша RIGHT/SPEED
         */
        RIGHT,
        /**
         * Клавиша DOWN/GAME
         */
        DOWN,
        /**
         * Клавиша LEFT/LEVEL
         */
        LEFT,
        /**
         * Клавиша ROTATE DIRECTION
         */
        ENTER
    }

    /**
     * Клавиши используемые для управления устройством.
     */
    public static enum SysKey
    {
        /**
         * Клавиша RESET
         */
        RESET,
        /**
         * Клавиша SOUND
         */
        SOUND,
        /**
         * Клавиша START/PAUSE
         */
        START,
        /**
         * Клавиша ON/OFF
         */
        ONOFF
    }

    private final HashMap<CtrlKey, Boolean> ctrlKeysMap;
    private final HashMap<SysKey, Boolean> sysKeysMap;

    /**
     * Инациализирует карты с клавишами для управляющих и системных клавиш.
     */
    public Keyboard()
    {
        this.ctrlKeysMap = new HashMap<>();
        for (CtrlKey key : CtrlKey.values())
        {
            this.ctrlKeysMap.put(key, false);
        }

        this.sysKeysMap = new HashMap<>();
        for (SysKey key : SysKey.values())
        {
            this.sysKeysMap.put(key, false);
        }
    }

    /**
     * Метод проверяет, нажата ли управляющая клавиша.
     *
     * @param key Требуемая клавиша - объект перечисления {@link ua.ilnicki.jbgm.machine.Keyboard.CtrlKey}.
     *
     * @return Результат проверки нажатия управляющей клавиши. Возвращает true - если указанная клавиша нажата и false в противном случае.
     */
    public boolean isCtrlKeyDown(CtrlKey key)
    {
        return this.ctrlKeysMap.get(key);
    }

    /**
     * Устанавливает значение управляющей клавиши.
     *
     * @param key Требуемая клавиша - объект перечисления {@link ua.ilnicki.jbgm.machine.Keyboard.CtrlKey}.
     * @param state Состояние, в которое устанавливается клавиша: true - нажата, false - отжата.
     */
    public void setCtrlKeyState(CtrlKey key, boolean state)
    {
        this.ctrlKeysMap.put(key, state);
    }

    /**
     * Метод проверяет, нажата ли системная клавиша.
     *
     * @param key Объект перечисления {@link ua.ilnicki.jbgm.machine.Keyboard.SysKey}.
     *
     * @return Результат проверки нажатия системной клаиши. Возвращает true - если указанная клавиша нажата и false в противном случае.
     */
    public boolean isSysKeyDown(SysKey key)
    {
        return this.sysKeysMap.get(key);
    }

    /**
     * Устанавливает значение системной клавиши.
     *
     * @param key Объект перечисления {@link ua.ilnicki.jbgm.machine.Keyboard.SysKey}.
     * @param state Состояние, в которое устанавливается клавиша: true - нажата, false - отжата.
     */
    public void setSysKeyState(SysKey key, boolean state)
    {
        this.sysKeysMap.put(key, state);
    }

    /**
     * Создает и возвращает объект {@link ua.ilnicki.jbgm.machine.Keyboard.KeyboardPasser}.
     *
     * @return {@link ua.ilnicki.jbgm.machine.Keyboard.KeyboardPasser} созданный из текущего объекта {@link ua.ilnicki.jbgm.machine.Keyboard}
     */
    public KeyboardPasser getKeyboardPasser()
    {
        return new KeyboardPasser(this);
    }

    /**
     * Класс, предназначеный для передачи значений клавиатуры во внешние приложения, подключаемые к машине.
     */
    public static class KeyboardPasser
    {
        private final Keyboard kb;

        private KeyboardPasser(Keyboard kb)
        {
            this.kb = kb;
        }
        
        /**
         * Метод проверяет, нажата ли управляющая клавиша.
         * @param key Требуемая клавиша - объект перечисления {@link ua.ilnicki.jbgm.machine.Keyboard.CtrlKey}.
         * @return Результат проверки нажатия управляющей клавиши. Возвращает true - если указанная клавиша нажата и false в противном случае.
         */
        public boolean isKeyDown(CtrlKey key)
        {
            return kb.isCtrlKeyDown(key);
        }
    }
}
