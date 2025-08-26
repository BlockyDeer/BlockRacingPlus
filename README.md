# BlockRacingPlus

__方块竞速BlockRacingPlus，和自己的朋友们玩的修改版本。整体上降低了难度，对人少的情况友好__

# **该版本已不再维护**

原作者的仓库链接在这里( **LQSnow** )：[传送门](https://github.com/LQSnow/BlockRacing)  
此README在原作者的基础上有删改  

这是一个Minecraft多人竞速小游戏，分为两个队伍，先收集完指定方块的队伍获胜。

版本：Java 1.19.3

# 特色功能

1. 队伍箱子：每个队伍都有3个队伍箱子，箱子里的物品对同队伍成员共享。

2. 奖励机制：每收集一个方块，对方队伍将会获得一组该方块（存放在队伍箱子里）。

3. 队伍TP：同队伍之间可以自由TP。

4. Roll：当前所需方块太难获取时，可以花费队伍积分轮换掉，每局限3次。  
    __BlockyDeer注：这里原作者的设计是每回合只能使用一次Roll，并且只能Roll出普通方块。这里改为每局每队3次后，为了游戏平衡性考虑改为了单纯的重轮换__

5. 定位：玩家可以花费队伍积分购买locate指令，用于定位群系或结构。

6. 记录点：每个队伍有3个记录点，可以自由保存、传送、删除。

# 玩法说明

## 准备阶段

- 进入游戏后，按Shift+F打开菜单。
- 在菜单左上角进行选队。
- 菜单左下角可以设置目标方块库，可以选择性开启中等难度方块、困难难度方块、染色方块和末地方块。
- 菜单右下角可以设置目标方块数量。
- 菜单右下角可以切换模式（普通模式或极限竞速模式）
- 所有玩家准备后，即可在菜单右上角点击钻石开始游戏。
- 所有玩家输入/restartgame可以关闭服务器（通过后续设置可以实现自动重启）。

## 游戏阶段

- 随机传送后，按照记分板上的内容，开始收集方块吧。
  
- Shift+F可以打开菜单，在菜单里可以使用队伍箱子、Roll、定位、记录点、随机传送功能。
  
- 购买定位权限后，可以输入/locate <structure | biome> \<name>进行定位。
  
- 输入/tp \<teammates>可以TP队友。


# 安装教程

1. 准备一个Paper服务器  
（BlockyDeer注：你也可以使用[Spigot](https://www.spigotmc.org/)，或者[Purpur](https://purpurmc.org/)，总之Bukkit系的服务端都可以）

2. 下载插件，将插件放到服务器目录下的`plugins`文件夹中  

3.
   （**推荐**）  
   将`server.properties`中的`spawn-protection`后的值（一般来说默认是16）改为0  

   __解释：此为出生点保护，打开可能会导致出生点附近无法破坏方块，防止方块等“bug”__  

   将`server.properties`中的`view-distance`（视野距离）调整为你的服务器带宽与内存允许的值（个人推荐6）  

   将`server.properties`中的`simulation-distance`（模拟距离）调整为你的服务器CPU可接受的值  

4. 你也可以考虑将`config`文件夹中的`paper-global.yml`允许活塞头破基岩等原版特性打开  

5.
   推荐将种子留空，玩完一局后将`world`，`world_nether`，`world_the_end`三个文件夹删除，起到重置种子的作用。  
   __BlockyDeer注：建议自行筛一定范围内全群系种子，新版本生物群系规模是老版本的四倍，在不会locate biomes的情况下，跑图非常折磨。但是要记得每次重开都要把seed栏中的位置填上新种子名__  

   你也可以更改服务器启动文件（start.bat）以自动重启、自动重置种子（seed必须留空）：

   ```
   :start
   java -Xmx4G -Xms4G -jar server.jar nogui
   rd /s /q world
   rd /s /q world_nether
   rd /s /q world_the_end
   timeout /nobreak /t 5
   goto start
   ```

   记得修改server.jar为你的服务器核心文件名，并按实际情况分配内存。

# 目标方块库修改

在服务器文件夹下的plugins\BlockRacing目录中，存在下面这几个文件：

```
EasyBlocks.txt 简单方块库
NormalBlocks.txt 中等方块库
HardBlocks.txt 困难方块库
DyedBlocks.txt 染色方块库
EndBlocks.txt 末地方块库
zh_cn.json 翻译文件
```

除了翻译文件，剩下的方块库文件你可以自由修改，但请注意：

1. 每行只写一个方块名
   
2. 方块名使用全大写的方块命名空间
   
3. 5个方块库中不要有重复方块
   
4. 不要有空行、空格等字符
   
5. 不要修改文件名，不要删除文件
   
6. 游戏内除了简单方块库必选以外，剩下的都可以选择性开启，如非特殊需要不要动这5个文件

# 版权说明

该项目签署 [**GNU Affero General Public License v3.0**](https://github.com/LQSnow/BlockRacing/blob/main/LICENSE) 授权许可

The project is licensed under the [**GNU Affero General Public License v3.0**](https://github.com/LQSnow/BlockRacing/blob/main/LICENSE)
