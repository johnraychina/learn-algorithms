import java.io.File;

/**
 * @author Zhang Yi
 */
public class FileStructure {

    public static void main(String[] args) {
        File file = new File("/Users/zhangyi/workspace/GitBook");
        System.out.println(file.exists());
        //递归遍历
        traverse(file, 0);
    }

    //知道递归深度才好做缩进
    public static void traverse(File file, int depth) {
        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            padding.append("  ");
        }

        System.out.println(padding.toString() + "|--" + file.getName());
        if (file.isDirectory() && file.listFiles() != null) {
            for (File child : file.listFiles()) {
                traverse(child, depth + 1);
            }
        }
    }
}
