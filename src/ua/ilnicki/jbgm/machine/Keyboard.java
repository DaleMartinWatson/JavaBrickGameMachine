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
         *//**
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
        ROTATE
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

    private final HashMap<CtrlKey, Integer> ctrlKeysMap;
    private final HashMap<SysKey, Integer> sysKeysMap;

    /**
     * Инациализирует карты с клавишами для управляющих и системных клавиш.
     */
    public Keyboard()
    {
        this.ctrlKeysMap = new HashMap<>();
        for (CtrlKey key : CtrlKey.values())
        {
            this.ctrlKeysMap.put(key, -1);
        }

        this.sysKeysMap = new HashMap<>();
        for (SysKey key : SysKey.values())
        {
            this.sysKeysMap.put(key, -1);
        }
    }

    /**
     * Метод проверяет, нажата ли управляющая клавиша.
     *
     * @param key Объект перечисления {@link ua.ilnicki.jbgm.machine.Keyboard.CtrlKey}.
     *
     * @return Результат проверки нажатия управляющей клаиши. Возвращает true - если указанная клавиша нажата и false в противном случае.
     */
    public boolean isCtrlKeyDown(CtrlKey key)
    {
        return this.ctrlKeysMap.get(key) > -1;
    }
    
    /**
     * Метод возвращает время, на протяжении которого нажата управляющая клавиша.
     *
     * @param key Объект перечисления {@link ua.ilnicki.jbgm.machine.Keyboard.CtrlKey}.
     *
     * @return Время, на протяжении которого нажата клавиша. Если результат равен -1, то клавиша отжата.
     */
    public int ctrlKeyDownTicksCount(CtrlKey key)
    {
        return this.ctrlKeysMap.get(key);
    }

    /**
     * Обновляет значение управляющей клавиши. Если клавиша нажата, то счетчик 
     * времени нажатия увеличивается на 1, в противном случае устанавливается в -1.
     *
     * @param key Объект перечисления {@link ua.ilnicki.jbgm.machine.Keyboard.CtrlKey}.
     * @param state Состояние, в котором находится клавиша: true - нажата, false - отжата.
     */
    public void updateCtrlKeyState(CtrlKey key, boolean state)
    {
        this.ctrlKeysMap.put(key, state ? this.ctrlKeysMap.get(key) + 1 : -1);
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
        return this.sysKeysMap.get(key) > -1;
    }
    
    /**
     * Метод возвращает время, на протяжении которого нажата системная клавиша.
     *
     * @param key Объект перечисления {@link ua.ilnicki.jbgm.machine.Keyboard.SysKey}.
     *
     * @return Время, на протяжении которого нажата клавиша. Если результат равен -1, то клавиша отжата.
     */
    public int sysKeyDownTicksCount(SysKey key)
    {
        return this.sysKeysMap.get(key);
    }

    /**
     * Обновляет значение системной клавиши. Если клавиша нажата, то счетчик 
     * времени нажатия увеличивается на 1, в противном случае устанавливается в -1.
     *
     * @param key Объект перечисления {@link ua.ilnicki.jbgm.machine.Keyboard.SysKey}.
     * @param state Состояние, в котором находится клавиша: true - нажата, false - отжата.
     */
    public void updateSysKeyState(SysKey key, boolean state)
    {
        this.sysKeysMap.put(key, state ? this.sysKeysMap.get(key) + 1 : -1);
    }

    /**
     * Создает и возвращает объект {@link ua.ilnicki.jbgm.machine.Keyboard.KeyboardPasser}.
     *
     * @return {@link ua.ilnicki.jbgm.machine.Keyboard.KeyboardPasser} созданный из текущего объекта {@link ua.ilnicki.jbgm.machine.Keyboard}
     */
    public KeyboardPasser getKeyboardPasser()
    {
        return new KeyboardPasser();
    }

    /**
     * Класс, предназначеный для передачи значений клавиатуры во внешние приложения, подключаемые к машине.
     */
    public class KeyboardPasser
    {
        /**
         * Метод проверяет, нажата ли управляющая клавиша.
         * @param key Требуемая клавиша - объект перечисления {@link ua.ilnicki.jbgm.machine.Keyboard.CtrlKey}.
         * @return Результат проверки нажатия управляющей клавиши. Возвращает true - если указанная клавиша нажата и false в противном случае.
         */
        public boolean isKeyDown(CtrlKey key)
        {
            return Keyboard.this.isCtrlKeyDown(key);
        }
        
        /**
         *
         * @param key
         * @return
         */
        public int keyDownTicksCount(CtrlKey key)
        {
            return Keyboard.this.ctrlKeyDownTicksCount(key);
        }
    }
}
