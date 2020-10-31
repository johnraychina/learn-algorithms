

## RE与DFA对偶性 (Kleene's theorem )
正则表达式RE(Regular Expression): 精确定义一组字符串
有限自动机DFA(Deterministic Finite Automaton): 判别某个字符串是否在给定的一组字符串中的"机器"

## Kleene理论指出：
- 对于任何DFA，都存在一个RE描述相同的一组字符串。
- 对于任何RE，都存在一个DFA来判别相同的一组字符串。


## 那么如何实现模式识别呢Pattern Matching ?
基本思路：从RE构建一个DFA，然后用DFA对输入进行模拟。
坏消息是：简单方案不可行，因为DFA的状态数是指数级别的。
修正方案：从RE构建一个NFA，用NFA对输入进行模拟

Kleene's theorem 指出DFA是指数级复杂度
用NFA代替可以保证平方级别（通常是线性）复杂度

## NFA非确定自动机是什么呢？
NFA(Non-deterministic Finite Automaton)是一个非确定的 状态转换的自动机器，模拟字符串（匹配）状态的转换。

**一组状态:**
其中accept state是接受状态

**状态转换**
对文本进行顺序扫描
    每次扫描到一个文本字符，NFA做一次对应的状态转换，
    其中epsilon转换表示转换到下个状态时不扫描文本。
    如果转换到accept 状态则表示匹配成功。

匹配字符串时，可能存在多个可匹配的状态，
再转换到下个状态时，就需要从多个状态转换到多个状态，
如何表示呢？
用一个DiGraph来表示这种可达性






