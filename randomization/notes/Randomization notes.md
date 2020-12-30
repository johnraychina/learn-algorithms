# https://www.taodudu.cc/news/show-610298.html
蒙特卡罗（Monte Carlo）算法
举个例子，假如筐里有100个苹果，让我每次闭眼拿1个，挑出最大的。
于是我随机拿1个，再随机拿1个跟它比，留下大的，再随机拿1个……我每拿一次，留下的苹果都至少不比上次的小。
拿的次数越多，挑出的苹果就越大，但我除非拿100次，否则无法肯定挑出了最大的。这个挑苹果的算法，就属于蒙特卡罗算法——尽量找好的，但不保证是最好的。

https://www.zhihu.com/question/20254139/answer/49246507
蒙特卡罗算法——大家听说过蒙特卡罗求π吧？就是画一个正方形和内切圆，随机撒点，数一下点落在园内和正方形内的数量之比，就是二者面积之比π/4。
所以蒙特卡罗就是求面积的方法。
而积分是曲线下的面积，所以蒙特卡罗就是求积分的方法。
而均值就是概率密度与自变量乘积的积分，所以蒙特卡罗就是求均值的方法。
而期望就是均值，所以蒙特卡罗就是求期望的方法。
而最优值往往接近或就是期望，所以蒙特卡罗就是求最优值的方法。


拉斯维加斯（Las Vegas）算法
假如有一把锁，给我100把钥匙，只有1把是对的。
于是我每次随机拿1把钥匙去试，打不开就再换1把。我试的次数越多，打开（最优解）的机会就越大，但在打开之前，那些错的钥匙都是没有用的。
这个试钥匙的算法，就是拉斯维加斯算法——尽量找最好的，但不保证能找到。

比如N皇后的排列问题，除了顺序枚举法之外，随机枚举也是一种策略。




# https://www.jianshu.com/p/4aee0bbb8c6d
随机性和不确定性是现实世界中许多机器学习场景的关键要素。随机化方法是机器学习领域，它为输入中不确定性和随机性因素的算法建模。
很多人可能都熟悉机器学习领域中最著名的随机算法:蒙特卡洛方法。
蒙特卡洛技术属于随机算法的范畴，它试图为一个具有一定随机性的问题提供答案。
在这个领域，蒙特卡洛方法被视为另一个算法的替代品:拉斯维加斯。

## 拉斯维加斯方法
蒙特卡罗和拉斯维加斯技术的主要区别在于输出的准确性。
拉斯维加斯方法总是提供一个确切的答案，而蒙特卡洛方法是返回的答案与随机数量的误差。显然，蒙特卡洛系统的误差程度随着数据或计算模型等资源的增加而减小。
拉斯维加斯算法的一个典型例子是随机快速排序算法，它随机选取一个pivot，然后将元素分成三组：所有小于pivot的元素、所有等于pivot的元素以及所有大于pivot的元素。

随机快速排序方法往往消耗大量资源，但保证了确切的答案。因此，在具有少量潜在答案的情景中，倾向于推荐拉斯维加斯方法。

尽管拉斯维加斯模型在理论上看起来很棒，但它们在许多深度学习场景中都是不切实际的，这些场景如此之大，以至于永远无法产生精确答案。
蒙特卡罗技术通过提高计算图的效率来解决拉斯维加斯算法的一些局限性，从而在答案中引入一定程度的随机性。
毫不奇怪，蒙特卡罗技术在处理多维，大容量数据集的深度学习场景中变得非常流行。

## 蒙特卡罗方法
蒙特卡罗方法在深度学习系统中的主要应用之一是从表示数据集的一些概率分布中抽取样本。
这通常被称为蒙特卡罗采样，并且在历史上被广泛用于解决高度复杂的数据估计问题。
在一个最臭名昭着的例子中，法国数学家Pierre-Simon Laplace曾经提出一个利用蒙特卡罗抽样估计pi值的方法。

在深度学习系统的背景下，蒙特卡罗采样方法具有非常着名的应用。
例如，通常利用蒙特卡罗采样来选择近似于原始数据集的训练数据集的分布。蒙特卡罗方法也在正则化或优化技术中起作用，估计输出数据集而无需评估整个计算图。

有几种蒙特卡罗技术已在现代深度学习平台中广泛实施。蒙特卡洛家族中最着名的成员是将马尔科夫链带入随机世界的技术，通常称为马尔可夫链蒙特卡罗方法（MCMC）。

## 马尔可夫链蒙特卡罗方法（MCMC）

MCMC模型的主要目的是使用马尔可夫随机游走算法获得关于分布的信息。这是MCMC技术能够学习概率分布的基本属性而不必对其所有成员进行抽样的一种奇妙的说法。
读这篇文章你可能会感到困惑。蒙特卡罗方法的作用不是从分布中抽取例子吗？如果是这样，MCMC又有什么不同呢？

MCMC方法的主要区别在于使用马尔可夫链使用特殊的顺序过程生成样本。
虽然独立的蒙特卡罗方法能够从分布中生成样本，但是在许多情况下，没有可处理的方法从数据集中提取精确的示例。
马尔可夫链是传统蒙特卡罗方法的补充，它使用了一个模型，其中每个随机样本都被用作生成下一个随机样本(因此称为链)的垫脚石。
链的一个独特好处是，尽管每个新样本依赖于它之前的样本，但新样本不依赖于前一个样本之前的任何样本(这是“马尔科夫”属性)。

MCMC in Action
让我们用机器学习文献中的一个经典例子来说明MCMC模型的价值。假设一个讲师对学生群体的平均考试成绩感兴趣。
虽然平均分是未知的，但是知道分数是正态分布的，标准差是15。到目前为止，讲师只观察了一个学生的测试分数:100。
可以使用MCMC从目标分布中提取样本，在这种情况下是后验概率，表示给定总体均值的每个可能值的概率。

为了从测试分数的分布中抽取样本，MCMC从一个初始的猜测开始:从分布中抽取一个值。假设这个初始值是110。
然后用MCMC从最初的猜测中得到一系列新的样品。
每一个新样本都由两个简单的步骤产生:
首先，通过向最近的样本添加一个小的随机perturbation来创建新样本的提案;
其次，这个新提案要么被作为新样本接受，要么被拒绝(在这种情况下，旧的样本被保留)。
通过不断重复这个过程，MCMC模型应该产生一系列非常接近原始概率分布的样本。

如果我们接受随机性是社会经济动力学的一个关键特征，那么构建理解随机输入的算法是机器学习主流采用的一个重要因素。
拉斯维加斯和蒙特卡罗方法都在提高机器学习方法对随机性的适应性方面发挥着重要作用。我们可以在许多流行的深度学习框架中找到这两类算法的几种实现。


# https://en.wikipedia.org/wiki/Las_Vegas_algorithm
In computing, a Las Vegas algorithm is a randomized algorithm that always gives correct results; 
that is, it always produces the correct result or it informs about the failure. 
在计算领域，拉斯维加斯算法是一种随机化算法，它总是给出正确的结果，也就是说它总是产生正确的结果或者告知错误。


However, the runtime of a Las Vegas algorithm differs depending on the input. 
The usual definition of a Las Vegas algorithm includes the restriction that the expected runtime be finite, 
where the expectation is carried out over the space of random information, or entropy, used in the algorithm. 
但是拉斯维加斯算法会因为输入不同产生不同的运行时间。
它的定义通常包括：约束算法执行时间必须是有限的，期望值是按算法中使用的随机信息空间（或者熵）计算得到。

An alternative definition requires that a Las Vegas algorithm always terminates (is effective), 
but may output a symbol not part of the solution space to indicate failure in finding a solution.[1] 
另外一种定义要求拉斯维加斯算法必须停止（即它必须是有效的），但是会产生非解空间的值表明搜索失败。

The nature of Las Vegas algorithms makes them suitable in situations where the number of possible solutions is limited, 
and where verifying the correctness of a candidate solution is relatively easy while finding a solution is complex.
拉斯维加斯算法的特性让它非常适合  那些可能解决方案数是有限的，并且相对容易校验候选方案正确性的，但找到解决方案有非常困难的情况。


Las Vegas algorithms are prominent in the field of artificial intelligence, and in other areas of computer science 
and operations research. In AI, stochastic local search (SLS) algorithms are considered to be of Las Vegas type. 
拉斯维加斯算法在人工智能领域、其他计算机科学、运筹学等领域前景广阔。在人工智能领域，随机本地搜索（SLS）算是一种拉斯维加斯算法类型。


Recently, SLS algorithms have been used to address NP-complete decision problems and NP-hard combinatorial optimization problems.[2] 
最近，随机本地搜索（SLS）被用来解决NP-完备 决策问题和NP-困难的组合优化问题。

However, some systematic search methods, such as modern variants of the Davis–Putnam algorithm for propositional satisfiability (SAT), 
also utilize non-deterministic decisions, and can thus also be considered Las Vegas algorithms.[3]
然而，一些系统化搜索方法，比如 Davis-Putnam现代的变种也利用了非确定性决策，所以也被归为拉斯维加斯算法一族。

```text
// Las Vegas algorithm
repeat:
    k = RandInt(n)
    if A[k] == 1,
        return k;
```


                        Running Time    Correctness
Las Vegas Algorithm     probabilistic   certain
Monte Carlo Algorithm   certain         probabilistic


## https://en.wikipedia.org/wiki/Monte_Carlo_algorithm
In computing, a Monte Carlo algorithm is a randomized algorithm whose output may be incorrect with a certain (typically small) probability. 
Two examples of such algorithms are Karger–Stein algorithm[1] and Monte Carlo algorithm for minimum Feedback arc set.[2]
在计算领域中，蒙特卡罗算法是一种随机化的算法，它的输出可能小概率是不准确的。两个例子：Karger-Stein算法和蒙特卡罗minimum Feedback arc set.


The name refers to the grand casino in the Principality of Monaco at Monte Carlo, which is well-known around the world as an icon of gambling. 
The term "Monte Carlo" was first introduced in 1947 by Nicholas Metropolis.[3]
该算法的名于蒙特卡罗大赌场


Las Vegas algorithms are the subset of Monte Carlo algorithms that always produce the correct answer. 
Because they make random choices as part of their working, the time taken might vary between runs even with the same input.
拉斯维加斯算法是蒙特卡罗算法中一个子集，它总产生正确的答案。因为他们工作时采取随机选择，相同的输入花费不同的时间。

If there is a procedure for verifying whether the answer given by a Monte Carlo algorithm is correct, 
and the probability of a correct answer is bounded above zero, 
then with probability one running the algorithm repeatedly while testing the answers will eventually give a correct answer. 
Whether this process is a Las Vegas algorithm depends on whether halting with probability one is considered to satisfy the definition.
 
