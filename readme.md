# Client Might

----------------------------

Client Might 是一款开源的文本编辑器

内置Shell还有**得意黑**字体

启动器源码一并开源，可使用Visual Studio 2022 Community 打开

---------------------
## 构建

如何构建并使用ClientMight

首先你需要下载[IntelliJ IDEA](https://www.jetbrains.com/zh-cn/idea/)
还有[Visual Studio 2022 Community](https://visualstudio.microsoft.com/zh-hans/)

并且安装Java17JDK

### Client Might构建

使用**IntelliJ IDEA**打开Client Might的源代码文件夹

运行构建 - 构建工件 - Client Might.jar

生成的Jar文件在out文件夹内，out文件夹内自带资源，无需额外导入资源

注意:Client Might由于某些原因无法进行调试，只能生成jar包运行，但是软件内置调试，可用debug命令打开

软件文件夹内jre是java及时运行环境，可在没有java的电脑上运行此软件

### UserStart 用户启动器构建

使用**Visual Studio 2022 Community**打开**UserStart.sln**文件

运行生成 - 生成项目 | 即可生成启动器的静态打包文件

将启动器放入jar存在根目录即可运行软件

------------------

## 软件打包和运行
### 打包
将软件打包完成后即可进入最后打包运行的工作

打开jar打包目录，将编译好的**UserStart.exe**放入文件夹根目录

软件运行会自动进行初始化创建**Config**文件夹和**Plugins**文件夹，所以无需将这两个文件夹打包进发行版内

软件打包可以用7z等程序创建自解压或压缩包程序，打包目录只需**ClientMight.jar** | **UserStart.exe**打包

软件目录也仅需**Assets** | **jre** 这两个文件夹即可

### 运行

启动**UserStart.exe**可以发现两个按钮一个是**Start**一个是**Debug**

**Debug**可以启动一个控制台进行调试**Start**可以直接运行软件

--------

[KPGEY工作室官网](https://kpgeystudio.site/)

Technical support By @LeadinEA and @hedgehog


![](https://kpgeystudio.site/logo3.jpg)