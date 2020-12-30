import org.tensorflow.Graph;
import org.tensorflow.TensorFlow;

public class HelloTF {
    public static void main(String[] args) throws Exception {
        try (Graph g = new Graph()) {
            final String value = "Hello from " + TensorFlow.version();

            // 使用一个简单操作、一个名为 "MyConst" 的常数和一个值 "value" 来构建计算图。
            //try (Tensor t = Tensor.create(value.getBytes("UTF-8"))) {
            //    // Java API 目前还不包含足够方便的函数来执行“加”操作。
            //    g.opBuilder("Const", "MyConst").setAttr("dtype", t.dataType()).setAttr("value", t).build();
            //}
            //
            //// 在一个 Session 中执行 "MyConst" 操作。
            //try (Session s = new Session(g);
            //     Tensor output = s.runner().fetch("MyConst").run().get(0)) {
            //    System.out.println(new String(output.bytesValue(), "UTF-8"));
            //}
        }
    }
}
