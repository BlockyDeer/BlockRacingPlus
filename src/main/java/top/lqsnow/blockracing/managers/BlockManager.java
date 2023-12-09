package top.lqsnow.blockracing.managers;

import org.bukkit.Material;
import top.lqsnow.blockracing.utils.BlockDifficulty;

import java.util.*;

import static top.lqsnow.blockracing.listeners.EventListener.blockAmount;
import static top.lqsnow.blockracing.listeners.EventListener.*;

public class BlockManager {
    // 设置方块库
    public static boolean blockAmountCheckout = false;
    public static int maxBlockAmount;
    public static String[] easyBlocks;
    public static String[] normalBlocks;
    public static String[] hardBlocks;
    public static String[] dyedBlocks;
    public static String[] endBlocks;
    public static String[] blocks;

    public static void init() {
        ArrayList<String> var = new ArrayList<>();
        Collections.addAll(var, easyBlocks);
        if (enableNormalBlock) Collections.addAll(var, normalBlocks);
        if (enableHardBlock) Collections.addAll(var, hardBlocks);
        if (enableDyedBlock) Collections.addAll(var, dyedBlocks);
        if (enableEndBlock) Collections.addAll(var, endBlocks);
        if (blockAmount > var.size()) {
            blockAmountCheckout = false;
            maxBlockAmount = var.size();
            return;
        } else {
            blockAmountCheckout = true;
        }
        blocks = var.toArray(new String[0]);
    }

    public static List<String> getAllBlock() {
        List<String> allBlock = new ArrayList<>();
        allBlock.addAll(Arrays.asList(easyBlocks));
        allBlock.addAll(Arrays.asList(normalBlocks));
        allBlock.addAll(Arrays.asList(hardBlocks));
        allBlock.addAll(Arrays.asList(dyedBlocks));
        allBlock.addAll(Arrays.asList(endBlocks));

        return allBlock;
    }

    public static String roll(double progress, Random random) {
        switch (rollDifficulty(progress, random)) {
            case EASY -> {
                return easyBlocks[random.nextInt(easyBlocks.length)];
            }
            case NORMAL -> {
                return normalBlocks[random.nextInt(normalBlocks.length)];
            }
            case HARD -> {
                return hardBlocks[random.nextInt(normalBlocks.length)];
            }
            case ENDER -> {
                return endBlocks[random.nextInt(normalBlocks.length)];
            }
            default -> {
                return null;
            }
        }
    }

    private static BlockDifficulty rollDifficulty(double progress, Random random) {
        double probabilityEasy = Math.pow(0.4d, 2.0d * progress) - 0.002;
        double probabilityNormal = 0.6d * progress * progress + 0.045;
        double probabilityEnder = (0.5d * Math.pow(2.0d, progress) - 0.9 > 0) ? (0.5d * Math.pow(2.0d, progress) - 0.9) : 0.0d;
        double probabilityHard = 1.0d - probabilityEasy - probabilityNormal - probabilityEnder;

        double rand = random.nextDouble();
        if (rand <= probabilityEasy) {
            return BlockDifficulty.EASY;
        } else if (rand <= probabilityNormal + probabilityEasy) {
            return BlockDifficulty.NORMAL;
        } else if (rand <= probabilityHard + probabilityNormal + probabilityEasy) {
            return BlockDifficulty.HARD;
        } else {
            return BlockDifficulty.ENDER;
        }
    }
}
