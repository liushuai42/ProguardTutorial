# Proguard 入门指南
## 1. 解决什么样的问题
- 减少包的大小，清理无用的类、字段、方法、属性等。通过混淆，缩短类、字段、方法名的长度也可以有效的减少包大小。
- 清理无用的类，减轻64K的问题。
- 在 **一定程度上** 可以防止逆向工程。
- 针对 library project 明确对外的接口
## 2. 原理处理流程
```
graph LR
subgraph inputs
  A[Input jars]
    F[Library jars]
	end

	subgraph outputs
	  E[Output jars]
	    G[Library jars]
		end

		A--"shrink"--> B[Shrunk code]
		B--"optimize"--> C[Optim. code]
		C--"obfuscate"--> D[Obfusc. code]
		D--"preverify"--> E[Output jars]

		F[Library jars]--"(unchanged)"--> G[Library jars]

		style F fill:#8888FF,stroke:#333;
		style G fill:#8888FF,stroke:#333;
		```
		流程中的步骤都是可选的, Optimize 步骤可以与 Shrinking 步骤一起多次执行
		- **shrinking** 检测并删除无用的类、字段、方法、属性。
		- **optimization** 分析优化 bytecode。
		- **obfuscation** 使用无意义的名字重命名类、字段、方法、属性。
		- **preverify** 添加 preverifaction 信息到 class 中，J2ME 版本需要，优化类的加载速度。

## 3. 配置文件及输出
		配置文件可以再 build.gradle 中通过 proguardFiles 指定，并且在开启*minifyEnabled*的情况下启用。
		```groovy
		minifyEnabled true
		proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
		```
		Android SDK提供了两个默认的选项文件供选择，不开启优化(**proguard-android.txt**)和开启优化(**proguard-android-optimize.txt**)  
	输出的清单可以在 <module-name>/build/outputs/mapping/release/ 目录下找到

	|文件名|说明|
	|---|---|
	|dump.txt|Describes the internal structure of all the class files in the APK.|
	|mapping.txt|Provides a translation between the original and obfuscated class, method, and field names.|
	|seeds.txt|Lists the classes and members that were not obfuscated.|
	|usage.txt|Lists the code that was removed from the APK.|

## 4. 如何配置及常见的配置项
	|关键字|影响的阶段|说明|
	|---|---|---|
	|-dontusemixedcaseclassnames|Obfuscation|不要使用大小写混合的类名，而仅仅使用小写|
	|-dontskipnonpubliclibraryclasses|obfuscation|不要忽略私有的类|
	|-verbose|General|啰嗦模式，打印信息到命令行输出|
	|-dontoptimize|Optimization|不要进行优化|
	|-dontpreverify|Preverification|不要进行preverify|
	|-keepattributes|Obfuscation|保留相关的LineNumberTable, LocalVariableTable, SourceFile, Deprecated, Synthetic, Signature, and InnerClasses等信息|
	|-keep|Keep|指定的类和类的成员（字段和方法）将会被作为 Entry Points 保留，在进行Proguard时，保留*类名和指定的成员*|
	|-keepclassmembers|Keep|指定类的成员（字段和方法）将会被作为 Entry Points 保留。仅在所属的类被保留的情况下生效，保留*指定的成员（字段和方法）*|
	|-keepclasseswithmember|Keep|完整包含指成员（字段和方法）的类将会被作为 Entry Points 保留。区别在于，通过指定的成员来匹配需要保留的类，而不仅仅是通过类名的模式来匹配。保留*匹配条件的类名和指定的成员。*|
	|-keepnames|Keep|Short for -keep,allowshrinking|
	|-keepclassmembernames|Keep|Short for -keepclassmembers,allowshrinking|
	|-keepclasseswithmembernames|Keep|Short for -keepclasseswithmembers,allowshrinking|
	|-dontwarn|General|指定忽略为解决的引用问题和其他重要的问题|
	|-dontnote|General|指定忽略配置文件中未发现的类，例如配置一个保留的类，可是class未发现|
	|-allowaccessmodification|Optimization|允许修改访问修饰符，比如在inline时，可能同时修改被访问字段。仅在优化阶段，并且指定*-repackageclass*生效|
	|-repackageclass|Obfuscation|指定混淆后的类归属的包名|

## 5. 常见的问题及解决方法
	- WARN/NOTE
	根据上一个章节，我们已经清楚的知道 *-dontnote* 和 *-dontwarn* 的含义，如果在工作中提示有 note 和 warn 信息时，确信 App 不会受到影响，则可以使用这两条指令忽略他们。
	- Duplicate class
	- 重复引入包的问题/library project

## 6. 参考文献
	1. [Proguard Manual](https://stuff.mit.edu/afs/sipb/project/android/sdk/android-sdk-linux/tools/proguard/docs/index.html)
	1. [Shrink Your Code and Resources](https://developer.android.com/studio/build/shrink-code.html)

